import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayTwo implements ActionListener
{
	//objects for Add Player Panel
	JButton addPlayerButton;
	JTextField loginID;
	JTextField playerEmail;
	JTextField password;
	String addThisLine;
	
	//objects for Edit Player Panel
	JButton editPlayerButton;
	JTextField editLoginID;
	JTextField editPlayerEmail;
	JTextField editPassword;
	String editThisLine;

	//objects for Delete Player Panel
	JButton deletePlayerButton;
	JTextField deleteLoginID;
	String deleteThisID;
	
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
				String loginIDToBeDeletedTemp;
				public void actionPerformed(ActionEvent e)
				{
					loginIDToBeDeletedTemp = deleteLoginID.getText();
					System.out.println(loginIDToBeDeletedTemp);
					deleteThisID = loginIDToBeDeletedTemp;
					
					//TODO: make it stall somehow before deleting
					deleteLoginID.setText("Login ID");
				}
			}
			);
		deletePlayerPanel.add(deletePlayerButton);
		
		return deletePlayerPanel;
	}

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
		editPlayerPanel.add(editLoginID);
		editPlayerPanel.add(editPlayerEmail);
		editPlayerPanel.add(editPassword);
		
		//add buttons to send whats in text fields
		editPlayerButton = new JButton("Enter");
		editPlayerButton.addActionListener(
			new ActionListener()
			{
				String editThisLineTemp;
				public void actionPerformed(ActionEvent e)
				{						
					String login = editLoginID.getText();
					String email = editPlayerEmail.getText();
					String password = editPassword.getText();
					
					editThisLineTemp = login + "," + email + "," + password;
					System.out.println(editThisLineTemp);
					editThisLine = editThisLineTemp;
					
					//TODO: make it stall for 5 seconds somehow
					editLoginID.setText("Login ID");
					editPlayerEmail.setText("Player Email");
					editPassword.setText("Password");
				}
			}
			);
		editPlayerPanel.add(editPlayerButton);
		
		return editPlayerPanel;
	}

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
				String addThisLineTemp;
				public void actionPerformed(ActionEvent e)
				{
					String login = loginID.getText();
					String email = playerEmail.getText();
					String tempPassword = password.getText();
					
					addThisLineTemp = login + "," + email + "," + tempPassword;
					System.out.println(addThisLineTemp);
					addThisLine = addThisLineTemp;
					
					//TODO: make it stay for 5 seconds somehow before clearing
					loginID.setText("Login ID");
					playerEmail.setText("Player Email");
					password.setText("Password");
				}
			}
		);
		addPlayerPanel.add(addPlayerButton);
		
		return addPlayerPanel;
	}

	public static void main (String[] args)
	{
		new DisplayTwo();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub	
	}
}
