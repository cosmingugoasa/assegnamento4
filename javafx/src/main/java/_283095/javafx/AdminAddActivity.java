package _283095.javafx;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminAddActivity
{
  Alert alert = new Alert(AlertType.INFORMATION);

  @FXML
  private TextField tbNameActivity;

  @FXML
  private CheckBox cbCourse;

  @FXML
  private CheckBox cbRace;

  @FXML
  private Button btnAddActivity;

  @FXML
  private Label lbStatus;

  @FXML
  void CheckRace(ActionEvent event)
  {
    if (cbCourse.isSelected())
    {
      cbCourse.setSelected(false);
    }
  }

  @FXML
  void checkCourse(ActionEvent event)
  {
    if (cbRace.isSelected())
    {
      cbRace.setSelected(false);
    }
  }

  @FXML
  void addActivity(ActionEvent event) throws SQLException, IOException
  {
    if (cbCourse.isSelected()
        || cbRace.isSelected() && !tbNameActivity.getText().isEmpty())
    {
      String tipologia;
      if (cbCourse.isSelected())
        tipologia = "Corso";
      else
        tipologia = "Gara";

      if (App.getUserAdmin().addAttivita(tbNameActivity.getText(), tipologia))
      {
        alert.setContentText("Attività aggiunta con successo");
        alert.showAndWait();
        ((Stage) btnAddActivity.getScene().getWindow()).close();        
      }
      else
      {
        alert.setContentText("Errore: Attivita Esistente");
        alert.showAndWait();
      }
    }
    else
      lbStatus.setText("Errore: Riempire tutti i Campi");

  }

}
