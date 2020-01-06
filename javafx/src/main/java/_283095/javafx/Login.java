package _283095.javafx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login
{
  /*
  private static final String DBURL = "mysql-loca.alwaysdata.net";
  String dbName = "loca_circolosportivo";
  private static final String LOGIN = "loca";
  private static final String PASSWORD = "prova98";
  String url = "jdbc:mysql://localhost:3306/";
  String driver = "com.mysql.cj.jdbc.Driver";
  */

  @FXML
  private Button btnLogin;

  @FXML
  private Button btnSignUp;

  @FXML
  private Label lbEmail;

  @FXML
  private Label lbPwd;

  @FXML
  private TextField tbEmail;

  @FXML
  private PasswordField tbPwd;

  @FXML
  private Label lbAction;

  @FXML
  void Login(ActionEvent event) throws IOException
  {
    if (tbEmail.getText().contains("@") && !tbPwd.getText().isEmpty())
      // lbAction.setText("Bottone Premuto");
      Login("dax@gmail.com", "rr");
    else
      lbAction.setText("Inserire correttamente i parametri");
  }

  @FXML
  void SignUp(ActionEvent event)
  {

  }

  private void Login(final String email, final String pwd) throws IOException
  {
    try
    {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      /*Connection con = DriverManager.getConnection(
          "jdbc:mysql://mysql-loca.alwaysdata.net/loca_circolosportivo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
          LOGIN, PASSWORD);*/

      if (DBManager.getConnection() != null)
      {
        lbAction.setText("Connesso");
      }
      Statement stmt = DBManager.getConnection().createStatement();
      ResultSet rs = stmt.executeQuery("select * from PERSONA");
      while (rs.next())
      {
        String lastName = rs.getString("name");
        System.out.println(lastName + "\n");
        
      }
      
      Scene home = new Scene(FXMLLoader.load(getClass().getResource("UserManager.fxml"))); 
      App.window.setScene(home);
      
      //con.close();
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
