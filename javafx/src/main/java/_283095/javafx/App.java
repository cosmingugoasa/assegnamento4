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
  public static Stage window;

  @Override
  public void start(Stage primaryStage) throws IOException
  {
    window = primaryStage;
    //Parent root = FXMLLoader.load(getClass().getResource(fileFxml));
    Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fileFxml)));

    window.setTitle("Circolo Sportivo");
    window.setScene(scene);
    window.show();
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
