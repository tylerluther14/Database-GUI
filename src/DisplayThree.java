import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;




public class DisplayThree extends JFrame{
  
  //Modify Weapon JTextFields
  JTextField editWeapText = new JTextField();
  JTextField editWeapIDText = new JTextField();
  JTextField editWeapTypeText = new JTextField();
  JTextField editAbilityText = new JTextField();
  JTextField editAbilityIDText = new JTextField();
  JTextField editAbilityStatText = new JTextField();
  JTextField editAbilityEffectText = new JTextField();
  JTextField editStoredText = new JTextField();
  
  //Add Weapon JTextFields
  JTextField WeapNameText = new JTextField();
  JTextField WeapIDText = new JTextField();
  JTextField weapTypeText = new JTextField();
  JTextField abilityText = new JTextField();
  JTextField abilityIDText = new JTextField();
  JTextField storedText = new JTextField();
  
  //Delete Weapon JTextFields
  JTextField deleteWeapNameText = new JTextField();
  JTextField deleteWeapIDText = new JTextField();
  
  //Table Name
  private static final String TABLE_NAME = "Weapon";
  
  //Initialize Connection
  ConnectivityFramework cf = ConnectivityFramework.getCF();
  Connection m_dbConn = cf.getConnection();
  
  //CONSTRUCTOR FOR DISPLAY 3
  public DisplayThree() {
      
      //Add add, edit, and delete panels to one panel
      JPanel addWeapPanel = addPanel();
      JPanel deleteWeapPanel = deletePanel();
      JPanel editWeapPanel = modifyPanel();
      
      JPanel panel1 = new JPanel();
      panel1.setLayout(new GridLayout(0, 3));
      panel1.add(addWeapPanel);
      panel1.add(deleteWeapPanel);
      panel1.add(editWeapPanel);
      
      
      //combine sub-panels
      //Selected Weapon Panel
      Border selectedWeaponBorder = BorderFactory.createTitledBorder("Selected Weapon");
      JPanel selectedWeaponPanel = new JPanel();
      selectedWeaponPanel.setLayout(new GridLayout(0 , 1));
      selectedWeaponPanel.setBorder(selectedWeaponBorder);
      
      JPanel selectedWeapPanel = selectedPanel();
      JPanel abilityInfoPanel = selectedAbilityPanel();
      
      selectedWeaponPanel.add(selectedWeapPanel);
      selectedWeaponPanel.add(abilityInfoPanel);
      
      JPanel weaponListPanel = weapList();
      
      //Combine Weapon list and selected Weapon panels
      JPanel panel2 = new JPanel();
      panel2.setLayout(new GridLayout());
      panel2.add(weaponListPanel);
      panel2.add(selectedWeaponPanel);
      
      createFrame(panel1, panel2);
    }
  
