import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * @author rj0851 (Rachel Johnston)
 * Contains methods for JDBC and main method that runs the insertion tests
 */
public class ConnectivityFramework {
 
    /**
     * Activate the JDBC drivers, but is only setup to work with one specific 
     * driver.
      * Setup to work with a MySQL JDBC driver.
      *
      * @return Returns true if it successfully sets up the driver.
      */
    public boolean activateJDBC()
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Here");
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return true;
    }
   
    /**
     * Stores location of the DB I'm using for this HW
     */
    public static final String DB_LOCATION = "jdbc:mysql://db.engr.ship.edu:3306/csc471_12?useTimezone=true&serverTimezone=UTC";
    
    /**
     * User name for the DB I'm using
     */
    public static final String LOGIN_NAME = "csc471_12";
    
    /**
     * Password for the DB I'm using
     */
    public static final String PASSWORD = "Password_12";
    
    /**
     * Names for all of the tables we'll be using for each of the displays
     */
    //display one: tyler
    public static final String CHARACTER_TABLE_NAME = "Character";
    //display two: rachel
    public static final String PLAYER_TABLE_NAME = "Player";
    //display three: brian
    public static final String WEAPON_TABLE_NAME = "Weapon";
    //display four: alecia
    public static final String LOCATION_TABLE_NAME = "Location";
    
    
    protected Connection m_dbConn = null;
   
   
   /**
    * Creates a connection to the database that you can then send commands to.
    */
    public void createConnection()
    {
      try
      {
        m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
   
   /**
    * Executes an SQL statement that is not a SELECT statement
    * @param count (aka i from main method for loop)
    * @throws SQLException
    */
   public void insert(int count) throws SQLException
   {
     Statement stmt = m_dbConn.createStatement();
     //TODO: THIS NEEDS TO BE CHANGED
     String data = "INSERT INTO TEST_JOHNSTON (Num1,Num2,Name,Last_Name,Dub) VAlUES("+count+","+(count+1)+","+(count+10)+","+(count+20)+","+(count+0.3)+")";
     System.out.println(data);
     String insertData = new String(data);
     stmt.executeUpdate(insertData);
   }
}

