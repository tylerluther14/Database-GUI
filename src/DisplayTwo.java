import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Rachel Johnston
 * creates java GUI for display two (player table)
 * contains methods for creating SQL statements
 */
public class DisplayTwo implements StatementCreator
{
	/**
	 * all of these are objects for the Add Player Panel
	 */
	JButton addPlayerButton;
	JTextField loginID;
	JTextField playerEmail; //has to be 15-50 characters
	JTextField password; //has to be between 5-10 characters
	int addThisLoginID;
	String addThisPlayerEmail;
	String addThisPassword;
	
	/**
	 * all of these are objects for the Edit Player Panel
	 */
	JButton editPlayerButton;
	JTextField editLoginID;
	JTextField editPlayerEmail; //has to be 15-50 characters
	JTextField editPassword; //has to be between 5-10 characters
	JTextField editThisPlayer;
	int playerToBeEdited;
	int editToThisLoginID;
	String editToThisEmail;
	String editToThisPassword;

	/**
	 * all of these are objects for the Delete Player panel
	 */
	JButton deletePlayerButton;
	JTextField deleteLoginID;
	int deleteThisID;
	
	/**
	 * constant for my table name
	 */
	private static final String TABLE_NAME = "Player";
	
	ConnectivityFramework cf = ConnectivityFramework.getCF();
	Connection m_dbConn = cf.getConnection();
	
