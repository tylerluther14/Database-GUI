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
    
    
    
    JFrame frame = new JFrame();
    frame.setLayout(new GridLayout(0, 2));
    frame.add(InsertCharacterPanel);
    frame.pack();
    frame.setVisible(true);
  }
  
  
  
  
  public static void main(String[] args) {
    new DisplayOne();

  }

}
