import java.sql.*;

/**
 * @author rj0851 (Rachel Johnston)
 * Contains methods for JDBC and main method that runs the insertion tests
 */
public class ConnectivityFramework {
	
	
	/**
	 * establishes this class as a singleton
	 */
	private static ConnectivityFramework cf;
	
	/**
	 * creates connection automatically when ConnnectivityFramework object is created
	 */
	public ConnectivityFramework()
	{
		createConnection();
	}
	
	/**
	 * getter for the CF singleton
	 * @return the CF singleton
	 */
	public static ConnectivityFramework getCF()
	{
		if (cf == null)
		{
			cf = new ConnectivityFramework();
		}
		return cf;
	}
	
	/**
	 * getter for the connection created for the CF singleton
	 * @return the connection created for the CF singleton
	 */
	public Connection getConnection()
	{
		return m_dbConn;
	}
	
	/**
	 * main method, runs everything
	 * @param args command line arguments
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException
	{
	
		//statements need to be executed in this order
//		getCF().createTable(CREATE_PLAYER_TABLE_STMT);
//		getCF().createTable(CREATE_LOCATION_TABLE_STMT);
//		getCF().createTable(CREATE_CHARACTER_TABLE_STMT);
//		getCF().createTable(CREATE_ABILITY_TABLE_STMT);
//		getCF().createTable(CREATE_WEAPON_TABLE_STMT);
//		getCF().populatePlayerTable();
//		getCF().populateAbilityTable();
//		getCF().populateLocationTable();
		getCF().populateCharTable();
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
    
    /**
     * constant for CREATE statement for character
     * Tyler: display one
     */
    public static final String CREATE_CHARACTER_TABLE_STMT = 
    		"CREATE TABLE CharInfo ("
    		+ "Char_Name VARCHAR(100) PRIMARY KEY,"
    		+ "char_Strength REAL NOT NULL CHECK (char_Strength >= 0 AND char_Strength <= 100),"
    		+ "char_Stamina REAL NOT NULL CHECK (char_Stamina >= 0 AND char_Stamina <= 100),"
    		+ "char_Current_Hit_Points INTEGER NOT NULL,"
    		+ "Char_Max_Hit_Points INTEGER NOT NULL,"
    		+ "Player_ID INTEGER NOT NULL,"
    		+ "Location_ID INTEGER NOT NULL"
    		+ "CONSTRAINT fk_CharInfo_Player FOREIGN KEY (Player_ID) REFERENCES Player(P_Login),"
    		+ "Location_ID INTEGER NOT NULL, "
    		+ "CONSTRAINT fk_CharInfo_Location FOREIGN KEY (Location_ID) REFERENCES Location(LOC_ID)"
    		+ ");";
    	
    /**
     * constant for CREATE statement for player
     * Rachel: display two
     */
    public static final String CREATE_PLAYER_TABLE_STMT =
    		"CREATE TABLE Player "
    		+ "(P_Login INTEGER PRIMARY KEY, "
    		+ "P_Password VARCHAR(10) NOT NULL CHECK (LENGTH(P_Password) >= 5), "
    		+ "P_email VARCHAR(50) NOT NULL CHECK (LENGTH(P_email) >= 15));";
    
    /**
     * constant for CREATE statement for weapon
     * Brian: display three
     */
    public static final String CREATE_WEAPON_TABLE_STMT = 
    		"CREATE TABLE Weapon("
    		+ "Ability_id INTEGER NOT NULL,"
    		+ "W_id INTEGER NOT NULL,"
    		+ "WeaponType VARCHAR(50) NOT NULL,"
    		+ "StoredWeapon VARCHAR(3) NOT NULL," //TODO: BRIAN- stored is apparently an SQL keyword so i had to change the attributes name -r
    		+ "W_Name VARCHAR(100) NOT NULL,"
    		+ "CONSTRAINT pk_weapon PRIMARY KEY (W_id, W_Name),"
    		+ "CONSTRAINT fk_weapon_ability FOREIGN KEY (Ability_id) REFERENCES Ability(Ability_id)"
    		+ " );";
    
    /**
     * constant for CREATE statement for location
     * Alecia: display four
     */
    public static final String CREATE_LOCATION_TABLE_STMT = 
    		"CREATE TABLE Location ("
    		+ "LOC_ID INTEGER PRIMARY KEY,"
    		+ "Loc_Name VARCHAR(100) NOT NULL CHECK (LENGTH(Loc_Name) >= 3),"
    		+ "Loc_Size REAL NOT NULL,"
    		+ "Loc_Type VARCHAR(20) NOT NULL CHECK (LENGTH(Loc_Type) >= 4)"
    		+ ");";
    
