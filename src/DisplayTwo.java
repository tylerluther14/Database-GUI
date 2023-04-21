import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
	String addThisLine;
	
	/**
	 * all of these are objects for the Edit Player Panel
	 */
	JButton editPlayerButton;
	JTextField editLoginID;
	JTextField editPlayerEmail; //has to be 15-50 characters
	JTextField editPassword; //has to be between 5-10 characters
	JTextField editThisPlayer;
	String playerToBeEdited;
	String editToThisLoginID;
	String editToThisEmail;
	String editToThisPassword;

	/**
	 * all of these are objects for the Delete Player panel
	 */
	JButton deletePlayerButton;
	JTextField deleteLoginID;
	String deleteThisID;
	
	/**
	 * constant for my table name
	 */
	private static final String TABLE_NAME = "Player";
	
//	ConnectivityFramework cf = ConnectivityFramework.getCF();
//	Connection m_dbConn = cf.getConnection();
	
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
		deletePlayerPanel.setLayout(new GridLayout(0, 1));
		deletePlayerPanel.setBorder(deletePlayerBorder);
		
		//text fields for "Delete Player" panel
		deleteLoginID = new JTextField("Login ID");
		deletePlayerPanel.add(deleteLoginID);
		//add buttons to send whats in text fields
		deletePlayerButton = new JButton("Delete");
		deletePlayerButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					deleteThisID = deleteLoginID.getText();
					System.out.println(deleteThisID);
					//TODO: add delete() here
					try 
					{
						delete();
					} 
					catch (SQLException e1) 
					{
						e1.getMessage();
					}
					
					new Timer().schedule(
						new TimerTask() {
							@Override
							public void run()
							{
								deleteLoginID.setText("Login ID");
							}
						},
						3000
						);
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
		editPlayerPanel.setLayout(new GridLayout(0, 1));
		editPlayerPanel.setBorder(editPlayerBorder);
		
		//texts fields for "Edit Player" panel
		editLoginID = new JTextField("Login ID");
		editPlayerEmail = new JTextField("Player Email");
		editPassword = new JTextField("Password");
		editThisPlayer = new JTextField("Enter login ID of player you want to edit.");
		editPlayerPanel.add(editThisPlayer);
		editPlayerPanel.add(editLoginID);
		editPlayerPanel.add(editPlayerEmail);
		editPlayerPanel.add(editPassword);
		
		//add buttons to send whats in text fields
		editPlayerButton = new JButton("Enter");
		editPlayerButton.addActionListener(
			new ActionListener()
			{

				public void actionPerformed(ActionEvent e)
				{	
					playerToBeEdited = editThisPlayer.getText();
					editToThisLoginID = editLoginID.getText();
					editToThisEmail = editPlayerEmail.getText();
					editToThisPassword = editPassword.getText();

					try 
					{
						update();
					} 
					catch (SQLException e1) 
					{
						e1.getMessage();
					}
					
					System.out.println(playerToBeEdited);
					System.out.println(editToThisLoginID + "," + editToThisEmail + "," + editToThisPassword);
					new Timer().schedule(
						new TimerTask() {
							@Override
							public void run()
							{
								editLoginID.setText("Login ID");
								editPlayerEmail.setText("Player Email");
								editPassword.setText("Password");								}
							},
							3000
						);
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
		addPlayerPanel.setLayout(new GridLayout(0, 1));
		addPlayerPanel.setBorder(addPlayerBorder);
		
		//text fields for "Add Player" panel
		loginID = new JTextField("Login ID");
		playerEmail = new JTextField("Player Email");
		password = new JTextField("Password");
		addPlayerPanel.add(loginID);
		addPlayerPanel.add(playerEmail);
		addPlayerPanel.add(password);
		
		//add buttons to send whats in text fields
		addPlayerButton = new JButton("Enter");
		addPlayerButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String login = loginID.getText();
					String email = playerEmail.getText();
					String tempPassword = password.getText();
					
					addThisLine = "'" + login + "'" + "," + "'" + tempPassword + "'" + "," + "'" + email + "'";
					System.out.println(addThisLine);
					
					try 
					{
						insert();
					} catch (SQLException e1) 
					{
						e1.getMessage();
					}
				
					new Timer().schedule(
						new TimerTask() {
							@Override
							public void run()
							{
								loginID.setText("Login ID");
								playerEmail.setText("Player Email");
								password.setText("Password");							
							}								
							},
							3000
						);
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
		//Statement stmt = m_dbConn.createStatement();
		String data = "INSERT INTO " + TABLE_NAME + " (P_Login, P_Password, P_Email)" + " VALUES (" + addThisLine + ");"; 
		System.out.println(data);
    	//stmt.executeUpdate(data);
	}
	
	/**
	 * implemented by StatementCreator interface
	 * creates an UPDATE statement
	 */
	public void update() throws SQLException
	{
		//Statement stmt = m_dbConn.createStatement();
		String data = "UPDATE " + TABLE_NAME + " SET "
				+ "P_login = " + "'" + editToThisLoginID + "'" + "," + "P_Password = " 
				+ "'" + editToThisPassword + "'" + "," 
				+ "P_email = " + "'" + editToThisEmail + "'" + " WHERE P_login = " + "'" + playerToBeEdited + "'" + ";" ; //TODO: need to save original login ID and compare it here
		System.out.println(data);
    	//stmt.executeUpdate(data);
	}
	
	/**
	 * implemented by StatementCreator interface
	 * creates a DELETE statement
	 */
	public void delete() throws SQLException
	{
		//Statement stmt = m_dbConn.createStatement();
		String data = "DELETE FROM " + TABLE_NAME + " WHERE (" + "P_Login = " + "'" + deleteThisID + "'" + ");"; 
		System.out.println(data);
    	//stmt.executeUpdate(data);
	}
	
	
}
