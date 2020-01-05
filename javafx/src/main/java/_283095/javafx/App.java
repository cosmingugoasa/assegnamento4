package _283095.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{

  private static final String fileFxml = "Login.fxml";

  @Override
  public void start(Stage primaryStage) throws IOException
  {
    Parent root = FXMLLoader.load(getClass().getResource(fileFxml));
    Scene scene = new Scene(root);

    primaryStage.setTitle("Circolo Sportivo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
