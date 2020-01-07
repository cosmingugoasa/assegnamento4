package _283095.javafx;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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

    @FXML
    public void initialize() throws SQLException, IOException
    {
      System.out.println("Welcome to admin manager.");
    }
    
}
