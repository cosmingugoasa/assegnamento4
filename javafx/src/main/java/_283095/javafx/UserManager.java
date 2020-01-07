package _283095.javafx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class UserManager
{

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
  public void initialize() throws SQLException, IOException
  {
    if (App.getUser().getRuolo() == "Socio")
    {
      btnAdminFunctions.setVisible(false);
    }
    UpdateLists();
  }

  @FXML
  void Iscrizione(ActionEvent event) throws SQLException
  {
    if (App.getUser().getRuolo() == "Socio")
      App.getUserSocio()
          .Iscriviti(lvAttivita.getSelectionModel().getSelectedItem());
    else
      App.getUserAdmin()
          .Iscriviti(lvAttivita.getSelectionModel().getSelectedItem());

    UpdateLists();
  }

  @FXML
  void Disiscrizione(ActionEvent event) throws SQLException
  {
    if (App.getUser().getRuolo() == "Socio")
    {
      App.getUserSocio()
          .Disiscriviti(lvIscrizioni.getSelectionModel().getSelectedItem());
    }
    else
      App.getUserAdmin()
          .Disiscriviti(lvIscrizioni.getSelectionModel().getSelectedItem());
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
          "SELECT ATTIVITA.name FROM ATTIVITA, ISCRIZIONE WHERE ATTIVITA.id NOT IN (SELECT ISCRIZIONE.idAttivita from ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
              + App.getUser().getEmail() + "') GROUP by ATTIVITA.name");
    }

    while (rs.next())
    {
      lvAttivita.getItems().add(rs.getString("name"));
    }

    // lista di attività a cui è iscritto l'utente
    rs = stmt.executeQuery(
        "SELECT ATTIVITA.name FROM ATTIVITA WHERE ATTIVITA.id = ANY (SELECT ISCRIZIONE.idAttivita from ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
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
}
