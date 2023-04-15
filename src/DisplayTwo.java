import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class DisplayTwo 
{
	public DisplayTwo()
	{
		JFrame frame = new JFrame();
		
	//Add Player Panel
		Border addPlayerBorder = BorderFactory.createTitledBorder("Add Player");
		JPanel addPlayerPanel = new JPanel();
		addPlayerPanel.setLayout(new GridLayout(0, 1));
		addPlayerPanel.setBorder(addPlayerBorder);
		//text fields for "Add Player" panel
		JTextField loginID = new JTextField("Login ID");
		JTextField playerEmail = new JTextField("Player Email");
		JTextField password = new JTextField("Password");
		addPlayerPanel.add(loginID);
		addPlayerPanel.add(playerEmail);
		addPlayerPanel.add(password);
		
	//Edit Player Panel
		Border editPlayerBorder = BorderFactory.createTitledBorder("Edit Player");
		JPanel editPlayerPanel = new JPanel();
		editPlayerPanel.setLayout(new GridLayout(0, 1));
		editPlayerPanel.setBorder(editPlayerBorder);
		//texts fields for "Edit Player" panel
		JTextField editLoginID = new JTextField("Login ID");
		JTextField editPlayerEmail = new JTextField("Player Email");
		JTextField editPassword = new JTextField("Password");
		editPlayerPanel.add(editLoginID);
		editPlayerPanel.add(editPlayerEmail);
		editPlayerPanel.add(editPassword);
		
	//Delete Player Panel
		Border deletePlayerBorder = BorderFactory.createTitledBorder("Delete Player");
		JPanel deletePlayerPanel = new JPanel();
		deletePlayerPanel.setLayout(new GridLayout(0, 1));
		deletePlayerPanel.setBorder(deletePlayerBorder);
		//text fields for "Delete Player" panel
		JTextField deleteLoginID = new JTextField("Login ID");
		deletePlayerPanel.add(deleteLoginID);
		
	//Add add, edit, and delete panels to one panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 0));
		panel1.add(addPlayerPanel);
		panel1.add(editPlayerPanel);
		panel1.add(deletePlayerPanel);
		
	//Player List Panel
		Border playerListBorder = BorderFactory.createTitledBorder("Player List");
		JPanel playerListPanel = new JPanel();
		playerListPanel.setLayout(new GridLayout(0, 1));
		playerListPanel.setBorder(playerListBorder);
		JTextField temp = new JTextField("TEMP: This should be a table of all of the entries in the table");
		playerListPanel.add(temp);
		
	//Selected Player Panel
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

	public static void main (String[] args)
	{
		new DisplayTwo();
	}
}
