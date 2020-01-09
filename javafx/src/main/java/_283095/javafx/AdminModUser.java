package _283095.javafx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminModUser
{

  @FXML
  private TextField tfUserName;

  @FXML
  private TextField tfUserSurname;

  @FXML
  private TextField tfUserMail;

  @FXML
  private Button btnConfirm;

  @FXML
  private CheckBox cbAdmin;

  @FXML
  private Label lConfirmStatus;

  @FXML
  private PasswordField tfUserPassword;

  ResultSet selected;
  
  @FXML
  public void initialize() throws SQLException, IOException
  {
    Statement stmt = DBManager.getConnection().createStatement();
    selected = stmt.executeQuery(
        "SELECT * FROM PERSONA WHERE PERSONA.email = '" + AdminManager.getSelectedEmail() + "'");
    if (selected.next() != false)
    {
      tfUserName.setText(selected.getString("name"));
      tfUserSurname.setText(selected.getString("surname"));
      tfUserMail.setText(selected.getString("email"));
      tfUserPassword.setText(selected.getString("pwd"));
      if (selected.getString("ruolo") == "Amministratore")
      {
        cbAdmin.setSelected(true);
      }
    }
    else {
      lConfirmStatus.setText("User not found.");
      btnConfirm.setDisable(true);
    }
  }

  @FXML
  void ModUser(ActionEvent event) throws SQLException
  {
    String ruolo;
    if (cbAdmin.isSelected())
    {
      ruolo = "Amministratore";
    }
    else
    {
      ruolo = "Socio";
    }
    
    Statement stmt = DBManager.getConnection().createStatement();
    int rs = stmt
        .executeUpdate("UPDATE `PERSONA` SET `email`='" + tfUserMail.getText()
            + "',`name`='" + tfUserName.getText() + "',`surname`='"
            + tfUserSurname.getText() + "',`pwd`='" + tfUserPassword.getText()
            + "',`ruolo`='" + ruolo + "' WHERE PERSONA.email = '"
            + selected.getString("email")
            + "'");
    
    if(rs == 1) {
      ((Stage) btnConfirm.getScene().getWindow()).close();
    }else {
      lConfirmStatus.setText("Query error.");
    }
  }

}
