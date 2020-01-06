package _283095.javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
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
  public void initialize() throws SQLException
  {
    // POPULATE LISTVIEWS
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://mysql-loca.alwaysdata.net/loca_circolosportivo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        LOGIN, PASSWORD);

    if (con != null)
    {
      return;
    }
    
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from ATTIVITA");
    while (rs.next())
    {
      lvAttivita.getItems().add(rs.toString());
    }
  }

}
