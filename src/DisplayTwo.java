import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Rachel Johnston
 * creates java GUI for display two (player table)
 * contains methods for creating SQL statements
 */
public class DisplayTwo implements StatementCreator, MouseListener
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
	 * these are objects for the Player List panel
	 */
	DefaultTableModel playerListmodel = new DefaultTableModel();
	JTable playerListTable = new JTable(playerListmodel);
	
	/**
	 * these are objects for the Selected Player panel
	 */
	JTextField selectedLoginID;
	JTextField selectedPlayerEmail;
	JTextField selectedPlayerPassword;
	DefaultTableModel charListModel = new DefaultTableModel();
	JTable selectedPlayerCharacterList;
	
	/**
	 * constant for my table name
	 */
	private static final String TABLE_NAME = "Player";
	
	/**
	 * Retrieves CF singleton to be used in the display
	 */
	private ConnectivityFramework cf = ConnectivityFramework.getCF();
	
	/**
	 * Retrieves connection from CF singleton
	 */
	private Connection m_dbConn = cf.getConnection();
	
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
		
		this.refreshPlayerListJTable(playerListmodel);
		
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
		
		JLabel loginIDLabel = new JLabel("Login ID: ");
		subPanel.add(loginIDLabel);
		selectedLoginID = new JTextField();
		selectedLoginID.setEditable(false);
		subPanel.add(selectedLoginID);
		
		JLabel emailLabel = new JLabel("Player Email: ");
		subPanel.add(emailLabel);
		selectedPlayerEmail = new JTextField();
		selectedPlayerEmail.setEditable(false);
		subPanel.add(selectedPlayerEmail);
		
		JLabel passwordLabel = new JLabel("Password: ");
		subPanel.add(passwordLabel);
		selectedPlayerPassword = new JTextField();
		selectedPlayerPassword.setEditable(false);
		subPanel.add(selectedPlayerPassword);
		
	//create panel for right side of selected player panel
		JPanel subPanel2 = new JPanel();
		subPanel2.setLayout(new GridLayout(0, 1));
		
		selectedPlayerCharacterList = new JTable(charListModel);
		charListModel.addColumn("Character name");
		JScrollPane pane = new JScrollPane(selectedPlayerCharacterList);
		
		selectedPlayerPanel.add(pane);
		
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
		
		playerListmodel.addColumn("Login ID");
		playerListmodel.addColumn("Email");
		playerListmodel.addColumn("Password");
		
		playerListTable.addMouseListener(this);
		
		JScrollPane pane = new JScrollPane(playerListTable);
		
		playerListPanel.add(pane);
		
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
							deleteLoginID.setText("");
						} 
						catch (SQLException e1) 
						{
							System.out.println(e1.getMessage());
						}
						
						refreshPlayerListJTable(playerListmodel); 
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

							//this try/catch block accounts for SQL execution exceptions
							try 
							{
								update();
								
								editLoginID.setText("");
								editPlayerEmail.setText("");
								editPassword.setText("");
								editThisPlayer.setText("");
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
						
						refreshPlayerListJTable(playerListmodel); 
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
							loginID.setText("");
							playerEmail.setText("");
							password.setText("");
						} 
						catch (SQLException e1) 
						{
							System.out.println(e1.getMessage());
						}
						
						refreshPlayerListJTable(playerListmodel); 
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
    	
		ps.executeUpdate();
		
		System.out.println("Insert: " + addThisLoginID + "," + addThisPassword + "," + addThisPlayerEmail);
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
    	
		ps.executeUpdate();
		
		System.out.println("Update " + playerToBeEdited + "to: " + editToThisLoginID + "," + editToThisPassword + "," + editToThisEmail);
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
		
		ps.executeUpdate();
		
		System.out.print("Delete: " + deleteThisID);
	}

	/**
	 * used in add, edit, and delete buttons to refresh and display updated values in the JTable
	 * @param model
	 */
	private void refreshPlayerListJTable(DefaultTableModel model) 
	{
		String selectStmt = "SELECT * FROM Player";
		
		Statement stmt;
		
		//clears the table
		while(model.getRowCount() > 0)
		{
		    model.removeRow(0);
		}
		
		//this try/catch accounts for an SQL exception when creating a statement
		try 
		{
			stmt = m_dbConn.createStatement();
			
			//this try/catch accounts for an SQL exception when executing a query
			try 
			{
				ResultSet rs = stmt.executeQuery(selectStmt);
				while (rs.next())
				{
					int loginID = rs.getInt(1);
					String password = rs.getString(2);
					String email = rs.getString(3);
					
					model.addRow(new Object[]{loginID, email, password});
				}
			} 
			catch (SQLException e1) 
			{
				System.out.println(e1.getMessage());
			}
			stmt.close();
		} 
		catch (SQLException e2) 
		{
			System.out.println(e2.getMessage());
		}
	}
	
	/**
	 * when a JTable entry is clicked, it will execute these actions
	 */
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		int index = playerListTable.getSelectedRow();
		String loginID = playerListmodel.getValueAt(index, 0).toString();
		String email = playerListmodel.getValueAt(index, 1).toString();
		String password = playerListmodel.getValueAt(index, 2).toString();
		
		//displays selected player in text views of Selected Player panel
		selectedLoginID.setText(loginID);
		selectedPlayerEmail.setText(email);
		selectedPlayerPassword.setText(password);
		
		//displays selected player's character list in charList table
		try
		{
			refreshCharListJTable(loginID);
		}
		catch (SQLException s)
		{
			System.out.println(s.getMessage());
		}
			
	}

	/**
	 * used when a mouseClicked event happens, refreshes and populates character list table 
	 * in selected player panel when a player is clicked.
	 * @param loginID
	 * @throws SQLException
	 */
	private void refreshCharListJTable(String loginID) throws SQLException 
	{
		String selectStmt = "SELECT Char_Name FROM CharInfo WHERE P_Login = ?";
		PreparedStatement ps = m_dbConn.prepareStatement(selectStmt);
		ps.setInt(0, Integer.parseInt(loginID));
		ResultSet rs = ps.executeQuery();
		
		//clears the table
		while(charListModel.getRowCount() > 0)
		{
		    charListModel.removeRow(0);
		}
		
		while (rs.next())
		{
			String charName = rs.getString(1);
			charListModel.addRow(new Object[]{charName});
		}
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//unneeded, can't get rid of it since i implemented MouseListener interface
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		//unneeded, can't get rid of it since i implemented MouseListener interface	
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		//unneeded, can't get rid of it since i implemented MouseListener interface
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		//unneeded, can't get rid of it since i implemented MouseListener interface
	}
	
	
}
