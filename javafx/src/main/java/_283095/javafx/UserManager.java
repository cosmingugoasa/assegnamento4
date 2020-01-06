package _283095.javafx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class UserManager
{

  private static final String LOGIN = "loca";
  private static final String PASSWORD = "prova98";

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
    //lista attività disponibili
    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery("select * from ATTIVITA");
    while (rs.next())
    {
      lvAttivita.getItems().add(rs.getString("name"));
    }
    
    //TODO lista di attività a cui è iscritto l'utente
    
    
  }

}
