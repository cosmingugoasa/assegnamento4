package _283095.javafx;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AdminAddUser
{
  Alert alert = new Alert(AlertType.INFORMATION);

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
    lConfirmStatus.setText("");
    if (tfUserMail.getText().contains("@") && !tfUserName.getText().isEmpty()
        && !tfUserSurname.getText().isEmpty()
        && !tfUserPassword.getText().isEmpty())
    {
      String ruolo;
      if (cbAdmin.isSelected())
        ruolo = "Amministratore";
      else
        ruolo = "Socio";

      if (App.getUserAdmin().addUser(tfUserMail.getText(), tfUserName.getText(),
          tfUserSurname.getText(), tfUserPassword.getText(), ruolo))
      {
        alert.setContentText("Utente aggiunto con successo.");
        alert.showAndWait();
        tfUserMail.clear();
        tfUserName.clear();
        tfUserSurname.clear();
        tfUserPassword.clear();
        cbAdmin.setSelected(false);
      }
      else
      {
        alert.setContentText("Utente esistente oppure query errata.");
        alert.showAndWait();
      }

    }
    else
      lConfirmStatus.setText("Riempire i campi correttamente");
  }
}
