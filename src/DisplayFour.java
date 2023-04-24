

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.table.DefaultTableModel;


/**
 * @author Alecia Meredith
 * Creates a GUI for the user to view the Location table
 * contains methods for SQL statements
 */
public class DisplayFour implements StatementCreator, MouseListener {


    private static final String LOCATION_TABLE = "Location";
    private ConnectivityFramework cf = ConnectivityFramework.getCF();
    private Connection m_dbConn = cf.getConnection();

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    JTextField locationID;

    JTextField locationName;

    JTextField locationSize;

    JTextField locationType;


    /**
     * main method
     * @throws SQLException 
     **/
    public DisplayFour() throws SQLException {

        JFrame frame = new JFrame();

        //Add Location Info Panel
        Border addLocationInfo = BorderFactory.createTitledBorder("LOCATION INFORMATION");
        JPanel LocationInfoPanel = new JPanel();
        LocationInfoPanel.setLayout(new GridLayout(0, 1));
        LocationInfoPanel.setBorder(addLocationInfo);

        //text fields for "Add Location" panel
        new JTextField("Location ID");
        JTextField locationID;

        new JTextField("Location Name");
        JTextField locationName;
        new JTextField("Location Size");
        JTextField locationSize;
        new JTextField("Location Type");
        JTextField locationType;

        //Add Location ID panel that has 3 rows
        Border locationIDBorder = BorderFactory.createTitledBorder("LOCATION ID");
        JPanel locationIDPanel = new JPanel();
        locationIDPanel.setLayout(new GridLayout(3, 1));
        locationIDPanel.setBorder(locationIDBorder);
        locationID = new JTextField("");
        locationIDPanel.add(locationID);

        JButton editLocationID = new JButton("Edit");
        editLocationID.setLayout(new GridLayout(1, 1));

        JTextField finalLocationID1 = locationID;
        editLocationID.addActionListener(e -> finalLocationID1.setEditable(true));

        //Add Location ID panel to Location Info panel
        JPanel Panel1 = new JPanel();
        Panel1.setLayout(new GridLayout(3, 1));
        Panel1.add(locationIDPanel);
        Panel1.add(editLocationID);


        //*********************************************************************************


        //Add Location Name
        Border locationNameBorder = BorderFactory.createTitledBorder("LOCATION Name");
        JPanel locationNamePanel = new JPanel();
        locationNamePanel.setLayout(new GridLayout(3, 1));
        locationNamePanel.setBorder(locationNameBorder);
        locationName = new JTextField("");
        locationNamePanel.add(locationName);
        JButton editLocationName = new JButton("Edit");
        editLocationName.setLayout(new GridLayout(1, 1));

        JTextField finalLocationName = locationName;
        editLocationName.addActionListener(e -> finalLocationName.setEditable(true));

        //Add Location Name panel to Location Info panel
        JPanel Panel2 = new JPanel();
        Panel2.setLayout(new GridLayout(3, 1));
        Panel2.add(locationNamePanel);
        Panel2.add(editLocationName);

        //*********************************************************************************

        //Add Location Size
        Border locationSizeBorder = BorderFactory.createTitledBorder("LOCATION Size");
        JPanel locationSizePanel = new JPanel();
        locationSizePanel.setLayout(new GridLayout(3, 1));
        locationSizePanel.setBorder(locationSizeBorder);
        locationSize = new JTextField("");
        locationSizePanel.add(locationSize);
        JButton editLocationSize = new JButton("Edit");
        editLocationSize.setLayout(new GridLayout(1, 1));

        JTextField finalLocationSize = locationSize;
        editLocationName.addActionListener(e -> finalLocationSize.setEditable(true));

        //Add Location Size panel to Location Info panel
        JPanel Panel3 = new JPanel();
        Panel3.setLayout(new GridLayout(3, 1));
        Panel3.add(locationSizePanel);
        Panel3.add(editLocationSize);

        //*********************************************************************************

        //Add Location Type
        Border locationTypeBorder = BorderFactory.createTitledBorder("LOCATION Type");
        JPanel locationTypePanel = new JPanel();
        locationTypePanel.setLayout(new GridLayout(3, 1));
        locationTypePanel.setBorder(locationTypeBorder);
        locationType = new JTextField("");
        locationTypePanel.add(locationType);
        JButton editLocationType = new JButton("Edit");
        editLocationType.setLayout(new GridLayout(1, 1));

        JTextField finalLocationType = locationType;
        editLocationName.addActionListener(e -> finalLocationType.setEditable(true));


        //Add Location ID panel to Location Info panel
        JPanel Panel4 = new JPanel();
        Panel4.setLayout(new GridLayout(3, 1));
        Panel4.add(locationTypePanel);
        Panel4.add(editLocationType);

        //*********************************************************************************

        //Insert Add button
        JButton addLocation = new JButton("Add");
        addLocation.setLayout(new GridLayout(0, 1));
        JTextField finalLocationID = locationID;

        addLocation.addActionListener(e -> {
            String text = finalLocationID.getText();
            String text1 = finalLocationName.getText();
            String text2 = finalLocationSize.getText();
            String text3 = finalLocationType.getText();
            System.out.println(text);
            System.out.println(text1);
            System.out.println(text2);
            System.out.println(text3);


        });

        //Add a delete
        JButton deleteLocation = new JButton("Delete");
        deleteLocation.setLayout(new GridLayout(0, 1));
        JTextField finalLocationID2 = locationID;
        deleteLocation.addActionListener(e -> {

            finalLocationID2.setText("");
            finalLocationName.setText("");
            finalLocationSize.setText("");
            finalLocationType.setText("");
//            refreshJTable(model);
        });

        frame.setLayout(new GridLayout(2, 4));
        frame.add(Panel1);
        frame.add(Panel2);
        frame.add(Panel3);
        frame.add(Panel4);
        frame.add(createLocationListPanel());
        frame.add(addLocation);
        frame.add(deleteLocation);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Display Four");
        frame.pack();
        frame.setVisible(true);
        
        this.refreshJTable(model);
    }

