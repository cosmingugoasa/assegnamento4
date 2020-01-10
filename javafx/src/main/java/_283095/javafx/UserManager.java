package _283095.javafx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;

public class UserManager
{
  Alert alert = new Alert(AlertType.INFORMATION);

  @FXML
  private Button btnIscriviti;

  @FXML
  private Button btnDisiscriversi;

  @FXML
  private Button btnAdminFunctions;

  @FXML
  private ListView<String> lvAttivita;

  @FXML
  private ListView<String> lvIscrizioni;

  @FXML
  private Menu btnUser;

  @FXML
  private MenuItem btnLogout;

  @FXML
  private Button btnRefreshLists;

  @FXML
  public void initialize() throws SQLException, IOException
  {
    if (App.getUser().getRuolo().equals("Socio"))
    {
      btnAdminFunctions.setVisible(false);
    }
    btnUser.setText(App.getUser().getName());
    UpdateLists();
  }

  void setAlert(String message)
  {
    alert.setContentText(message);
    alert.showAndWait();
  }

  @FXML
  void Iscrizione(ActionEvent event) throws SQLException
  {
    String selectedActivity = lvAttivita.getSelectionModel().getSelectedItem();
    if (selectedActivity != null)
    {
      if (App.getUser().getRuolo().equals("Socio"))
      {
        if (App.getUserSocio().Iscriviti(selectedActivity))
          setAlert("Iscrizione a " + selectedActivity + " effettuata");
        else
          setAlert("Errore Iscrizione, corso non più esistente ");

      }
      else if (App.getUserAdmin()
          .Iscriviti(lvAttivita.getSelectionModel().getSelectedItem()))

        setAlert("Iscrizione a " + selectedActivity + " effettuata");
      else
        setAlert("Errore Iscrizione, corso non più esistente ");
    }
    else
      setAlert("ATTENZIONE: selezionare attività ");

    UpdateLists();
  }

  @FXML
  void Disiscrizione(ActionEvent event) throws SQLException
  {
    String selectedActivity = lvIscrizioni.getSelectionModel()
        .getSelectedItem();
    if (selectedActivity != null)
    {
      if (App.getUser().getRuolo().equals("Socio"))
      {
        if (App.getUserSocio().Disiscriviti(selectedActivity))
          setAlert("Disiscrizione effettuata");
        else
          setAlert("Errore Disiscrizione");
      }
      else
      {
        if (App.getUserAdmin().Disiscriviti(selectedActivity))
          setAlert("Disiscrizione effettuata");
        else
          setAlert("Errore Disiscrizione");
      }

    }
    else
      setAlert("ATTENZIONE: selezionare attività ");

    UpdateLists();
  }

  void UpdateLists() throws SQLException
  {
    lvAttivita.getItems().clear();
    lvIscrizioni.getItems().clear();

    // lista attivita disponibili
    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery(
        "SELECT * FROM ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
            + App.getUser().getEmail() + "'");
    if (rs.next() == false)
    {
      rs = stmt.executeQuery("SELECT ATTIVITA.name FROM ATTIVITA");
    }
    else
    {
      rs.close();
      rs = stmt.executeQuery(
          "SELECT ATTIVITA.name FROM ATTIVITA, ISCRIZIONE WHERE ATTIVITA.name NOT IN (SELECT ISCRIZIONE.nameAttivita from ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
              + App.getUser().getEmail() + "') GROUP by ATTIVITA.name");
    }

    while (rs.next())
    {
      lvAttivita.getItems().add(rs.getString("name"));
    }

    // lista di attività a cui è iscritto l'utente
    rs = stmt.executeQuery(
        "SELECT ATTIVITA.name FROM ATTIVITA WHERE ATTIVITA.name = ANY (SELECT ISCRIZIONE.nameAttivita from ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
            + App.getUser().getEmail() + "')");

    while (rs.next())
    {
      lvIscrizioni.getItems().add(rs.getString("name"));
    }
  }

  @FXML
  void ToggleAdminFunctions(ActionEvent event) throws IOException, SQLException
  {
    Scene home = new Scene(
        FXMLLoader.load(getClass().getResource("AdminManager.fxml")));
    App.setWindow(home);
  }

  @FXML
  void Logout(ActionEvent event) throws IOException
  {
    Scene home = new Scene(
        FXMLLoader.load(getClass().getResource("Login.fxml")));
    App.setWindow(home);
  }

  @FXML
  void RefreshLists(ActionEvent event) throws SQLException
  {
    UpdateLists();
  }
}
