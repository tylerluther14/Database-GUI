import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class DisplayOne {

  public DisplayOne() {
    
    //**************************
    
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
    
    //Insert Character button
    JButton b = new JButton("Insert Character"); 
    
    
    InsertCharacterPanel.add(char_Name);
    InsertCharacterPanel.add(char_Strength);
    InsertCharacterPanel.add(char_Stamina);
    InsertCharacterPanel.add(char_Current_Hit_points);
    InsertCharacterPanel.add(Char_Max_Hit_points);
    InsertCharacterPanel.add(Player_Id);
    InsertCharacterPanel.add(b);
    
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
    
    //******************************************
    // Delete Character Panel
    
    Border DeleteCharacterBorder = BorderFactory.createTitledBorder("Delete Character");
    JPanel DeleteCharacterPanel = new JPanel();
    DeleteCharacterPanel.setLayout(new GridLayout(0, 1));
    DeleteCharacterPanel.setBorder(DeleteCharacterBorder);
    
    // text fields for delete character
    JTextField char_NameD = new JTextField("Char_Name");
    
    DeleteCharacterPanel.add(char_NameD);
    
    //*********************************************
    //char info panel
    Border CharacterInfoBorder = BorderFactory.createTitledBorder("Character Info");
    JPanel CharacterInfoPanel = new JPanel();
    CharacterInfoPanel.setLayout(new GridLayout(0, 1));
    CharacterInfoPanel.setBorder(CharacterInfoBorder);
    
    //************************************************
    // selected character display
    
    Border SelectedCharacterBorder = BorderFactory.createTitledBorder("Selected Character");
    JPanel SelectedCharacterPanel = new JPanel();
    SelectedCharacterPanel.setLayout(new GridLayout(0, 1));
    SelectedCharacterPanel.setBorder(SelectedCharacterBorder);
    
   
    //***********************************************
    // left side of Frame
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(3, 0));
    leftPanel.add(InsertCharacterPanel);
    leftPanel.add(UpdateCharacterPanel);
    leftPanel.add(DeleteCharacterPanel);
    
    //************************************************
    // right side panel
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new GridLayout(2, 0));
    rightPanel.add(CharacterInfoPanel);
    rightPanel.add(SelectedCharacterPanel);
    
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
  
  
  
  
  public static void main(String[] args) {
    new DisplayOne();

  }

}