    /**
     * constant for CREATE statement for ability
     */
    public static final String CREATE_ABILITY_TABLE_STMT =
            "CREATE TABLE Ability (" +
            " Ability_id INTEGER PRIMARY KEY," +
            " Hit_Points INTEGER NOT NULL," +
            " Strength REAL NOT NULL CHECK (Strength >= 0 AND Strength <= 100)," +
            " Stamina REAL NOT NULL CHECK (Stamina >= 0 AND Stamina <= 100)," +
            " Repeat_Rate REAL," +
            "Repeating varchar(3) NOT NULL," +
            "Repeat_Time INTEGER," +
            "Execute_Time INTEGER NOT NULL," +
            "Ability_Type varchar(7) NOT  NULL" +  
            " );";
       
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
     * Executes CREATE TABLE SQL statements 
     * @param tableStmt the SQL statement to be executed
     * @throws SQLException if the statement is invalid
     */
    public void createTable(String tableStmt) throws SQLException
    {
    	Statement stmt = m_dbConn.createStatement();
    	System.out.println("Statement to be executed: " + tableStmt);
    	String insertData = new String(tableStmt);
    	stmt.executeUpdate(insertData);
    }
    
    /**
     * call to populate Player table with 5 entries
     * by: Rachel
     * @throws SQLException
     */
    public void populatePlayerTable() throws SQLException
    {
    	String[][] playerEntries = new String[5][3];
    	playerEntries[0][0] = "123"; playerEntries[0][1] = "password"; playerEntries[0][2] ="rachelj@gmail.com";
    	playerEntries[1][0] = "234"; playerEntries[1][1] = "hello22"; playerEntries[1][2] = "tylerl22@ship.edu";
    	playerEntries[2][0] = "345"; playerEntries[2][1] = "hey8903"; playerEntries[2][2] = "aleciam41@msn.com";
    	playerEntries[3][0] = "456"; playerEntries[3][1] = "cool20"; playerEntries[3][2] = "brian45@outlook.com";
    	playerEntries[4][0] = "567"; playerEntries[4][1] = "csc471"; playerEntries[4][2] = "drgirard@ship.edu";
    	
    	String insertStmt = "INSERT INTO Player (P_login, P_Password, P_Email) VALUES (?, ?, ?);";
    	PreparedStatement ps = m_dbConn.prepareStatement(insertStmt);
    	for (int i = 0; i < 5; i++)
    	{
    		for (int j = 0; j < 3; j++)
    		{
    			if (j == 0)
    			{
    				int n = Integer.parseInt(playerEntries[i][j]);
    				ps.setInt(j+1, n);
    			}
    			else
    			{
    				ps.setString(j+1, playerEntries[i][j]);
    			}
    		}
    		ps.executeUpdate();
    	}
    	System.out.println("populatePlayerTable() added 5 entries to Player.");
    }
    
