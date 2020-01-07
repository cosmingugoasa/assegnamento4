package _283095.javafx;

import java.sql.SQLException;
import java.sql.Statement;
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
    return this.pwd;
  }

  public void Iscriviti(String item) throws SQLException
  {
    Statement updateStm = DBManager.getConnection().createStatement();
    DBManager.getConnection().setAutoCommit(false); // start transaction block
    updateStm.executeUpdate(
        "INSERT INTO `ISCRIZIONE`(`emailPersona`, `idAttivita`, `DATA`) VALUES ('"
            + email
            + "', (SELECT ATTIVITA.id FROM ATTIVITA WHERE ATTIVITA.name = '"
            + item + "'),'" + LocalDateTime.now() + "')");
    DBManager.getConnection().commit();
    DBManager.getConnection().setAutoCommit(true);
  }

  public void Disiscriviti(String item) throws SQLException
  {
    Statement updateStm = DBManager.getConnection().createStatement();
    DBManager.getConnection().setAutoCommit(false); // start transaction block
    updateStm.executeUpdate("DELETE FROM ISCRIZIONE WHERE emailPersona = '"
        + email
        + "' AND idAttivita = (SELECT ATTIVITA.id from ATTIVITA where ATTIVITA.name = '"
        + item + "')");
    DBManager.getConnection().commit();
    DBManager.getConnection().setAutoCommit(true);

  }
}
