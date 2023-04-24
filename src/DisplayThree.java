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
import java.util.Timer;
import java.util.TimerTask;


/**
 * 
 * @author Brian
 *
 */
public class DisplayThree implements StatementCreator, MouseListener {
      
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
      
     
      int addThisAbilityID;
      int addThisWeaponID;
      String addThisWeaponType;
      String addThisStored;
      String addThisWeaponName;
      
      
      int weaponToBeEdited;
      int editToThisWeaponID;
      int editToThisAbilityID;
      double editToThisAbilityStrength;
      double editToThisAbilityStamina;
      String editToThisAbilityName;
      String editToThisWeaponType;
      String editToThisStored;
      String editToThisWeaponName;
      

      /**
       * all of these are objects for the Delete Player panel
       */
      int deleteThisID;
      
      DefaultTableModel model = new DefaultTableModel();
      JTable table = new JTable(model);
      
      //Table Name
      private static final String TABLE_NAME = "Weapon";
      private static final String TABLE_NAME2 = "Ability";
      
      private ConnectivityFramework cf = ConnectivityFramework.getCF();
      
      /**
       * Retrieves connection from CF singleton
       */
      private Connection m_dbConn = cf.getConnection();
      
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
        
        buttonAdd.addActionListener(
            new ActionListener()
            {
              String weapoInformation;
              public void actionPerformed(ActionEvent e)
              {           
                try
                {
                  
                  addThisAbilityID = Integer.parseInt(abilityIDText.getText());
                  addThisWeaponID = Integer.parseInt(WeapIDText.getText());
                  addThisWeaponType = weapTypeText.getText();
                  addThisStored = storedText.getText();
                  addThisWeaponName = WeapNameText.getText();
                  
                  
                  weapoInformation = WeapNameText.getText() + ", " + WeapIDText.getText() + ", " + weapTypeText.getText() + ", " + abilityText.getText()
                                     + ", " + abilityIDText.getText()
                                     + ", " + storedText.getText();
                  
                  System.out.println("Adding " + weapoInformation);
                 try 
                  {
                    insert();
                    WeapNameText.setText("");
                    WeapIDText.setText("");
                    weapTypeText.setText("");
                    abilityText.setText("");
                    abilityIDText.setText("");
                    storedText.setText("");
                  } 
                  catch (SQLException e1) 
                  {
                    System.out.println(e1.getMessage());
                  }
                  
                  refreshJTable(model); 
                }
                catch (NumberFormatException n)
                {
                  WeapIDText.setText("Numbers only");
                }
               }
             }
            );
        
        
        
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
        JLabel deleteWeapID = new JLabel("Weapon ID: ");
        JButton buttonDelete = new JButton("Delete");
        JLabel moveDeleteButton = new JLabel("");
        
        //Adds JTexts and JLabels to Panel
        deleteWeapPanel.add(deleteWeapID);
        deleteWeapPanel.add(deleteWeapIDText);
        deleteWeapPanel.add(moveDeleteButton);
        
