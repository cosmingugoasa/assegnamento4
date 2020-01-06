package _283095.javafx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
  private ListView<String> lvAttivita;

  @FXML
  private ListView<String> lvIscrizioni;

  @FXML
  public void initialize() throws SQLException, IOException
  {
    System.out.println("init called");
    // lista attività disponibili
    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery(
        "SELECT ATTIVITA.name FROM ATTIVITA, ISCRIZIONE WHERE ATTIVITA.id <> ISCRIZIONE.idAttivita AND ISCRIZIONE.emailPersona = '"
            + "dax@gmail.com" + "'");
    while (rs.next())
    {
      lvAttivita.getItems().add(rs.getString("name"));
    }

    // TODO lista di attività a cui è iscritto l'utente
    rs = stmt.executeQuery(
        "SELECT ATTIVITA.name FROM `ATTIVITA` WHERE ATTIVITA.id = (SELECT ISCRIZIONE.idAttivita from ISCRIZIONE WHERE ISCRIZIONE.emailPersona = '"
            + "dax@gmail.com" + "')");
    while (rs.next())
    {
      lvIscrizioni.getItems().add(rs.getString("name"));
    }

  }

  @FXML
  void Iscrizione(ActionEvent event) throws SQLException
  {
    Statement stmt = DBManager.getConnection().createStatement();
    stmt.executeQuery(
        "INSERT INTO `ISCRIZIONE`(`emailPersona`, `idAttivita`, `DATA`) VALUES ('"
            + "dax@gmail.com"
            + "', (SELECT ATTIVITA.id FROM ATTIVITA WHERE ATTIVITA.name = '"
            + lvAttivita.getSelectionModel().getSelectedItem()
            + "'),'2020/01/01')");
  }

  // TODO refresh function
}
