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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login
{
  @FXML
  private Button btnLogin;

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
  void UserLogin(ActionEvent event) throws IOException
  {
    if (tbEmail.getText().contains("@") && !tbPwd.getText().isEmpty())
    {
      if (TryLogin(tbEmail.getText(), tbPwd.getText()))
      {

        Scene home = new Scene(
            FXMLLoader.load(getClass().getResource("UserManager.fxml")));
        App.setWindow(home);
      }
      else
        lbAction.setText("Email o Password Errati");
    }
    else
      lbAction.setText("Inserire correttamente i parametri");
  }

  private boolean TryLogin(final String email, final String pwd)
      throws IOException
  {
    try
    {
      if (DBManager.getConnection() != null)
      {
        lbAction.setText("Connesso");

        Statement stmt = DBManager.getConnection().createStatement();
        ResultSet rs = stmt
            .executeQuery("SELECT * FROM PERSONA WHERE email = \"" + email
                + "\" AND pwd = \"" + pwd + "\";");
        while (rs.next())
        {
          switch (rs.getString("ruolo"))
          {
            case "Amministratore":
              App.setUserAdmin(new Amministratore(rs.getString("name"),
                  rs.getString("surname"), rs.getString("email"),
                  rs.getString("pwd"), rs.getString("ruolo")));
              return true;
            case "Socio":
              App.setUserSocio(new Socio(rs.getString("name"),
                  rs.getString("surname"), rs.getString("email"),
                  rs.getString("pwd"), rs.getString("ruolo")));
              return true;
          }
        }
        return false;
      }
      else
      {
        lbAction.setText("Errore Connessione");
        return false;
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
}
