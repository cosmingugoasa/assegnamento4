package _283095.javafx;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Persona
{

  private String name;
  private String surname;
  private String email;
  private String pwd;
  private String ruolo;

  public Persona(String _name, String _surname, String _email, String _pwd,
      String _ruolo)
  {
    name = _name;
    surname = _surname;
    email = _email;
    pwd = _pwd;
    ruolo = _ruolo;
  }

  public String getName()
  {
    return this.name;
  }

  public String getSurname()
  {
    return this.surname;
  }

  public String getEmail()
  {
    return this.email;
  }

  public String getPwd()
  {
    return this.pwd;
  }

  public String getRuolo()
  {
    return this.ruolo;
  }

  /*
   * sends query to be executed on the DB.
   * returs true if query is successful
   */
  public boolean Iscriviti(String item) throws SQLException
  {
    // start transaction block
    PreparedStatement updateStm;
    try
    {
      DBManager.getConnection().setAutoCommit(false);
      updateStm = DBManager.getConnection().prepareStatement(
          "INSERT INTO `ISCRIZIONE`(`emailPersona`, `nameAttivita`, `DATA`) VALUES ('"
              + email + "','" + item + "','" + LocalDateTime.now() + "')");

      boolean result = updateStm.execute();

      DBManager.getConnection().commit();
      DBManager.getConnection().setAutoCommit(true);

      if (result == false)
      {
        return true;
      }
    }
    catch (SQLException e)
    {
      System.out.println("Errore query, attività o useremail non presente");
      return false;
    }
    return false;
  }

  /*
   * sends query to be executed on the DB.
   * returs true if query is successful
   */
  public boolean Disiscriviti(String item) throws SQLException
  {
    PreparedStatement updateStm;
    try
    {
      DBManager.getConnection().setAutoCommit(false);
      updateStm = DBManager.getConnection()
          .prepareStatement("DELETE FROM ISCRIZIONE WHERE emailPersona = '"
              + email + "' AND nameAttivita = '" + item + "'");

      boolean result = updateStm.execute();

      DBManager.getConnection().commit();
      DBManager.getConnection().setAutoCommit(true);

      if (result == false)
      {
        return true;
      }
    }
    catch (SQLException e)
    {
      System.out.println("Errore query, attività o useremail non presente");
      return false;
    }
    return false;
  }
}
