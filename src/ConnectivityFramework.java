import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * @author rj0851 (Rachel Johnston)
 * Contains methods for JDBC and main method that runs the insertion tests
 */
public class ConnectivityFramework {
	
	public static void main(String[] args)
	{
		System.out.println(CREATE_CHARACTER_TABLE_STMT);
		System.out.println(CREATE_PLAYER_TABLE_STMT);
		System.out.println(CREATE_WEAPON_TABLE_STMT);
		System.out.println(CREATE_LOCATION_TABLE_STMT);
	}
 
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
    

    //display one: tyler
    public static final String CREATE_CHARACTER_TABLE_STMT = 
    		"CREATE TABLE CharInfo (Char_Name VARCHAR(100) PRIMARY KEY,"
    		+ "char_Strength REAL NOT NULL CHECK (char_Strength >= 0 AND char_Strength <= 100),"
    		+ "char_Stamina REAL NOT NULL CHECK (char_Stamina >= 0 AND char_Stamina <= 100),"
    		+ "char_Current_Hit_Points INTEGER NOT NULL,"
    		+ "Char_Max_Hit_Points INTEGER NOT NULL,"
    		+ "Player_ID INTEGER NOT NULL,"
    		+ "Location_ID INTEGER NOT NULL,"
    		+ "CONSTRAINT fk_CharInfo_Player"
    		+ "FOREIGN KEY (Player_ID) REFERENCES Player(P_Login),"
    		+ "CONSTRAINT fk_CharInfo_Location FOREIGN KEY (Location_ID) REFERENCES Location(LOC_ID)"
    		+ ");";
    		
    //display two: rachel
    public static final String CREATE_PLAYER_TABLE_STMT =
    		"CREATE TABLE Player "
    		+ "(P_Login INTEGER PRIMARY KEY, "
    		+ "P_Password VARCHAR(10) NOT NULL CHECK (LENGTH(P_Password) >= 5), "
    		+ "P_email VARCHAR(50) NOT NULL CHECK (LENGTH(P_email) >= 15));";
    
    //display three: brian
    public static final String CREATE_WEAPON_TABLE_STMT = 
    		"CREATE TABLE Weapon("
    		+ "Ability_id INTEGER NOT NULL,"
    		+ "W_id INTEGER NOT NULL,"
    		+ "WeaponType VARCHAR(50) NOT NULL,"
    		+ "Stored VARCHAR(3) NOT NULL,"
    		+ "W_Name VARCHAR(100) NOT NULL,"
    		+ "CONSTRAINT pk_weapon PRIMARY KEY (W_id, W_Name),"
    		+ "CONSTRAINT fk_weapon_ability FOREIGN KEY (Ability_id) REFERENCES Ability(Ability_id),"
    		+ "CONSTRAINT fk_weapon_id FOREIGN KEY (W_id) REFERENCES  Items(item_ID)"
    		+ " );";
    
    //display four: alecia
    public static final String CREATE_LOCATION_TABLE_STMT = 
    		"CREATE TABLE Location ("
    		+ "LOC_ID INTEGER PRIMARY KEY,"
    		+ "Loc_Name VARCHAR(100) NOT NULL CHECK (LENGTH(Loc_Name) >= 3),"
    		+ "Loc_Size REAL NOT NULL,"
    		+ "Loc_Type VARCHAR(20) NOT NULL CHECK (LENGTH(Loc_Type) >= 4),"
    		+ "Lead_Id INTEGER,"
    		+ "CONSTRAINT fk_leadid FOREIGN KEY (Lead_Id) REFERENCES Location(LOC_ID)"
    		+ ");";
    
    
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
    
    public String createTables()
    {
    	
    	
    	return null;
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