        buttonDelete.addActionListener(
            new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                //this try/catch makes sure that text field only has integers
                try
                {
                  deleteThisID = Integer.parseInt(deleteWeapID.getText());
                  System.out.println(deleteThisID);
                  
                  //this try/catch accounts for SQL execution exceptions
                  try 
                  {
                    delete();
                    deleteWeapID.setText("");
                  } 
                  catch (SQLException e1) 
                  {
                    System.out.println(e1.getMessage());
                  }
                  
                  refreshJTable(model); 
                }
                catch (NumberFormatException n)
                {
                  //sets message in text field
                  deleteWeapID.setText("Numbers only.");
                }
              }
            }
            );
        
        
        
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
        
        model.addColumn("Weapon Name");
        model.addColumn("Weapon ID");
        model.addColumn("Weapon Type");
        model.addColumn("Ability");
        model.addColumn("Ability ID");
        model.addColumn("Stored");
        
        table.addMouseListener(this);
        
        JScrollPane pane = new JScrollPane(table);
        
        weaponListPanel.add(pane);
        
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
        JLabel selectedAbilityStat = new JLabel("Strength: ");
        JLabel selectedAbilityStatInfo = new JLabel("TEMP INFO");
        JLabel selectedAbilityEffect = new JLabel("Stamina: ");
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
      
      
      /**
       * implemented by StatementCreator interface
       * creates an INSERT statement
       */
      public void insert() throws SQLException
      {
        String insertStmt = "INSERT INTO " + TABLE_NAME + " (Ability_ID, W_id, WeaponType, StoredWeapon, W_Name) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = m_dbConn.prepareStatement(insertStmt);
        ps.setInt(1, addThisAbilityID);
        ps.setString(4, addThisStored);
        ps.setString(3, addThisWeaponType);
        ps.setInt(2, addThisWeaponID);
        ps.setString(5, addThisWeaponName);
        //ps.setString(4, addThisAbility);
          
        ps.executeUpdate();
        
        System.out.println("Insert: " + addThisAbilityID + "," + addThisStored + "," + addThisWeaponType);
      }
      
      /**
       * implemented by StatementCreator interface
       * creates an UPDATE statement
       */
      public void update() throws SQLException
      {
        String updateStmt = "UPDATE " + TABLE_NAME + " SET Abilty_id = ?, W_id = ?, WeaponType = ?, StoredWeapon = ?, W_Name = ? WHERE Ability_id = ?;";
        PreparedStatement ps = m_dbConn.prepareStatement(updateStmt);
        
        String updateStmtAbil = "UPDATE " + TABLE_NAME2 + " SET Ability_id = ?, Hit_Points = ?, Strength = ?, Stamina = ?, "
                                          + "Repeat_Rate = ?, Repeat_Time = ?, Execute_Time = ?, Ability_Type = ? WHERE Ability_id = ?";
        PreparedStatement ps2 = m_dbConn.prepareStatement(updateStmtAbil);
        ps.setInt(1, editToThisWeaponID);
        ps.setString(2, editToThisWeaponName);
        ps.setString(3, editToThisWeaponType);
        ps.setInt(4, weaponToBeEdited);
        
        ps2.setInt(1, editToThisAbilityID);
        ps2.setDouble(3, editToThisAbilityStrength);
        ps2.setDouble(4, editToThisAbilityStamina);
          
        ps.executeUpdate();
        ps2.executeUpdate();
        
        System.out.println("Update " + weaponToBeEdited + "to: " + editToThisWeaponID + "," + editToThisWeaponName + "," + editToThisWeaponType);
      }
      
      /**
       * implemented by StatementCreator interface
       * creates a DELETE statement
       */
      public void delete() throws SQLException
      {   
        String deleteStmt = "DELETE FROM " + TABLE_NAME + " WHERE (W_ID = ?);";
        PreparedStatement ps = m_dbConn.prepareStatement(deleteStmt);
        ps.setInt(1, deleteThisID);
        
        ps.executeUpdate();
        
        System.out.print("Delete: " + deleteThisID);
      }

      
      
      /**
       * used in add, edit, and delete buttons to refresh and display updated values in the JTable
       * @param model
       */
      private void refreshJTable(DefaultTableModel model) 
      {
        String selectStmt = "SELECT * FROM Weapon";
        
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
              
              String weapN = rs.getString(5);
              int weapID = rs.getInt(2);
              String weapT = rs.getString(3);
              int abilityID = rs.getInt(1);
              String stored = rs.getString(4);
              
              model.addRow(new Object[]{weapN, weapID, weapT, abilityID, stored});
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
        int index = table.getSelectedRow();
        String weapN = model.getValueAt(index, 0).toString();
        String weapID = model.getValueAt(index, 1).toString();
        String weapT = model.getValueAt(index, 2).toString();
        String abilityID = model.getValueAt(index, 3).toString();
        String stored = model.getValueAt(index, 4).toString();
       
        //selectedLoginID.setText(loginID);
        //selectedPlayerEmail.setText(email);
        //selectedPlayerPassword.setText(password);
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
      
      public static void main(String[] args) {
        new DisplayThree();
      }

    }
