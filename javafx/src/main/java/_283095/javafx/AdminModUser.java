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

public class AdminModUser {

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

    @FXML
    public void initialize() throws SQLException, IOException
    {
      Statement stmt = DBManager.getConnection().createStatement();
      ResultSet rs = stmt
          .executeQuery("SELECT * FROM PERSONA WHERE PERSONA.email = '"
              + AdminManager.getLvUsers().getSelectionModel().getSelectedItem() + "'");
      if (rs.next())
      {
        tfUserName.setText(rs.getString("name"));
        tfUserSurname.setText(rs.getString("surname"));
        tfUserMail.setText(rs.getString("email"));
        tfUserPassword.setText(rs.getString("pwd"));
        if (rs.getString("ruolo") == "Amministratore")
        {
          cbAdmin.setSelected(true);
        }
      }
    }
    
    @FXML
    void ModUser(ActionEvent event) {

    }

}
