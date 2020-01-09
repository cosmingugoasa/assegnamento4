package _283095.javafx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AdminModUser
{
  Alert alert = new Alert(AlertType.INFORMATION);
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
    selected = stmt.executeQuery("SELECT * FROM PERSONA WHERE PERSONA.email = '"
        + AdminManager.getSelectedEmail() + "'");
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
    else
    {
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

    if (!tfUserMail.getText().isEmpty() && !tfUserName.getText().isEmpty()
        && !tfUserSurname.getText().isEmpty()
        && !tfUserPassword.getText().isEmpty())
    {
      if (App.getUserAdmin().ModifyUser(tfUserMail.getText(),
          tfUserName.getText(), tfUserSurname.getText(),
          tfUserPassword.getText(), ruolo, selected.getString("email")))
      {
        alert.setContentText("Utente Modificato");
        alert.showAndWait();
      }
      else
      {
        alert.setContentText("Errore modifica Utente ");
        alert.showAndWait();
      }
    }
    else
      lConfirmStatus.setText("Riempire i campi di inserimento");
  }

}