  //CREATES THE JFRAME AND ADDS ALL PANELS
  private JFrame createFrame(JPanel panel1, JPanel panel2) {
  //adds all panels to the frame
    JFrame frame = new JFrame();
    
    frame.setLayout(new GridLayout(0, 1));
    frame.add(panel1);
    frame.add(panel2);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Weapons Database");
    frame.pack();
    frame.setVisible(true);
    
    return frame;
  }
  
//DISPLAYS INFORMATION TO MODIFY WEAPON IN PANEL
  private JPanel modifyPanel() {
    
    //Creates the Panel and Border
    Border editWeapBorder = BorderFactory.createTitledBorder("Modify Weapon");
    JPanel editWeapPanel = new JPanel();
    editWeapPanel.setLayout(new GridLayout(0, 2));
    editWeapPanel.setBorder(editWeapBorder);
    
    JLabel editWeapName = new JLabel("Weapon Name: ");
    JLabel editWeapID = new JLabel("Weap_ID: ");
    JLabel editWeapType = new JLabel("Weapon Type: ");
    JLabel editAbility = new JLabel("Ability: ");
    JLabel editAbilityID = new JLabel("Ability ID: ");
    JLabel abilityStat = new JLabel("Stat Modified: ");
    JLabel abilityEffect = new JLabel("Stat Debuff/Buff: ");
    JLabel editStored = new JLabel("stored: ");
    JButton buttonEdit = new JButton("Modify"); 
    JLabel moveEditButton = new JLabel("");
    
    
    //texts fields and JLabels for "Modify Weapon" panel
    editWeapPanel.add(editWeapName);
    editWeapPanel.add(editWeapText);
    editWeapPanel.add(editWeapID);
    editWeapPanel.add(editWeapIDText);
    editWeapPanel.add(editWeapType);
    editWeapPanel.add(editWeapTypeText);
    editWeapPanel.add(editAbility);
    editWeapPanel.add(editAbilityText);
    editWeapPanel.add(abilityStat);
    editWeapPanel.add(editAbilityStatText);
    editWeapPanel.add(abilityEffect);
    editWeapPanel.add(editAbilityEffectText);
    editWeapPanel.add(editAbilityID);
    editWeapPanel.add(editAbilityIDText);
    editWeapPanel.add(editStored);
    editWeapPanel.add(editStoredText);
    editWeapPanel.add(moveEditButton);
    
    buttonEdit.addActionListener(
        new ActionListener()
        {
          String weapoInformation;
          public void actionPerformed(ActionEvent e)
          {           
            
            weapoInformation = editWeapText.getText() + ", " + editWeapIDText.getText() + ", " + editWeapTypeText.getText() + ", " + editAbilityText.getText()
                               + ", " + editAbilityStatText.getText() + ", " + editAbilityEffectText.getText() + ", " + editAbilityIDText.getText()
                               + ", " + editStoredText.getText();
            
            System.out.println(weapoInformation);
            new Timer().schedule(
              new TimerTask() {
                @Override
                public void run()
                 {
                  editWeapText.setText("Weapon Name");
                  editWeapIDText.setText("Weapon ID");
                  editWeapTypeText.setText("Weapon Text");
                  editAbilityText.setText("Ability");
                  editAbilityIDText.setText("Abiltiy ID");
                  editAbilityStatText.setText("Abiltiy Stat");
                  editAbilityEffectText.setText("Ability Effect");
                  editStoredText.setText("Stored");
                  
                 }
               },
                3000
              );
            }
        }
        );
    
    
    editWeapPanel.add(buttonEdit);
    
    return editWeapPanel;
 }
  
  //DISPLAYS INFORMATION TO ADD WEAPON IN PANEL
  private JPanel addPanel() {
    //Add Weapon Panel and border
    Border addWeapBorder = BorderFactory.createTitledBorder("Add Weapon");
    JPanel addWeapPanel = new JPanel();
    addWeapPanel.setLayout(new GridLayout(0, 2));
    addWeapPanel.setBorder(addWeapBorder);
    
    //text fields and JLabels for "Add Weapon" panel
    JLabel WeapName = new JLabel("Weapon Name: ");
    JLabel WeapID = new JLabel("Weap_ID: ");
    JLabel weapType = new JLabel("Weapon Type: ");
    JLabel ability = new JLabel("Ability: ");
    JLabel abilityID = new JLabel("AbilityID:");
    JLabel stored = new JLabel("stored: ");
    JButton buttonAdd = new JButton("Add");
    JLabel moveAddButton = new JLabel("");
    
    //Adds JTexts and JLabels to Panel
    addWeapPanel.add(WeapName);
    addWeapPanel.add(WeapNameText);
    addWeapPanel.add(WeapID);
    addWeapPanel.add(WeapIDText);
    addWeapPanel.add(weapType);
    addWeapPanel.add(weapTypeText);
    addWeapPanel.add(ability);
    addWeapPanel.add(abilityText);
    addWeapPanel.add(abilityID);
    addWeapPanel.add(abilityIDText);
    addWeapPanel.add(stored);
    addWeapPanel.add(storedText);
    addWeapPanel.add(moveAddButton);
    addWeapPanel.add(buttonAdd);
    
    return addWeapPanel;
  }
  
  //DISPLAYS INFORMATION TO DELETE WEAPON IN PANEL
  private JPanel deletePanel() {
    
    //Delete Weapon Panel and Border
    Border deleteWeaponBorder = BorderFactory.createTitledBorder("Delete Weapon");
    JPanel deleteWeapPanel = new JPanel();
    deleteWeapPanel.setLayout(new GridLayout(0, 2));
    deleteWeapPanel.setBorder(deleteWeaponBorder);
    
    //text fields and JLabels for "Delete Weapon" panel
    JLabel deleteWeapName = new JLabel("Weapon Name: ");
    JLabel deleteWeapID = new JLabel("Weapon ID: ");
    JButton buttonDelete = new JButton("Delete");
    JLabel moveDeleteButton = new JLabel("");
    
    //Adds JTexts and JLabels to Panel
    deleteWeapPanel.add(deleteWeapName);
    deleteWeapPanel.add(deleteWeapNameText);
    deleteWeapPanel.add(deleteWeapID);
    deleteWeapPanel.add(deleteWeapIDText);
    deleteWeapPanel.add(moveDeleteButton);
    deleteWeapPanel.add(buttonDelete);
   
    
    return deleteWeapPanel;
  }
  
