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
  private static Stage window;
  private static Amministratore admin;
  private static Socio socio;

  @Override
  public void start(Stage primaryStage) throws IOException
  {
    window = primaryStage;
    //Parent root = FXMLLoader.load(getClass().getResource(fileFxml));
    Scene login = new Scene(FXMLLoader.load(getClass().getResource(fileFxml)));

    window.setTitle("Circolo Sportivo");
    window.setScene(login);
    window.show();
  }

  public static void main(String[] args)
  {
    launch(args);
  }

  public static Stage getWindow()
  {
    return window;
  }

  public static void setWindow(Scene _scene)
  {
    window.setScene(_scene);
  }

  public static Amministratore getAdmin()
  {
    return admin;
  }

  public static void setAdmin(Amministratore _admin)
  {
    admin = _admin;
  }

  public static void setSocio(Socio _socio)
  {
    socio = _socio;
  }

  public static Socio getSocio()
  {
    return socio;
  }

}
