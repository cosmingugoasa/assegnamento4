package _283095.javafx;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager
{
  private static final String DBURL = "mysql-loca.alwaysdata.net";
  String dbName = "loca_circolosportivo";
  private static final String LOGIN = "loca";
  private static final String PASSWORD = "prova98";
  String url = "jdbc:mysql://localhost:3306/";
  String driver = "com.mysql.cj.jdbc.Driver";
  private static Connection con = null;
  
  public static Connection getConnection()
  {
      if (con != null) return con;
      // get db, user, pass from settings file
      return getConnection(DBURL, LOGIN, PASSWORD);
  }

  private static Connection getConnection(String db_name,String user_name,String password)
  {
      try
      {
          Class.forName("com.mysql.cj.jdbc.Driver");
          con=DriverManager.getConnection(
              "jdbc:mysql://mysql-loca.alwaysdata.net/loca_circolosportivo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
              LOGIN, PASSWORD);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }

      return con;        
  }
}
