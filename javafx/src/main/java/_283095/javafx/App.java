package _283095.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{

  private static Stage window;
  private static Persona user;

  @Override
  public void start(Stage primaryStage) throws IOException
  {
    window = primaryStage;
    Scene login = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));

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

  public static Persona getUser()
  {
    return user;
  }

  public static  void setUserAdmin(Amministratore _user)
  {
    user = new Amministratore();
    user = _user;
  }

  public static Amministratore getUserAdmin()
  {
    return (Amministratore) user;
  }
  
  public static void setUserSocio(Socio _user)
  {
    user = new Socio();
    user = _user;
  }

  public static Socio getUserSocio()
  {
    return (Socio) user;
  }
  
}
