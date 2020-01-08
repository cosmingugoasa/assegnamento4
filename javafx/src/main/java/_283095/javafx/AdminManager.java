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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
  private Label lbStatus;

  @FXML
  void CheckRace(ActionEvent event)
  {
    if (cbCourse.isSelected())
    {
      cbCourse.setSelected(false);
    }
  }

  @FXML
  void checkCourse(ActionEvent event)
  {
    if (cbRace.isSelected())
    {
      cbRace.setSelected(false);
    }
  }

  @FXML
  void addActivity(ActionEvent event) throws SQLException, IOException
  {
    if (cbCourse.isSelected()
        || cbRace.isSelected() && !tbNameActivity.getText().isEmpty())
    {
      String tipologia;
      if (cbCourse.isSelected())
        tipologia = "Corso";
      else
        tipologia = "Gara";

      if (App.getUserAdmin().addAttivita(tbNameActivity.getText(), tipologia))
      {
        ((Stage) btnAddActivity.getScene().getWindow()).close();
        // UpdateLists();
      }
      else
        lbStatus.setText("Errore: Attivita Esistente");
    }
    else
      lbStatus.setText("Errore: Riempire tutti i Campi");

  }

  @FXML
  void DeleteActivity(ActionEvent event) throws SQLException, IOException
  {
    if (lvAttivita.getItems() != null)
    {
      if (App.getUserAdmin()
          .removeAttivita(lvAttivita.getSelectionModel().getSelectedItem()))
      {
        UpdateLists();
      }
      /*else
        lOperations.setText("Errore rimozione attività");*/
    }
    /*else
    lOperations.setText("Selezionare Attivita!!!");*/
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

  @FXML
  public void initialize() throws SQLException, IOException
  {
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
    Stage addForm = new Stage();
    addForm.setTitle("My New Stage Title");
    addForm.setScene(new Scene(
        FXMLLoader.load(getClass().getResource("AdminAddUser.fxml"))));
    addForm.show();
  }

  @FXML
  void AddUser(ActionEvent event) throws SQLException
  {
    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery("SELECT ATTIVITA.name FROM ATTIVITA");
  }
}
