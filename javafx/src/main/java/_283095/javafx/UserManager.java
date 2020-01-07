package _283095.javafx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    if(App.getAdmin() == null) {
      btnAdminFunctions.setVisible(false);
    }
    UpdateLists();
  }

  @FXML
  void Iscrizione(ActionEvent event) throws SQLException
  {
    Statement updateStm = DBManager.getConnection().createStatement();
    DBManager.getConnection().setAutoCommit(false); // start transaction block
    updateStm.executeUpdate(
        "INSERT INTO `ISCRIZIONE`(`emailPersona`, `idAttivita`, `DATA`) VALUES ('"
            + "dax@gmail.com"
            + "', (SELECT ATTIVITA.id FROM ATTIVITA WHERE ATTIVITA.name = '"
            + lvAttivita.getSelectionModel().getSelectedItem() + "'),'"
            + LocalDateTime.now() + "')");
    DBManager.getConnection().commit();
    DBManager.getConnection().setAutoCommit(true);
    UpdateLists();
  }

  @FXML
  void Disiscrizione(ActionEvent event) throws SQLException
  {
    Statement updateStm = DBManager.getConnection().createStatement();
    DBManager.getConnection().setAutoCommit(false); // start transaction block
    updateStm.executeUpdate(
        "DELETE FROM ISCRIZIONE WHERE emailPersona = 'dax@gmail.com' AND idAttivita = (SELECT ATTIVITA.id from ATTIVITA where ATTIVITA.name = '"
            + lvIscrizioni.getSelectionModel().getSelectedItem() + "')");
    DBManager.getConnection().commit();
    DBManager.getConnection().setAutoCommit(true);
    UpdateLists();
  }

  void UpdateLists() throws SQLException
  {
    lvAttivita.getItems().clear();
    lvIscrizioni.getItems().clear();

    // lista attivita disponibili
    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery(
        "SELECT * FROM ISCRIZIONE WHERE ISCRIZIONE.emailPersona = 'dax@gmail.com'");
    if (rs.next() == false)
    {
      rs = stmt.executeQuery("SELECT ATTIVITA.name FROM ATTIVITA");
    }
    else
    {
      rs.close();
      rs = stmt.executeQuery(
          "SELECT ATTIVITA.name FROM ATTIVITA, ISCRIZIONE WHERE ATTIVITA.id NOT IN (SELECT ISCRIZIONE.idAttivita from ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
              + "dax@gmail.com" + "') GROUP by ATTIVITA.name");
    }

    while (rs.next())
    {
      lvAttivita.getItems().add(rs.getString("name"));
    }

    // lista di attività a cui è iscritto l'utente
    rs = stmt.executeQuery(
        "SELECT ATTIVITA.name FROM ATTIVITA WHERE ATTIVITA.id = ANY (SELECT ISCRIZIONE.idAttivita from ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
            + "dax@gmail.com" + "')");

    while (rs.next())
    {
      lvIscrizioni.getItems().add(rs.getString("name"));
    }
  }
  
  @FXML
  void ToggleAdminFunctions(ActionEvent event) {

  }

}
