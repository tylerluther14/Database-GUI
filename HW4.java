import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * @author rj0851 (Rachel Johnston)
 * Contains methods for JDBC and main method that runs the insertion tests
 */
public class HW4 {
 
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
    protected Connection m_dbConn = null;
    private static final int DATA_ENTRIES = 10000;
   
    //CREATE statement w/o PK: CREATE TABLE TEST_JOHNSTON (Num1 INT, Num2 INT, Name CHAR(10), Last_Name VARCHAR(15), Dub FLOAT);
    //CREATE statement w/ PK: CREATE TABLE TEST_JOHNSTON (Num1 INT, Num2 INT, Name CHAR(10), Last_Name VARCHAR(15), Dub FLOAT, PRIMARY KEY(Num1));
    //DROP statement: DROP TABLE TEST_JOHNSTON;
    //INSERT statement format: INSERT INTO TEST_JOHNSTON (Num1,Num2,Name,Last_Name,Dub) VAlUES (1,2,Rachel,Johnston,10.3)
   
    /**
     * Runs tests and prints the time taken for each insertion round
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
      long startTime = System.currentTimeMillis();
      HW4 exp = new HW4();
      exp.createConnection();
      for (int i = 0; i < DATA_ENTRIES; i++)
      {
        exp.insert(i);
      }
      long endTime = System.currentTimeMillis();
      long runningTime = endTime - startTime;
      System.out.println("Running time: " + runningTime);
    }
   
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
     String data = "INSERT INTO TEST_JOHNSTON (Num1,Num2,Name,Last_Name,Dub) VAlUES("+count+","+(count+1)+","+(count+10)+","+(count+20)+","+(count+0.3)+")";
     System.out.println(data);
     String insertData = new String(data);
     stmt.executeUpdate(insertData);
   }
}

