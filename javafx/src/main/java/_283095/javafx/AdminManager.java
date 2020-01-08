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
import javafx.scene.control.Label;
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
  
  @FXML
  private Label lOperations;

  // ADD USER ELEMENTS
  Stage addForm = new Stage();

  @FXML
  private TextField tfUserName = new TextField();

  @FXML
  private TextField tfUserSurname  = new TextField();

  @FXML
  private TextField tfUserMail  = new TextField();

  @FXML
  private TextField tfUserPassword  = new TextField();

  @FXML
  private CheckBox cbAdmin = new CheckBox();

  @FXML
  private Button btnConfirm;

  @FXML
  private Label lConfirmStatus;

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
    System.out.println("Called INIT");
    if (App.getInit() == false)
    {
      UpdateLists();
      App.setInit(true);
    }
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
        "SELECT PERSONA.name FROM PERSONA WHERE PERSONA.email <> '"
            + App.getUser().getEmail() + "'");

    while (rs.next())
    {
      lvUsers.getItems().add(rs.getString("name"));
    }
  }

  @FXML
  void OpenAddUser(ActionEvent event) throws IOException
  {
    addForm.setTitle("Add User Form");
    addForm.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminAddUser.fxml"))));
    addForm.show();
  }

  @FXML
  void OpenModUser(ActionEvent event) throws IOException, SQLException
  {
    addForm.setTitle("Mod User Form");
    addForm.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminAddUser.fxml"))));
    addForm.show();
    
    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt
        .executeQuery("SELECT * FROM PERSONA WHERE PERSONA.name = '"
            + lvUsers.getSelectionModel().getSelectedItem() + "'");
    if (rs.next())
    {
      tfUserName.setText(rs.getString("name"));
      System.out.println(tfUserName.getText());
      tfUserSurname.setText(rs.getString("surname"));
      tfUserMail.setText(rs.getString("email"));
      tfUserPassword.setText(rs.getString("pwd"));
      if (rs.getString("ruolo") == "Amministratore")
      {
        cbAdmin.setSelected(true);
      }
    }
  }

  @FXML
  void AddUser(ActionEvent event) throws SQLException, IOException
  {
    Statement stmt = DBManager.getConnection().createStatement();
    int rs;

    if (tfUserMail.getText().isEmpty() || tfUserName.getText().isEmpty()
        || tfUserSurname.getText().isEmpty()
        || tfUserPassword.getText().isEmpty())
    {
      lConfirmStatus.setText("Inputs not valid. Retry.");
      return;
    }

    if (cbAdmin.isSelected())
    {
      rs = stmt.executeUpdate(
          "INSERT INTO `PERSONA`(`email`, `name`, `surname`, `pwd`, `ruolo`) VALUES ('"
              + tfUserMail.getText() + "','" + tfUserName.getText() + "','"
              + tfUserSurname.getText() + "','" + tfUserPassword.getText()
              + "','" + "Amministratore" + "')");
    }
    else
    {
      rs = stmt.executeUpdate(
          "INSERT INTO `PERSONA`(`email`, `name`, `surname`, `pwd`, `ruolo`) VALUES ('"
              + tfUserMail.getText() + "','" + tfUserName.getText() + "','"
              + tfUserSurname.getText() + "','" + tfUserPassword.getText()
              + "','" + "Socio" + "')");
    }

    ((Stage) btnConfirm.getScene().getWindow()).close();
  }
  
  @FXML
  void DeleteUser(ActionEvent event) throws SQLException, IOException {
    Statement stmt = DBManager.getConnection().createStatement();
    int rs = stmt.executeUpdate("DELETE FROM PERSONA WHERE PERSONA.name = '" + lvUsers.getSelectionModel().getSelectedItem() + "'");
    if(rs == 1) {
      lOperations.setText("User deleted.");
      UpdateLists();
    }
  }
}
