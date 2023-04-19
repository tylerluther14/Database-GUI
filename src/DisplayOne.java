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


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class DisplayOne {

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
    
    JButton d = new JButton("Delete Character");
    d.addActionListener(
        new ActionListener()
        {
          String deletedCharacter;
          public void actionPerformed(ActionEvent e)
          {
            deletedCharacter = deleteLoginID.getText();
            System.out.println(loginIDToBeDeletedTemp);
            deleteThisID = loginIDToBeDeletedTemp;
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

    
    // text fields for delete character
    JTextField char_NameD = new JTextField("Char_Name");
    
    DeleteCharacterPanel.add(char_NameD);
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
    
    //Insert Character button
    JButton b = new JButton("Insert Character"); 
    
    
    InsertCharacterPanel.add(char_Name);
    InsertCharacterPanel.add(char_Strength);
    InsertCharacterPanel.add(char_Stamina);
    InsertCharacterPanel.add(char_Current_Hit_points);
    InsertCharacterPanel.add(Char_Max_Hit_points);
    InsertCharacterPanel.add(Player_Id);
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
  
  
  
  
  public static void main(String[] args) {
    new DisplayOne();

  }

}
