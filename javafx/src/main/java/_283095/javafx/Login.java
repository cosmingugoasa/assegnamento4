package _283095.javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
  void Login(ActionEvent event)
  {
    if (tbEmail.getText().contains("@") && !tbPwd.getText().isEmpty())
      // lbAction.setText("Bottone Premuto");
      Login(tbEmail.getText(), tbPwd.getText());
    else
      lbAction.setText("Inserire correttamente i parametri");
  }

  @FXML
  void SignUp(ActionEvent event)
  {

  }

  private void Login(final String email, final String pwd)
  {
    try
    {
      // Class.forName("com.mysql.cj.jdbc.Driver");
      /*Connection con = DriverManager.getConnection(
          "jdbc:mysql://mysql-loca.alwaysdata.net/loca_circolosportivo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
          LOGIN, PASSWORD);*/

      if (DBManager.getConnection() != null)
      {
        lbAction.setText("Connesso");

        Statement stmt = DBManager.getConnection().createStatement();
        ResultSet rs = stmt
            .executeQuery("SELECT * FROM PERSONA WHERE email = \"" + email
                + "\" AND pwd = \"" + pwd + "\";");

        while (rs.next())
        {
          String lastName = rs.getString("name");
          System.out.println(lastName + "\n");
        }
      }
      else
        lbAction.setText("Errore Connessione");
      // con.close();
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
