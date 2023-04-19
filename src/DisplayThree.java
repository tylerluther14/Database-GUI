import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class DisplayThree extends JFrame{

  public DisplayThree() {
    
    JFrame frame = new JFrame();
    //Add Weapon Panel
      Border addWeapBorder = BorderFactory.createTitledBorder("Add Weapon");
      JPanel addWeapPanel = new JPanel();
      addWeapPanel.setLayout(new GridLayout(0, 2));
      addWeapPanel.setBorder(addWeapBorder);
      
      //text fields for "Add Weapon" panel
      JLabel WeapName = new JLabel("Weapon Name: ");
      JTextField WeapNameText = new JTextField();
      JLabel WeapID = new JLabel("Weap_ID: ");
      JTextField WeapIDText = new JTextField();
      JLabel weapType = new JLabel("Weapon Type: ");
      JTextField weapTypeText = new JTextField();
      JLabel ability = new JLabel("Ability: ");
      JTextField abilityText = new JTextField();
      JLabel abilityID = new JLabel("AbilityID:");
      JTextField abilityIDText = new JTextField();
      JLabel stored = new JLabel("stored: ");
      JTextField storedText = new JTextField();
      JButton buttonAdd = new JButton("Add");
      JLabel moveAddButton = new JLabel("");
      
      
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
      
      //setSize(100, 150);
    //Modify Weapon Panel
      Border editWeapBorder = BorderFactory.createTitledBorder("Modify Weapon");
      JPanel editWeapPanel = new JPanel();
      editWeapPanel.setLayout(new GridLayout(0, 2));
      editWeapPanel.setBorder(editWeapBorder);
      JLabel editWeapName = new JLabel("Weapon Name: ");
      JTextField editWeapText = new JTextField();
      JLabel editWeapID = new JLabel("Weap_ID: ");
      JTextField editWeapIDText = new JTextField();
      JLabel editWeapType = new JLabel("Weapon Type: ");
      JTextField editWeapTypeText = new JTextField();
      JLabel editAbility = new JLabel("Ability: ");
      JTextField editAbilityText = new JTextField();
      JLabel editAbilityID = new JLabel("Ability ID: ");
      JTextField editAbilityIDText = new JTextField();
      JLabel abilityStat = new JLabel("Stat Modified: ");
      JTextField editAbilityStatText = new JTextField();
      JLabel abilityEffect = new JLabel("Stat Debuff/Buff: ");
      JTextField editAbilityEffectText = new JTextField();
      JLabel editStored = new JLabel("stored: ");
      JTextField editStoredText = new JTextField();
      JButton buttonEdit = new JButton("Modify"); 
      JLabel moveEditButton = new JLabel("");
      //texts fields for "Modify Weapon" panel
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
      editWeapPanel.add(buttonEdit);
      
    //Delete Weapon Panel
      Border deleteWeaponBorder = BorderFactory.createTitledBorder("Delete Weapon");
      JPanel deleteWeaponPanel = new JPanel();
      deleteWeaponPanel.setLayout(new GridLayout(0, 2));
      deleteWeaponPanel.setBorder(deleteWeaponBorder);
      //text fields for "Delete Weapon" panel
      JLabel deleteWeapName = new JLabel("Weapon Name: ");
      JTextField deleteWeapNameText = new JTextField();
      JLabel deleteWeapID = new JLabel("Weapon ID: ");
      JTextField deleteWeapIDText = new JTextField();
      JButton buttonDelete = new JButton("Delete");
      JLabel moveDeleteButton = new JLabel("");
      
      deleteWeaponPanel.add(deleteWeapName);
      deleteWeaponPanel.add(deleteWeapNameText);
      deleteWeaponPanel.add(deleteWeapID);
      deleteWeaponPanel.add(deleteWeapIDText);
      deleteWeaponPanel.add(moveDeleteButton);
      deleteWeaponPanel.add(buttonDelete);
    //Add add, edit, and delete panels to one panel
      JPanel panel1 = new JPanel();
      panel1.setLayout(new GridLayout(0, 3));
      panel1.add(addWeapPanel);
      panel1.add(deleteWeaponPanel);
      panel1.add(editWeapPanel);
      
    //Weapon List Panel
      Border weaponListBorder = BorderFactory.createTitledBorder("Weapon List");
      JPanel weaponListPanel = new JPanel();
      weaponListPanel.setLayout(new GridLayout(1, 0));
      weaponListPanel.setBorder(weaponListBorder);
      JTextField temp = new JTextField("TEMP: This should be a table of all of the entries in the table");
      weaponListPanel.add(temp);
      
      //Selected Weapon Panel
      Border selectedWeaponBorder = BorderFactory.createTitledBorder("Selected Weapon");
      JPanel selectedWeaponPanel = new JPanel();
      selectedWeaponPanel.setLayout(new GridLayout(0 , 1));
      selectedWeaponPanel.setBorder(selectedWeaponBorder);
      
      //create panel for left side of selected Weapon panel
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
      
      
      selectedWeapPanel.add(selectedWeapName);
      selectedWeapPanel.add(WeapNameInfo);
      selectedWeapPanel.add(selectedWeapID);
      selectedWeapPanel.add(WeapIDInfo);
      selectedWeapPanel.add(selectedWeapType);
      selectedWeapPanel.add(WeapTypeInfo);
      selectedWeapPanel.add(selectedWeapStored);
      selectedWeapPanel.add(storedInfo);
      
      //JPanel abilityInfoPanel = new JPanel();
      //selectedWeaponPanel.add(selectedWeaponCharacterList);
      
      //SELECTED WEAPON ABILITY INFO
      
      Border selectedWeaponAbility = BorderFactory.createTitledBorder("Ability Information");
      JPanel abilityInfoPanel = new JPanel();
      abilityInfoPanel.setLayout(new GridLayout(0, 2));
      abilityInfoPanel.setBorder(selectedWeaponAbility);
      
      
      JLabel selectedAbility = new JLabel("Ability: ");
      JLabel selectedAbilityInfo = new JLabel("TEMP INFO");
      JLabel selectedAbilityID = new JLabel("Ability ID: ");
      JLabel selectedAbilityIDInfo = new JLabel("TEMP INFO");
      JLabel selectedAbilityStat = new JLabel("Stat Modified: ");
      JLabel selectedAbilityStatInfo = new JLabel("TEMP INFO");
      JLabel selectedAbilityEffect = new JLabel("Stat Debuff/Buff: ");
      JLabel selectedAbilityEffectInfo = new JLabel("TEMP INFO");
      
      abilityInfoPanel.add(selectedAbility);
      abilityInfoPanel.add(selectedAbilityInfo);
      abilityInfoPanel.add(selectedAbilityID);
      abilityInfoPanel.add(selectedAbilityIDInfo);
      abilityInfoPanel.add(selectedAbilityStat);
      abilityInfoPanel.add(selectedAbilityStatInfo);
      abilityInfoPanel.add(selectedAbilityEffect);
      abilityInfoPanel.add(selectedAbilityEffectInfo);
      
      //combine sub-panels
      selectedWeaponPanel.add(selectedWeapPanel);
      selectedWeaponPanel.add(abilityInfoPanel);
      
    //Combine Weapon list and selected Weapon panels
      JPanel panel2 = new JPanel();
      panel2.setLayout(new GridLayout());
      panel2.add(weaponListPanel);
      panel2.add(selectedWeaponPanel);
      
        
    //adds all panels to the frame
      frame.setLayout(new GridLayout(0, 1));
      frame.add(panel1);
      frame.add(panel2);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Weapons Database");
      frame.pack();
      frame.setVisible(true);
      
    }
  
  public static void main(String[] args) {
    new DisplayThree();

  }

}
