package _283095.javafx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AdminManager
{

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

  // ADD USER ELEMENTS

  @FXML
  private TextField tfUserName;

  @FXML
  private TextField tfUserSurname;

  @FXML
  private TextField tfUserMail;

  @FXML
  private TextField tfUserPassword;

  @FXML
  private Button btnConfirm;

  // ADD ACTIVITIES ELEMENTS

  @FXML
  private TextField tbNameActivity;

  @FXML
  private CheckBox cbCourse;

  @FXML
  private CheckBox cbRace;

  @FXML
  private Button btnAddActivity;

  @FXML
  void Refresh(ActionEvent event) throws SQLException, IOException
  {
    UpdateLists();
  }

  @FXML
  void DeleteActivity(ActionEvent event)
  {

  }

  @FXML
  void OpenAddActivity(ActionEvent event) throws IOException
  {
    Scene AddActivityWindow = new Scene(
        FXMLLoader.load(getClass().getResource("AdminAddActivity.fxml")));
    App.setWindow(AddActivityWindow);
  }

  @FXML
  void CheckRace(ActionEvent event)
  {

  }

  @FXML
  void checkCourse(ActionEvent event)
  {

  }

  @FXML
  void addActivity(ActionEvent event)
  {
  }

  @FXML
  public void initialize() throws SQLException, IOException
  {
    UpdateLists();
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
    rs = stmt.executeQuery("SELECT PERSONA.name FROM PERSONA WHERE PERSONA.email <> '" + App.getUser().getEmail() + "'");

    while (rs.next())
    {
      lvUsers.getItems().add(rs.getString("name"));
    }
  }
  
  @FXML
  void AddUser(ActionEvent event) throws IOException {
    Stage addForm = new Stage();
    addForm.setTitle("My New Stage Title");
    addForm.setScene(new Scene(FXMLLoader.load(getClass().getResource("AdminAddUser.fxml"))));
    addForm.show();
  }
}
