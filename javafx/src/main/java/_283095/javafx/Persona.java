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

  public boolean Iscriviti(String item) throws SQLException
  {
    /*Statement stmtActivity = DBManager.getConnection().createStatement();
    Statement stmtEmail = DBManager.getConnection().createStatement();
    ResultSet activity = stmtActivity.executeQuery(
        "SELECT ATTIVITA.name FROM ATTIVITA WHERE ATTIVITA.name = '" + item
            + "'");
    ResultSet userEmail = stmtEmail.executeQuery(
        "SELECT PERSONA.email FROM PERSONA WHERE PERSONA.email = '" + email
            + "'");
    // Check if email and activity are in this time present
    if (activity.next() == true && userEmail.next() == true)
    {*/
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