  //DISPLAYS WEAPON LIST IN PANEL
  private JPanel weapList() {
    //Weapon List Panel and Border
    Border weaponListBorder = BorderFactory.createTitledBorder("Weapon List");
    JPanel weaponListPanel = new JPanel();
    weaponListPanel.setLayout(new GridLayout(1, 0));
    weaponListPanel.setBorder(weaponListBorder);
    JTextField temp = new JTextField("TEMP: This should be a table of all of the entries in the table");
    weaponListPanel.add(temp);
    
    return weaponListPanel;
  }
  
  //DISPLAYS SELECTED WEAPON INFO IN PANEL
  private JPanel selectedPanel() {
    
    //create selected weapon info Panel and Border
    JPanel selectedWeapPanel = new JPanel();
    selectedWeapPanel.setLayout(new GridLayout(0, 2));
    JLabel selectedWeapName = new JLabel("Weapon Name:    ");
    JLabel WeapNameInfo = new JLabel("TEMP INFO");
    JLabel selectedWeapID = new JLabel("Weap_ID:    ");
    JLabel WeapIDInfo = new JLabel("TEMP INFO");
    JLabel selectedWeapType = new JLabel("Weapon Type:    ");
    JLabel WeapTypeInfo = new JLabel("TEMP INFO");
    JLabel selectedWeapStored = new JLabel("Stored:   ");
    JLabel storedInfo = new JLabel("TEMP INFO");
    
    //Adds JLables to Panel
    selectedWeapPanel.add(selectedWeapName);
    selectedWeapPanel.add(WeapNameInfo);
    selectedWeapPanel.add(selectedWeapID);
    selectedWeapPanel.add(WeapIDInfo);
    selectedWeapPanel.add(selectedWeapType);
    selectedWeapPanel.add(WeapTypeInfo);
    selectedWeapPanel.add(selectedWeapStored);
    selectedWeapPanel.add(storedInfo);
    
    return selectedWeapPanel;
    
  }
  
  //DISPLAYS ABILITY INFORMATION OF THE SELECTED WEAPON
  private JPanel selectedAbilityPanel() {
    //Creates Ability info Panel in the Selected Weapon Panel
    Border selectedWeaponAbility = BorderFactory.createTitledBorder("Ability Information");
    JPanel abilityInfoPanel = new JPanel();
    abilityInfoPanel.setLayout(new GridLayout(0, 2));
    abilityInfoPanel.setBorder(selectedWeaponAbility);
    
    //Creates JLabels for Abilty INFO
    JLabel selectedAbility = new JLabel("Ability: ");
    JLabel selectedAbilityInfo = new JLabel("TEMP INFO");
    JLabel selectedAbilityID = new JLabel("Ability ID: ");
    JLabel selectedAbilityIDInfo = new JLabel("TEMP INFO");
    JLabel selectedAbilityStat = new JLabel("Stat Modified: ");
    JLabel selectedAbilityStatInfo = new JLabel("TEMP INFO");
    JLabel selectedAbilityEffect = new JLabel("Stat Debuff/Buff: ");
    JLabel selectedAbilityEffectInfo = new JLabel("TEMP INFO");
    
    //Adds JLabels to the Panel
    abilityInfoPanel.add(selectedAbility);
    abilityInfoPanel.add(selectedAbilityInfo);
    abilityInfoPanel.add(selectedAbilityID);
    abilityInfoPanel.add(selectedAbilityIDInfo);
    abilityInfoPanel.add(selectedAbilityStat);
    abilityInfoPanel.add(selectedAbilityStatInfo);
    abilityInfoPanel.add(selectedAbilityEffect);
    abilityInfoPanel.add(selectedAbilityEffectInfo);
    
    return abilityInfoPanel;
  }
  
  public static void main(String[] args) {
    new DisplayThree();

  }

}
