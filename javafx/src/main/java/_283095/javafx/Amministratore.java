package _283095.javafx;

import java.sql.PreparedStatement;
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

  public boolean addUser(String _email, String _name, String _surname,
      String _pwd, String _ruolo) throws SQLException
  {

    Statement updateStm = DBManager.getConnection().createStatement();
    DBManager.getConnection().setAutoCommit(false); // start transaction block
    try
    {
      int result = updateStm.executeUpdate(
          "INSERT INTO `PERSONA`(`email`, `name`, `surname`, `pwd`, `ruolo`) VALUES ('"
              + _email + "','" + _name + "','" + _surname + "','" + _pwd + "','"
              + _ruolo + "')");
      DBManager.getConnection().commit();
      DBManager.getConnection().setAutoCommit(true);
      if (result == 1)
        return true;
    }
    catch (Exception e)
    {
      return false;
    }
    return false;
  }

  public boolean removeUser(String _email) throws SQLException
  {
    Statement stmt = DBManager.getConnection().createStatement();
    int rs = stmt.executeUpdate(
        "DELETE FROM PERSONA WHERE PERSONA.email = '" + _email + "'");
    if (rs == 1)
      return true;

    return false;
  }

  public boolean ModifyUser(String _email, String _name, String _surname,
      String _pwd, String _ruolo, String ChangeUser) throws SQLException
  {

    Statement updateStm = DBManager.getConnection().createStatement();
    DBManager.getConnection().setAutoCommit(false); // start transaction block
    try
    {
      int result = updateStm.executeUpdate(
          "UPDATE `PERSONA` SET `email`='" + _email + "',`name`='" + _name
              + "',`surname`='" + _surname + "',`pwd`='" + _pwd + "',`ruolo`='"
              + _ruolo + "' WHERE PERSONA.email = '" + ChangeUser + "'");
      DBManager.getConnection().commit();
      DBManager.getConnection().setAutoCommit(true);
      if (result == 1)
        return true;
    }
    catch (Exception e)
    {
      return false;
    }
    return false;
  }

  public boolean addAttivita(String activityName, String tipologia)
      throws SQLException
  {

    PreparedStatement updateStm;
    try
    {
      DBManager.getConnection().setAutoCommit(false);
      updateStm = DBManager.getConnection().prepareStatement(
          "INSERT INTO `ATTIVITA`(`name`, `tipologia`) VALUES ('" + activityName
              + "','" + tipologia + "')");

      boolean result = updateStm.execute();

      DBManager.getConnection().commit();
      DBManager.getConnection().setAutoCommit(true);

      if (result == false)
        return true;

    }
    catch (SQLException e)
    {
      System.out.println("Errore query, attività Esistente");
      return false;
    }
    return false;

  }

  public boolean removeAttivita(String activityName)
  {
    Statement updateStm;
    try
    {
      updateStm = DBManager.getConnection().createStatement();
      DBManager.getConnection().setAutoCommit(false);

      int result = updateStm
          .executeUpdate("DELETE FROM `ATTIVITA` WHERE ATTIVITA.name = '"
              + activityName + "'");

      DBManager.getConnection().commit();
      DBManager.getConnection().setAutoCommit(true);
      if (result == 1)
        return true;
    }
    catch (SQLException e)
    {
      return false;
    } // start transaction block

    return false;

  }
}