    /**
     * call to populate Location table with 5 entries
     * by: Alecia
     */
    public void populateLocationTable() throws SQLException
	{
		String[][] locationEntries = new String[5][4];
		locationEntries[0][0] = "123456"; locationEntries[0][1] = "Watery Grave"; locationEntries[0][2] = "4896.3"; locationEntries[0][3] = "Ocean";
		locationEntries[1][0] = "654321"; locationEntries[1][1] = "Ancestral Caves"; locationEntries[1][2] = "1500.75"; locationEntries[1][3] = "Mountain";
		locationEntries[2][0] = "785968"; locationEntries[2][1] = "Burning Plains"; locationEntries[2][2] = "333.7"; locationEntries[2][3] = "Desert";
		locationEntries[3][0] = "285856"; locationEntries[3][1] = "Lost Woods"; locationEntries[3][2] = "117.85"; locationEntries[3][3] = "Forest";
		locationEntries[4][0] = "456987"; locationEntries[4][1] = "Toxic Wasteland"; locationEntries[4][2] = "100.0"; locationEntries[4][3] = "Grassland";
		String insertStmt = "INSERT INTO Location (LOC_ID, Loc_Name, Loc_Size, Loc_Type) VALUES (?, ?, ?, ?);";
		PreparedStatement ps = m_dbConn.prepareStatement(insertStmt);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 0) {
					int n = Integer.parseInt(locationEntries[i][j]);
					ps.setInt(j+1, n);
				}
				else {
					ps.setString(j+1, locationEntries[i][j]);
				}
			}
			ps.executeUpdate();
		}
		System.out.println("populateLocationTable() added 5 entries to Location.");
    }
    
    /**
     * call to populate CharInfo table with 5 different entries
     * Author: tyler
     * @throws SQLException 
     */
    public void populateCharTable() throws SQLException
    {
      String[][] characterEntries = new String[5][7];
      characterEntries[0][0] = "tyler"; characterEntries[0][1] = "50"; characterEntries[0][2] ="50"; characterEntries[0][3] = "50"; characterEntries[0][4] = "50"; characterEntries[0][5] = "123"; characterEntries[0][6] = "123456";
      characterEntries[1][0] = "rachel"; characterEntries[1][1] = "40"; characterEntries[1][2] = "40"; characterEntries[1][3] = "40"; characterEntries[1][4] = "40"; characterEntries[1][5] = "123"; characterEntries[1][6] = "654321";
      characterEntries[2][0] = "Brian"; characterEntries[2][1] = "30"; characterEntries[2][2] = "30"; characterEntries[2][3] = "30"; characterEntries[2][4] = "30"; characterEntries[2][5] = "123"; characterEntries[2][6] = "785968";
      characterEntries[3][0] = "Alecia"; characterEntries[3][1] = "20"; characterEntries[3][2] = "20"; characterEntries[3][3] = "20"; characterEntries[3][4] = "20"; characterEntries[3][5] = "123"; characterEntries[3][6] = "285856";
      characterEntries[4][0] = "bob"; characterEntries[4][1] = "10"; characterEntries[4][2] = "10"; characterEntries[4][3] = "10"; characterEntries[4][4] = "10"; characterEntries[4][5] = "123"; characterEntries[4][6] = "456987";
      
      String insertStmt = "INSERT INTO CharInfo (Char_Name, char_Strength, char_Stamina, char_Current_Hit_Points, Char_Max_Hit_Points, Player_ID, Location_ID) VALUES (?, ?, ?, ?, ?, ?, ?);";
      PreparedStatement ps = m_dbConn.prepareStatement(insertStmt);
      for (int i = 0; i < 5; i++)
      {
        for (int j = 0; j < 7; j++)
        {
          //int attributes
          if ((j == 3) || (j == 4) || (j == 5) || (j == 6))
          {
            int x = Integer.parseInt(characterEntries[i][j]);
            ps.setInt(j+1, x);
          }
          //real attributes
          else if ((j == 1) || (j == 2))
          {
            double x = Double.parseDouble(characterEntries[i][j]);
            ps.setDouble(j+1, x);
          }
          //char attributes
          else if (j == 0)
          {
            ps.setString(j+1, characterEntries[i][j]);
          }
        }
        ps.executeUpdate();
      }
      System.out.println("populateCharTable() added 5 entries to CharInfo.");
    }

    
    
    /**
     * call to populate Ability table with 5 different entries
     * @throws SQLException 
     */
    public void populateAbilityTable() throws SQLException
    {
        String[][] abilityEntries = new String[5][9];
        abilityEntries[0][0] = "1"; abilityEntries[0][1] = "15"; abilityEntries[0][2] = "10.0"; abilityEntries[0][3] = "50.5"; abilityEntries[0][4] = "3.3"; abilityEntries[0][5] = "No"; abilityEntries[0][6] = "2"; abilityEntries[0][7] = "3"; abilityEntries[0][8] = "Speed";  
        abilityEntries[1][0] = "2"; abilityEntries[1][1] = "10"; abilityEntries[1][2] = "5.5"; abilityEntries[1][3] = "25.5"; abilityEntries[1][4] = "2.2"; abilityEntries[1][5] = "Yes"; abilityEntries[1][6] = "1"; abilityEntries[1][7] = "4"; abilityEntries[1][8] = "Heal";
        abilityEntries[2][0] = "3"; abilityEntries[2][1] = "20"; abilityEntries[2][2] = "7.25"; abilityEntries[2][3] = "20.2"; abilityEntries[2][4] = "3.4"; abilityEntries[2][5] = "Yes"; abilityEntries[2][6] = "3"; abilityEntries[2][7] = "2"; abilityEntries[2][8] = "Invis"; 
        abilityEntries[3][0] = "4"; abilityEntries[3][1] = "5"; abilityEntries[3][2] = "15.2"; abilityEntries[3][3] = "30.6"; abilityEntries[3][4] = "4.5"; abilityEntries[3][5] = "No"; abilityEntries[3][6] = "4"; abilityEntries[3][7] = "1"; abilityEntries[3][8] = "2xDam";
        abilityEntries[4][0] = "5"; abilityEntries[4][1] = "25"; abilityEntries[4][2] = "20.1"; abilityEntries[4][3] = "22.6"; abilityEntries[4][4] = "5.6"; abilityEntries[4][5] = "No"; abilityEntries[4][6] = "3"; abilityEntries[4][7] = "2"; abilityEntries[4][8] = "Blast"; 
    	
    	String insertStmt = "INSERT INTO Ability (Ability_id, Hit_Points, Strength, Stamina, Repeat_Rate, Repeating, Repeat_Time, Execute_Time, Ability_Type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    	PreparedStatement ps = m_dbConn.prepareStatement(insertStmt);
    	
    	for (int i = 0; i < 5; i++)
    	{
    		for (int j = 0; j < 9; j++)
    		{
    			//int attributes
    			if ((j == 0) || (j == 1) || (j == 6) || (j == 7))
    			{
    				int x = Integer.parseInt(abilityEntries[i][j]);
    				ps.setInt(j+1, x);
    			}
    			//real attributes
    			else if ((j == 2) || (j == 3) || (j == 4))
    			{
    				double x = Double.parseDouble(abilityEntries[i][j]);
    				ps.setDouble(j+1, x);
    			}
    			//char attributes
    			else if ((j == 5) || (j == 8))
    			{
    				ps.setString(j+1, abilityEntries[i][j]);
    			}
    		}
    		ps.executeUpdate();
    	}
    }
    
    /**
     * call to populate Weapons table with 5 different entries
     */
    public void populateWeaponsTable()
    {
    	//TODO: Brian
    }
}