	/**
	 * constructor for the GUI
	 */
	public DisplayTwo()
	{
		JFrame frame = new JFrame();
		
		JPanel addPlayerPanel = createAddPlayerPanel();
		JPanel editPlayerPanel = createEditPlayerPanel();
		JPanel deletePlayerPanel = createDeletePlayerPanel();
		JPanel playerListPanel = createPlayerListPanel();
		JPanel selectedPlayerPanel = createSelectedPlayerPanel();
		
	//Add add, edit, and delete panels to one panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 0));
		panel1.add(addPlayerPanel);
		panel1.add(editPlayerPanel);
		panel1.add(deletePlayerPanel);
	
	//Combine player list and selected player panels
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 0));
		panel2.add(playerListPanel);
		panel2.add(selectedPlayerPanel);
		
	//adds all panels to the frame
		frame.setLayout(new GridLayout(0, 2));
		frame.add(panel1);
		frame.add(panel2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Display Two");
		frame.pack();
		frame.setVisible(true);
		
	}

	/**
	 * creates the Selected Player panel
	 * used in the constructor
	 * @return panel
	 */
	private JPanel createSelectedPlayerPanel() 
	{
		Border selectedPlayerBorder = BorderFactory.createTitledBorder("Selected Player");
		JPanel selectedPlayerPanel = new JPanel();
		selectedPlayerPanel.setLayout(new GridLayout(0, 2));
		selectedPlayerPanel.setBorder(selectedPlayerBorder);
		
		//create panel for left side of selected player panel
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(3, 0));
		JTextField selectedLoginID = new JTextField("Login ID");
		subPanel.add(selectedLoginID);
		JTextField selectedPlayerEmail = new JTextField("Player Email");
		subPanel.add(selectedPlayerEmail);
		JTextField selectedPlayerPassword = new JTextField("Password");
		subPanel.add(selectedPlayerPassword);
		
		//create panel for right side of selected player panel
		JPanel subPanel2 = new JPanel();
		subPanel2.setLayout(new GridLayout(0, 1));
		JTextField selectedPlayerCharacterList = new JTextField("Character List TEMP: should be a list");
		selectedPlayerPanel.add(selectedPlayerCharacterList);
		
		//combine sub-panels
		selectedPlayerPanel.add(subPanel);
		selectedPlayerPanel.add(subPanel2);
		
		return selectedPlayerPanel;
	}

	/**
	 * creates the Player List panel
	 * used in the constructor
	 * @return panel
	 */
	private JPanel createPlayerListPanel() 
	{
		Border playerListBorder = BorderFactory.createTitledBorder("Player List");
		JPanel playerListPanel = new JPanel();
		playerListPanel.setLayout(new GridLayout(0, 1));
		playerListPanel.setBorder(playerListBorder);
		JTextField temp = new JTextField("TEMP: This should be a table of all of the entries in the table");
		playerListPanel.add(temp);
		
		return playerListPanel;
	}

	/**
	 * creates the Delete Player panel
	 * used in the constructor
	 * @return panel
	 */
	private JPanel createDeletePlayerPanel() 
	{
		Border deletePlayerBorder = BorderFactory.createTitledBorder("Delete Player");
		JPanel deletePlayerPanel = new JPanel();
		deletePlayerPanel.setLayout(new GridLayout(0, 2));
		deletePlayerPanel.setBorder(deletePlayerBorder);
		
		//text fields for "Delete Player" panel
		JLabel loginLabel = new JLabel("ID of player: ");
		deleteLoginID = new JTextField("Login ID");
		
		deletePlayerPanel.add(loginLabel);
		deletePlayerPanel.add(deleteLoginID);
		//add buttons to send whats in text fields
		deletePlayerButton = new JButton("Delete");
		deletePlayerButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//this try/catch makes sure that text field only has integers
					try
					{
						deleteThisID = Integer.parseInt(deleteLoginID.getText());
						System.out.println(deleteThisID);
						
						//this try/catch accounts for SQL execution exceptions
						try 
						{
							delete();
						} 
						catch (SQLException e1) 
						{
							e1.getMessage();
						}
					}
					catch (NumberFormatException n)
					{
						//sets message in text field
						deleteLoginID.setText("Numbers only.");
					}
				}
			}
			);
		deletePlayerPanel.add(deletePlayerButton);
		
		return deletePlayerPanel;
	}
	
	/**
	 * creates the Edit Player panel
	 * used in the constructor
	 * @return panel
	 */
	private JPanel createEditPlayerPanel() 
	{
		Border editPlayerBorder = BorderFactory.createTitledBorder("Edit Player");
		JPanel editPlayerPanel = new JPanel();
		editPlayerPanel.setLayout(new GridLayout(0, 2));
		editPlayerPanel.setBorder(editPlayerBorder);
		
		//texts fields for "Edit Player" panel
		JLabel loginIDLabel = new JLabel("New Login ID: ");
		editLoginID = new JTextField();
		
		JLabel emailLabel = new JLabel("New Email: ");
		editPlayerEmail = new JTextField();
		editPlayerEmail.setDocument(new JTextFieldCharLimiter(50));
		
		JLabel passwordLabel = new JLabel("New Password: ");
		editPassword = new JTextField();
		editPassword.setDocument(new JTextFieldCharLimiter(10));
		
		JLabel editThisPlayerLabel = new JLabel("ID of player to be edited: ");
		editThisPlayer = new JTextField();

		editPlayerPanel.add(editThisPlayerLabel);
		editPlayerPanel.add(editThisPlayer);
		editPlayerPanel.add(loginIDLabel);
		editPlayerPanel.add(editLoginID);
		editPlayerPanel.add(emailLabel);
		editPlayerPanel.add(editPlayerEmail);
		editPlayerPanel.add(passwordLabel);
		editPlayerPanel.add(editPassword);
		
		//add buttons to send whats in text fields
		editPlayerButton = new JButton("Enter");
		editPlayerButton.addActionListener(
			new ActionListener()
			{

				public void actionPerformed(ActionEvent e)
				{	
					//this try/catch block accounts for the player ID thats going to be edited
					try
					{
						playerToBeEdited = Integer.parseInt(editThisPlayer.getText());
						
						//this try/catch block accounts for the updated player ID
						try
						{
							editToThisLoginID = Integer.parseInt(editLoginID.getText());
							//TODO: figure out how to set a minimum for email and password
							editToThisEmail = editPlayerEmail.getText();
							editToThisPassword = editPassword.getText();

							//this try/catch clock accounts for SQL execution exceptions
							try 
							{
								update();
							} 
							catch (SQLException e1) 
							{
								e1.getMessage();
							}
					
							System.out.println("Edit this player: " + playerToBeEdited);
							System.out.println("Update to this: " + editToThisLoginID + "," + editToThisEmail + "," + editToThisPassword);
						}
						catch (NumberFormatException n1)
						{
							editLoginID.setText("Numbers only.");
						}
					}
					catch (NumberFormatException n)
					{
						editThisPlayer.setText("Numbers only.");
					}
				}
			}
			);
		editPlayerPanel.add(editPlayerButton);
		
		return editPlayerPanel;
	}

	/**
	 * creates the Add Player panel
	 * used in the constructor
	 * @return panel
	 */
	private JPanel createAddPlayerPanel() 
	{
		Border addPlayerBorder = BorderFactory.createTitledBorder("Add Player");
		JPanel addPlayerPanel = new JPanel();
		addPlayerPanel.setLayout(new GridLayout(0, 2));
		addPlayerPanel.setBorder(addPlayerBorder);
		
		//text fields and labels for "Add Player" panel
		JLabel loginIDLabel = new JLabel("Login ID: ");
		loginID = new JTextField();
		
		JLabel emailLabel = new JLabel("Email: ");
		playerEmail = new JTextField();
		playerEmail.setDocument(new JTextFieldCharLimiter(50));
		
		JLabel passwordLabel = new JLabel("Password: ");
		password = new JTextField();
		password.setDocument(new JTextFieldCharLimiter(10));
		
		addPlayerPanel.add(loginIDLabel);
		addPlayerPanel.add(loginID);
		addPlayerPanel.add(emailLabel);
		addPlayerPanel.add(playerEmail);
		addPlayerPanel.add(passwordLabel);
		addPlayerPanel.add(password);
		
		//add buttons to send whats in text fields
		addPlayerButton = new JButton("Enter");
		addPlayerButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						addThisLoginID = Integer.parseInt(loginID.getText());
						//TODO: figure out how to set a minimum for email and password
						addThisPlayerEmail = playerEmail.getText();
						addThisPassword = password.getText();
						
						System.out.println("Adding " + addThisLoginID + "," + addThisPassword + "," + addThisPlayerEmail);
					
						try 
						{
							insert();
						} catch (SQLException e1) 
						{
							System.out.println(e1.getMessage());
						}
					}
					catch (NumberFormatException n)
					{
						loginID.setText("Numbers only");
					}
				}
			}
		);
		addPlayerPanel.add(addPlayerButton);
		
		return addPlayerPanel;
	}

	/**
	 * main method, runs everything
	 * @param args
	 */
	public static void main (String[] args)
	{
		new DisplayTwo();
	}
	
	/**
	 * implemented by StatementCreator interface
	 * creates an INSERT statement
	 */
	public void insert() throws SQLException
	{
		String insertStmt = "INSERT INTO " + TABLE_NAME + " (P_login, P_Password, P_Email) VALUES (?, ?, ?);";
		PreparedStatement ps = m_dbConn.prepareStatement(insertStmt);
		ps.setInt(1, addThisLoginID);
		ps.setString(2, addThisPassword);
		ps.setString(3, addThisPlayerEmail);
    	
		int row = ps.executeUpdate();
		
		System.out.println("Insert: " + addThisLoginID + "," + addThisPassword + "," + addThisPlayerEmail + "," + " into row" + row);
	}
	
	/**
	 * implemented by StatementCreator interface
	 * creates an UPDATE statement
	 */
	public void update() throws SQLException
	{
		String updateStmt = "UPDATE " + TABLE_NAME + " SET P_Login = ?, P_Password = ?, P_email = ? WHERE P_login = ?;";
		PreparedStatement ps = m_dbConn.prepareStatement(updateStmt);
		ps.setInt(1, editToThisLoginID);
		ps.setString(2, editToThisPassword);
		ps.setString(3, editToThisEmail);
		ps.setInt(4, playerToBeEdited);
    	
		int row = ps.executeUpdate();
		
		System.out.println("Update " + playerToBeEdited + "to: " + editToThisLoginID + "," + editToThisPassword + "," + editToThisEmail + "in row" + row);
	}
	
	/**
	 * implemented by StatementCreator interface
	 * creates a DELETE statement
	 */
	public void delete() throws SQLException
	{		
		String deleteStmt = "DELETE FROM " + TABLE_NAME + " WHERE (P_Login = ?);";
		PreparedStatement ps = m_dbConn.prepareStatement(deleteStmt);
		ps.setInt(1, deleteThisID);
		
		int row = ps.executeUpdate();
		
		System.out.print("Delete: " + deleteThisID + "from row" + row);
	}
	
	
}
