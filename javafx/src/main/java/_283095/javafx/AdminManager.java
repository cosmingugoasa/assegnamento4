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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminManager
{
  Alert alert = new Alert(AlertType.INFORMATION);

  @FXML
  public void initialize() throws SQLException, IOException
  {
    UpdateLists();
  }

  @FXML
  private ListView<String> lvUsers;

  @FXML
  private ListView<String> lvAttivita;

  @FXML
  private Button btnAddUser;

  @FXML
  private Button btnModUser;

  @FXML
  private Button btnDeleteUser;

  @FXML
  private Button btnAddActivities;

  @FXML
  private Button btnDeleteActivities;

  @FXML
  private Button btnRefreshList;

  @FXML
  private Button btnBack;

  static String selectedMail;

  @FXML
  void DeleteActivity(ActionEvent event) throws SQLException, IOException
  {
    if (lvAttivita.getSelectionModel().getSelectedItem() != null)
    {
      if (App.getUserAdmin()
          .removeAttivita(lvAttivita.getSelectionModel().getSelectedItem()))
      {
        UpdateLists();
        alert.setContentText("Attività Rimossa");
        alert.showAndWait();
      }
      else
      {
        alert.setContentText("Errore rimozione attività");
        alert.showAndWait();
      }
    }
    else
    {
      alert.setContentText("Selezionare Attivita!!!");
      alert.showAndWait();
    }
  }

  @FXML
  void Refresh(ActionEvent event) throws SQLException, IOException
  {
    UpdateLists();
  }

  @FXML
  void OpenAddActivity(ActionEvent event)
      throws IOException, SQLException, InterruptedException
  {
    Stage addAttivita = new Stage();
    addAttivita.initModality(Modality.APPLICATION_MODAL);
    addAttivita.setTitle("Add Activity");
    addAttivita.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminAddActivity.fxml"))));
    addAttivita.show();
    addAttivita.setOnCloseRequest(e -> {
      try
      {
        UpdateLists();
      }
      catch (SQLException | IOException e1)
      {
        e1.printStackTrace();
      }
    });
  }

  void UpdateLists() throws SQLException, IOException
  {
    lvAttivita.getItems().clear();
    lvUsers.getItems().clear();

    // lista attivita
    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery("SELECT ATTIVITA.name FROM ATTIVITA");

    while (rs.next())
    {
      lvAttivita.getItems().add(rs.getString("name"));
    }

    // lista di utenti
    rs = stmt.executeQuery(
        "SELECT PERSONA.email FROM PERSONA WHERE PERSONA.email <> '"
            + App.getUser().getEmail() + "'");

    while (rs.next())
    {
      lvUsers.getItems().add(rs.getString("email"));
    }
  }

  @FXML
  void OpenAddUser(ActionEvent event) throws IOException
  {
    Stage addForm = new Stage();
    addForm.initModality(Modality.APPLICATION_MODAL);
    addForm.setTitle("Add User Form");
    addForm.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminAddUser.fxml"))));
    addForm.show();

    addForm.setOnCloseRequest(e -> {
      try
      {
        UpdateLists();
      }
      catch (SQLException | IOException e1)
      {
        e1.printStackTrace();
      }
    });
  }

  @FXML
  void OpenModUser(ActionEvent event) throws IOException, SQLException
  {
    selectedMail = lvUsers.getSelectionModel().getSelectedItem();
    Stage modForm = new Stage();
    modForm.initModality(Modality.APPLICATION_MODAL);
    modForm.setTitle("Mod User Form");
    modForm.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminModUser.fxml"))));
    modForm.show();

    modForm.setOnCloseRequest(e -> {
      try
      {
        UpdateLists();
      }
      catch (SQLException | IOException e1)
      {
        e1.printStackTrace();
      }
    });
  }

  @FXML
  void DeleteUser(ActionEvent event) throws SQLException, IOException
  {
    if (lvUsers.getSelectionModel().getSelectedItem() != null)
    {

      if (App.getUserAdmin()
          .removeUser(lvUsers.getSelectionModel().getSelectedItem()))
      {

        alert.setContentText("Utente Rimosso");
        alert.showAndWait();
        UpdateLists();
      }
      else
      {
        alert.setContentText("Errore rimozione utente");
        alert.showAndWait();
      }

    }
    else
    {
      alert.setContentText("Selezionare L'utente");
      alert.showAndWait();
    }
  }

  @FXML
  void BackToUserManager(ActionEvent event) throws IOException
  {
    Scene home = new Scene(
        FXMLLoader.load(getClass().getResource("UserManager.fxml")));
    App.setWindow(home);
  }

  public static String getSelectedEmail()
  {
    return selectedMail;
  }
}