    /**
     * Creates the location list panel
     *
     * @return JPanel
     */

    private JPanel createLocationListPanel() {
        Border locationListBorder = BorderFactory.createTitledBorder("Location List");
        JPanel locationListPanel = new JPanel();
        locationListPanel.setLayout(new GridLayout(0, 1));
        locationListPanel.setBorder(locationListBorder);

        model.addColumn("Location ID");
        model.addColumn("Location Name");
        model.addColumn("Location Size");
        model.addColumn("Location Type");

        table.addMouseListener(this);

        JScrollPane pane = new JScrollPane(table);
        locationListPanel.add(pane);
        return locationListPanel;
    }

    /**
     * Creates the location list panel
     *
     * @return JPanel
     */

    private void refreshJTable(DefaultTableModel model) throws SQLException {
        String selectStmt = "SELECT * FROM Location";
        Statement stmt;

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        try {
            stmt = m_dbConn.createStatement();

            try {
                ResultSet rs = stmt.executeQuery(selectStmt);
                while (rs.next()) {
                    int locationID = rs.getInt(1);
                    String locationName = rs.getString(2);
                    String locationSize = rs.getString(3);
                    String locationType = rs.getString(4);

                    model.addRow(new Object[]{locationID, locationName, locationSize, locationType});
                }
            } catch (SQLException ex) {
                Logger.getLogger(DisplayFour.class.getName()).log(Level.SEVERE, null, ex);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates the location list panel
     *
     * @param args String[]
     * @throws SQLException 
     */

    public static void main(String[] args) throws SQLException {
        new DisplayFour();
    }

    /**
     * Creates the location list panel
     *
     * @throws SQLException SQLException
     */
    @Override
    public void insert() throws SQLException {

        PreparedStatement ps = m_dbConn.prepareStatement("INSERT INTO" + LOCATION_TABLE +
                "(LocationID, LocationName, LocationSize, LocationType) VALUES (?, ?, ?, ?)");
        ps.setString(1, "Location ID");
        ps.setString(2, "Location Name");
        ps.setString(3, "Location Size");
        ps.setString(4, "Location Type");
        ps.executeUpdate();


    }

    /**
     * Creates the location list panel
     *
     * @throws SQLException SQLException
     */

    @Override
    public void update() throws SQLException {

        PreparedStatement ps = m_dbConn.prepareStatement("UPDATE Location SET LocationID = ?, LocationName = ?, LocationSize = ?, LocationType = ?");
        ps.setString(1, "Location ID");
        ps.setString(2, "Location Name");
        ps.setString(3, "Location Size");
        ps.setString(4, "Location Type");
        ps.executeUpdate();

    }

    /**
     * Creates the location list panel
     *
     * @throws SQLException SQLException
     */
    @Override
    public void delete() throws SQLException {

        PreparedStatement ps = m_dbConn.prepareStatement("DELETE FROM Location WHERE LocationID = ?");
        ps.setString(1, "Location ID");
        ps.executeUpdate();
    }

    /**
     * Creates the location list panel
     *
     * @param e the event to be processed by the mouse listener
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        int locationID = Integer.parseInt(model.getValueAt(row, 0).toString());
        String locationName = model.getValueAt(row, 1).toString();
        String locationSize = model.getValueAt(row, 2).toString();
        String locationType = model.getValueAt(row, 3).toString();

        this.locationID.setText(String.valueOf(locationID));
        this.locationName.setText(locationName);
        this.locationSize.setText(locationSize);
        this.locationType.setText(locationType);
    }

    /**
     * Creates the location list panel
     *
     * @param e the event to be processed by the mouse listener
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Creates the location list panel
     *
     * @param e the event to be processed by the mouse listener
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Creates the location list panel
     *
     * @param e the event to be processed by the mouse listener
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Creates the location list panel
     *
     * @param e the event to be processed by the mouse listener
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}