import javax.swing.border.Border;
import java.awt.*;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayFour {

    public static void main(String[] args) {
        new DisplayFour();
    }

    public DisplayFour()
    {
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
        });

        frame.setLayout(new GridLayout(2, 4));
        frame.add(Panel1);
        frame.add(Panel2);
        frame.add(Panel3);
        frame.add(Panel4);
        frame.add(addLocation);
        frame.add(deleteLocation);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Display Four");
        frame.pack();
        frame.setVisible(true);


    }



}