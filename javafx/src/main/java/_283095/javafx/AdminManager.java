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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminManager
{
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
  private Label lOperations;
  
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
      }
      else
        lOperations.setText("Errore rimozione attività");
    }
    else
      lOperations.setText("Selezionare Attivita!!!");
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
    addForm.setTitle("Add User Form");
    addForm.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminAddUser.fxml"))));
    addForm.show();
  }

  @FXML
  void OpenModUser(ActionEvent event) throws IOException, SQLException
  {
    selectedMail = lvUsers.getSelectionModel().getSelectedItem();
    Stage modForm = new Stage();
    modForm.setTitle("Mod User Form");
    modForm.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminModUser.fxml"))));
    modForm.show();
    Statement stmt = DBManager.getConnection().createStatement();
  }

  @FXML
  void DeleteUser(ActionEvent event) throws SQLException, IOException
  {
    if (lvUsers.getSelectionModel().getSelectedItem() != null)
    {

      if (App.getUserAdmin()
          .removeUser(lvUsers.getSelectionModel().getSelectedItem()))
      {
        lOperations.setText("User deleted.");
        UpdateLists();
      }
      else
        lOperations.setText("Errore rimozione utente");

    }
    else
      lOperations.setText("Selezionare L'utente");
  }

  public static String getSelectedEmail()
  {
    return selectedMail;
  }
}
