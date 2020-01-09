package _283095.javafx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Amministratore extends Persona
{

  public Amministratore(String _nome, String _cognome, String _email,
      String _pwd, String _ruolo)
  {
    super(_nome, _cognome, _email, _pwd, _ruolo);
  }

  public Amministratore()
  {
    super("", "", "", "", "");
  }

  public void addUser()
  {
  }

  public void removeUser()
  {
  }

  public void ModifyUser()
  {
  }

  public void addGara()
  {
  }

  public boolean addAttivita(String activityName, String tipologia)
      throws SQLException
  {

    Statement stmt = DBManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery(
        "SELECT ATTIVITA.name FROM ATTIVITA WHERE ATTIVITA.name = '"
            + activityName + "'");

    if (rs.next() == false)
    {
      Statement updateStm = DBManager.getConnection().createStatement();
      DBManager.getConnection().setAutoCommit(false); // start transaction block
      int result = updateStm
          .executeUpdate("INSERT INTO `ATTIVITA`(`name`, `tipologia`) VALUES ('"
              + activityName + "','" + tipologia + "')");
      DBManager.getConnection().commit();
      DBManager.getConnection().setAutoCommit(true);
      if (result == 1)
        return true;
    }
    return false;
  }

  public boolean removeAttivita(String activityName) throws SQLException
  {
    Statement updateStm = DBManager.getConnection().createStatement();
    DBManager.getConnection().setAutoCommit(false); // start transaction block
    System.out.println(
        "DELETE FROM `ATTIVITA` WHERE ATTIVITA.name = '" + activityName + "'");
    int result = updateStm.executeUpdate(
        "DELETE FROM `ATTIVITA` WHERE ATTIVITA.name = '" + activityName + "'");

    DBManager.getConnection().commit();
    DBManager.getConnection().setAutoCommit(true);
    if (result == 1)
      return true;

    return false;

  }
}
