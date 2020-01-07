package _283095.javafx;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminManager {

    @FXML
    private ListView<?> lvUsers;

    @FXML
    private ListView<?> lvAttivita;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnModUser;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnAddActivities;

    @FXML
    private Button btnDeleteActivities;
    
    //ADD USER ELEMENTS
    
    @FXML
    private TextField tfUserName;

    @FXML
    private TextField tfUserSurname;

    @FXML
    private TextField tfUserMail;

    @FXML
    private TextField tfUserPassword;

    @FXML
    private Button btnConfirm;
    
    //ADD ACTIVITIES ELEMENTS
    
    @FXML
    private TextField tfActivityName;

    @FXML
    private CheckBox cbCourse;

    @FXML
    private CheckBox cbRace;


    @FXML
    public void initialize() throws SQLException, IOException
    {
      System.out.println("Welcome to admin manager.");
    }
    
}
