import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class DisplayOne {

  String deleteThisCharacter;
  String Char_Attributes;
  
  String characterName;
  int characterStrength;
  int characterStamina;
  int characterCHP;
  int characterMHP;
  int playerID;
  
  private static final String TABLE_NAME = "CharInfo";
  private ConnectivityFramework cf = ConnectivityFramework.getCF();
  private Connection m_dbConn = cf.getConnection();
  
  public DisplayOne() {
    
    JPanel InsertCharacterPanel = insertCharacterPanel();
    JPanel UpdateCharacterPanel = updateCharacterPanel();
    JPanel DeleteCharacterPanel = deleteCharacterPanel();
    JPanel CharacterInfoPanel = charInfoPanel();
    JPanel SelectedCharacterPanel = selectedCharacterInfo();
    JPanel leftPanel = leftSidePanel(InsertCharacterPanel, UpdateCharacterPanel, DeleteCharacterPanel);
    JPanel rightPanel = rightSidePanel(CharacterInfoPanel, SelectedCharacterPanel);
    addToFrame(leftPanel, rightPanel);
    
  }




  private void addToFrame(JPanel leftPanel, JPanel rightPanel) {
    //************************************************
    // adding panels to the Fame
    
    
    JFrame frame = new JFrame();
    frame.setTitle("Display 1");
    frame.setLayout(new GridLayout(0, 2));
    frame.add(leftPanel);
    frame.add(rightPanel);
    frame.pack();
    frame.setVisible(true);
  }




  private JPanel rightSidePanel(JPanel CharacterInfoPanel, JPanel SelectedCharacterPanel) {
    //************************************************
    // right side panel
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new GridLayout(2, 0));
    rightPanel.add(CharacterInfoPanel);
    rightPanel.add(SelectedCharacterPanel);
    return rightPanel;
  }




  private JPanel leftSidePanel(JPanel InsertCharacterPanel, JPanel UpdateCharacterPanel, JPanel DeleteCharacterPanel) {
    //***********************************************
    // left side of Frame
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(3, 0));
    leftPanel.add(InsertCharacterPanel);
    leftPanel.add(UpdateCharacterPanel);
    leftPanel.add(DeleteCharacterPanel);
    return leftPanel;
  }




  private JPanel selectedCharacterInfo() {
    //************************************************
    // selected character display
    
    Border SelectedCharacterBorder = BorderFactory.createTitledBorder("Selected Character");
    JPanel SelectedCharacterPanel = new JPanel();
    SelectedCharacterPanel.setLayout(new GridLayout(0, 1));
    SelectedCharacterPanel.setBorder(SelectedCharacterBorder);
    
    DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	
    JScrollPane pane = new JScrollPane(table);
    pane.setVisible(true);
    SelectedCharacterPanel.add(pane);
    
    return SelectedCharacterPanel;
  }




  private JPanel charInfoPanel() {
    //*********************************************
    //char info panel
    Border CharacterInfoBorder = BorderFactory.createTitledBorder("Character Info");
    JPanel CharacterInfoPanel = new JPanel();
    CharacterInfoPanel.setLayout(new GridLayout(0, 1));
    CharacterInfoPanel.setBorder(CharacterInfoBorder);
    return CharacterInfoPanel;
  }




  private JPanel deleteCharacterPanel() {
    //******************************************
    // Delete Character Panel
    
    Border DeleteCharacterBorder = BorderFactory.createTitledBorder("Delete Character");
    JPanel DeleteCharacterPanel = new JPanel();
    DeleteCharacterPanel.setLayout(new GridLayout(0, 1));
    DeleteCharacterPanel.setBorder(DeleteCharacterBorder);
    JTextField char_NameD = new JTextField("Char_Name");
    JButton d = new JButton("Delete Character");
    DeleteCharacterPanel.add(char_NameD);
    
    d.addActionListener(
        new ActionListener()
        {
          String deletedCharacter;
          public void actionPerformed(ActionEvent e)
          {
            deletedCharacter = char_NameD.getText();
            System.out.println(deletedCharacter);
            deleteThisCharacter = deletedCharacter;
            new Timer().schedule(
              new TimerTask() {
                @Override
                public void run()
                {
                  char_NameD.setText("Login ID");
                }
              },
              3000
              );
          }
        }
        );

   
    DeleteCharacterPanel.add(d);
    return DeleteCharacterPanel;
  }




  private JPanel insertCharacterPanel() {
    //Insert Character Panel
    Border InsertCharacterBorder = BorderFactory.createTitledBorder("Insert Character");
    JPanel InsertCharacterPanel = new JPanel();
    InsertCharacterPanel.setLayout(new GridLayout(0, 1));
    InsertCharacterPanel.setBorder(InsertCharacterBorder);
    
    //Character Panel Fields
    JTextField char_Name = new JTextField("Char_Name");
    JTextField char_Strength = new JTextField("char_Strength");
    JTextField char_Stamina = new JTextField("char_Stamina");
    JTextField char_Current_Hit_points = new JTextField("char_Current_Hit_points");
    JTextField Char_Max_Hit_points = new JTextField("Char_Max_Hit_points");
    JTextField Player_Id = new JTextField("Player_Id");
    
   
    InsertCharacterPanel.add(char_Name);
    InsertCharacterPanel.add(char_Strength);
    InsertCharacterPanel.add(char_Stamina);
    InsertCharacterPanel.add(char_Current_Hit_points);
    InsertCharacterPanel.add(Char_Max_Hit_points);
    InsertCharacterPanel.add(Player_Id);
    
    JButton b = new JButton("Insert Character");
    
    b.addActionListener(
        new ActionListener()
        {
          String characterAttributes;
          public void actionPerformed(ActionEvent e)
          {           
            characterName = char_Name.getText();
            characterStrength = Integer.parseInt(char_Strength.getText());
            characterStamina = Integer.parseInt(char_Stamina.getText());
            characterCHP = Integer.parseInt(char_Current_Hit_points.getText());
            characterMHP = Integer.parseInt(Char_Max_Hit_points.getText());
            playerID = Integer.parseInt(Player_Id.getText());
            
            characterAttributes = characterName + "," + characterStrength + "," + characterStamina;
            System.out.println(characterAttributes);
            Char_Attributes = characterAttributes;
            new Timer().schedule(
              new TimerTask() {
                @Override
                public void run()
                {
                  char_Name.setText("Login ID");
                  char_Strength.setText("Player Email");
                  char_Stamina.setText("Password");               }
                },
                3000
              );
            }
        }
        );


    
    
    InsertCharacterPanel.add(b);
    return InsertCharacterPanel;
  }




  private JPanel updateCharacterPanel() {
    //**************************************
    
    //Update Character Panel
    Border UpdateCharacterBorder = BorderFactory.createTitledBorder("Update Character");
    JPanel UpdateCharacterPanel = new JPanel();
    UpdateCharacterPanel.setLayout(new GridLayout(0, 1));
    UpdateCharacterPanel.setBorder(UpdateCharacterBorder);
    
    //update character panel fields
    JTextField char_NameU = new JTextField("Char_Name");
    JTextField char_StrengthU = new JTextField("char_Strength");
    JTextField char_StaminaU = new JTextField("char_Stamina");
    JTextField char_Current_Hit_pointsU = new JTextField("char_Current_Hit_points");
    JTextField Char_Max_Hit_pointsU = new JTextField("Char_Max_Hit_points");
    JTextField Player_IdU = new JTextField("Player_Id");
    
    //Insert Character button
    JButton u = new JButton("Update Character");
    
    UpdateCharacterPanel.add(char_NameU);
    UpdateCharacterPanel.add(char_StrengthU);
    UpdateCharacterPanel.add(char_StaminaU);
    UpdateCharacterPanel.add(char_Current_Hit_pointsU);
    UpdateCharacterPanel.add(Char_Max_Hit_pointsU);
    UpdateCharacterPanel.add(Player_IdU);
    
    
    UpdateCharacterPanel.add(u);
    return UpdateCharacterPanel;
  }
  
  /**
	 * implemented by StatementCreator interface
	 * creates an INSERT statement
	 */
  public void insert() throws SQLException
	{
		String insertStmt = "INSERT INTO " + TABLE_NAME + " (Char_Name, char_Strength, char_Stamina, char_Current_Hit_Points, Char_Max_Hit_Points, Player_ID) VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = m_dbConn.prepareStatement(insertStmt);
		ps.setString(1, characterName);
		ps.setInt(2, characterStrength);
		ps.setInt(3, characterStamina);
		ps.setInt(4, characterCHP);
		ps.setInt(5, characterMHP);
		ps.setInt(6, playerID);
  	
		ps.executeUpdate();
		
		System.out.println("Insert: " + characterName + "," + characterStrength + "," + characterStamina + "," + characterCHP + "," + characterMHP + "," + playerID);
	}
  
	/**
	 * implemented by StatementCreator interface
	 * creates an UPDATE statement
	 */
	public void update() throws SQLException
	{
		String updateStmt = "UPDATE " + TABLE_NAME + " SET Char_Name = ?, char_Strength = ?, char_Stamina = ?, char_Current_Hit_Points = ?, Char_Max_Hit_Points = ?, Player_ID = ?;";
		PreparedStatement ps = m_dbConn.prepareStatement(updateStmt);
		ps.setInt(1, editToThisLoginID);
		ps.setString(2, editToThisPassword);
		ps.setString(3, editToThisEmail);
		ps.setInt(4, playerToBeEdited);
  	
		ps.executeUpdate();
		
		System.out.println("Update " + playerToBeEdited + "to: " + editToThisLoginID + "," + editToThisPassword + "," + editToThisEmail);
	}
  
  public static void main(String[] args) {
    new DisplayOne();

  }

}
