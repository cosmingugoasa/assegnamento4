package _283095.javafx;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminAddUser
{
  @FXML
  private TextField tfUserName;

  @FXML
  private TextField tfUserSurname;

  @FXML
  private TextField tfUserMail;

  @FXML
  private PasswordField tfUserPassword;

  @FXML
  private CheckBox cbAdmin;

  @FXML
  private Button btnConfirm;

  @FXML
  private Label lConfirmStatus;
  
  @FXML
  void AddUser(ActionEvent event) throws SQLException, IOException
  {
    if (!tfUserMail.getText().isEmpty() || !tfUserName.getText().isEmpty()
        || !tfUserSurname.getText().isEmpty()
        || !tfUserPassword.getText().isEmpty())
    {
      String ruolo;
      if (cbAdmin.isSelected())
        ruolo = "Amministratore";
      else
        ruolo = "Socio";

      if (App.getUserAdmin().addUser(tfUserMail.getText(), tfUserName.getText(),
          tfUserSurname.getText(), tfUserPassword.getText(), ruolo))
      {
        ((Stage) btnConfirm.getScene().getWindow()).close();
        // UpdateLists();
      }
      else
        lConfirmStatus.setText("Errore Inserimento Utente");
    }
    else
      lConfirmStatus.setText("Inputs not valid. Retry.");
  }

}
