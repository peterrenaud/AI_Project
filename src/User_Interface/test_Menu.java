package src.User_Interface;

import src.Connect.test_Geocode;
import src.Connect.StreetNode;
import src.Connect.JunctionNode;
import src.Connect.StreetFinder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test_Menu extends JFrame implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    JPanel window;
    JButton search;
    ImageIcon map_icon;
    JLabel map, start, destination, country1_l, city1_l, address1_l, country2_l, city2_l, address2_l;
    JTextField country1_tf, city1_tf, address1_tf, country2_tf, city2_tf, address2_tf;

    public test_Menu(){
        super();
        this.setTitle("Route Finder");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600,600));
        window = new JPanel();
        window.setBorder(new EmptyBorder(0,0,0,0));
        this.setContentPane(window);
        GridBagLayout gbl_window = new GridBagLayout();
        gbl_window.columnWidths = new int[] {0, 0, 50, 0, 0};
        window.setLayout(gbl_window);

        map_icon = new ImageIcon();
        map = new JLabel(map_icon);
        GridBagConstraints gbc_map = new GridBagConstraints();
        gbc_map.gridwidth = GridBagConstraints.REMAINDER;
        gbc_map.gridheight = 1;
        gbc_map.insets = new Insets(0,0,0,0);
        gbc_map.fill = GridBagConstraints.HORIZONTAL;
        gbc_map.gridx = 0;
        gbc_map.gridy = 0;
        gbc_map.ipadx = 500;
        gbc_map.ipady = 400;
        window.add(map, gbc_map);

        start = new JLabel("Start");
        GridBagConstraints gbc_start = new GridBagConstraints();
        gbc_start.gridwidth = 1;
        gbc_start.gridheight = 1;
        gbc_start.insets = new Insets(0,0,0,0);
        gbc_start.fill = GridBagConstraints.HORIZONTAL;
        gbc_start.gridx = 0;
        gbc_start.gridy = 1;
        window.add(start, gbc_start);

        // In future versions, might expand to other countries,
        // But currently only have data for streets in Ontario
        /*
        country1_l = new JLabel("Enter country.");
        GridBagConstraints gbc_country1_l = new GridBagConstraints();
        gbc_country1_l.gridwidth = 1;
        gbc_country1_l.gridheight = 1;
        gbc_country1_l.insets = new Insets(0,0,0,0);
        gbc_country1_l.fill = GridBagConstraints.HORIZONTAL;
        gbc_country1_l.gridx = 0;
        gbc_country1_l.gridy = 2;
        window.add(country1_l, gbc_country1_l); 

        country1_tf = new JTextField();
        GridBagConstraints gbc_country1_tf = new GridBagConstraints();
        gbc_country1_tf.gridwidth = 1;
        gbc_country1_tf.gridheight = 1;
        gbc_country1_tf.insets = new Insets(0,10,0,0);
        gbc_country1_tf.fill = GridBagConstraints.HORIZONTAL;
        gbc_country1_tf.gridx = 1;
        gbc_country1_tf.gridy = 2;
        gbc_country1_tf.ipadx = 100;
        window.add(country1_tf, gbc_country1_tf); 
        */

        city1_l = new JLabel("Enter city.");
        GridBagConstraints gbc_city1_l = new GridBagConstraints();
        gbc_city1_l.gridwidth = 1;
        gbc_city1_l.gridheight = 1;
        gbc_city1_l.insets = new Insets(0,0,0,0);
        gbc_city1_l.fill = GridBagConstraints.HORIZONTAL;
        gbc_city1_l.gridx = 0;
        gbc_city1_l.gridy = 3;
        window.add(city1_l, gbc_city1_l); 

        city1_tf = new JTextField();
        GridBagConstraints gbc_city1_tf = new GridBagConstraints();
        gbc_city1_tf.gridwidth = 1;
        gbc_city1_tf.gridheight = 1;
        gbc_city1_tf.insets = new Insets(0,10,0,0);
        gbc_city1_tf.fill = GridBagConstraints.HORIZONTAL;
        gbc_city1_tf.gridx = 1;
        gbc_city1_tf.gridy = 3;
        gbc_city1_tf.ipadx = 100;
        window.add(city1_tf, gbc_city1_tf); 

        address1_l = new JLabel("Enter address.");
        GridBagConstraints gbc_address1_l = new GridBagConstraints();
        gbc_address1_l.gridwidth = 1;
        gbc_address1_l.gridheight = 1;
        gbc_address1_l.insets = new Insets(0,0,0,0);
        gbc_address1_l.fill = GridBagConstraints.HORIZONTAL;
        gbc_address1_l.gridx = 0;
        gbc_address1_l.gridy = 4;
        window.add(address1_l, gbc_address1_l); 

        address1_tf = new JTextField();
        GridBagConstraints gbc_address_tf = new GridBagConstraints();
        gbc_address_tf.gridwidth = 1;
        gbc_address_tf.gridheight = 1;
        gbc_address_tf.insets = new Insets(0,10,0,0);
        gbc_address_tf.fill = GridBagConstraints.HORIZONTAL;
        gbc_address_tf.gridx = 1;
        gbc_address_tf.gridy = 4;
        window.add(address1_tf, gbc_address_tf); 

        destination = new JLabel("Destination");
        GridBagConstraints gbc_destination = new GridBagConstraints();
        gbc_destination.gridwidth = 1;
        gbc_destination.gridheight = 1;
        gbc_destination.insets = new Insets(0,0,0,0);
        gbc_destination.fill = GridBagConstraints.HORIZONTAL;
        gbc_destination.gridx = 4;
        gbc_destination.gridy = 1;
        window.add(destination,gbc_destination);

        /*
        country2_l = new JLabel("Enter country.");
        GridBagConstraints gbc_country2_l = new GridBagConstraints();
        gbc_country2_l.gridwidth = 1;
        gbc_country2_l.gridheight = 1;
        gbc_country2_l.insets = new Insets(0,0,0,0);
        gbc_country2_l.fill = GridBagConstraints.HORIZONTAL;
        gbc_country2_l.gridx = 4;
        gbc_country2_l.gridy = 2;
        window.add(country2_l, gbc_country2_l); 

        country2_tf = new JTextField();
        GridBagConstraints gbc_country2_tf = new GridBagConstraints();
        gbc_country2_tf.gridwidth = 2;
        gbc_country2_tf.gridheight = 1;
        gbc_country2_tf.insets = new Insets(0,10,0,0);
        gbc_country2_tf.fill = GridBagConstraints.HORIZONTAL;
        gbc_country2_tf.gridx = 5;
        gbc_country2_tf.gridy = 2;
        window.add(country2_tf, gbc_country2_tf); 
        */
        
        city2_l = new JLabel("Enter city.");
        GridBagConstraints gbc_city2_l = new GridBagConstraints();
        gbc_city2_l.gridwidth = 1;
        gbc_city2_l.gridheight = 1;
        gbc_city2_l.insets = new Insets(0,0,0,0);
        gbc_city2_l.fill = GridBagConstraints.HORIZONTAL;
        gbc_city2_l.gridx = 4;
        gbc_city2_l.gridy = 3;
        window.add(city2_l, gbc_city2_l); 

        city2_tf = new JTextField();
        GridBagConstraints gbc_city2_tf = new GridBagConstraints();
        gbc_city2_tf.gridwidth = 1;
        gbc_city2_tf.gridheight = 1;
        gbc_city2_tf.insets = new Insets(0,10,0,0);
        gbc_city2_tf.fill = GridBagConstraints.HORIZONTAL;
        gbc_city2_tf.gridx = 5;
        gbc_city2_tf.gridy = 3;
        gbc_city2_tf.ipadx = 100;
        window.add(city2_tf, gbc_city2_tf); 

        address2_l = new JLabel("Enter address.");
        GridBagConstraints gbc_address2_l = new GridBagConstraints();
        gbc_address2_l.gridwidth = 1;
        gbc_address2_l.gridheight = 1;
        gbc_address2_l.insets = new Insets(0,0,0,0);
        gbc_address2_l.fill = GridBagConstraints.HORIZONTAL;
        gbc_address2_l.gridx = 4;
        gbc_address2_l.gridy = 4;
        window.add(address2_l, gbc_address2_l); 

        address2_tf = new JTextField();
        GridBagConstraints gbc_address2_tf = new GridBagConstraints();
        gbc_address2_tf.gridwidth = 1;
        gbc_address2_tf.gridheight = 1;
        gbc_address2_tf.insets = new Insets(0,10,0,0);
        gbc_address2_tf.fill = GridBagConstraints.HORIZONTAL;
        gbc_address2_tf.gridx = 5;
        gbc_address2_tf.gridy = 4;
        window.add(address2_tf, gbc_address2_tf);

        search = new JButton("Search");
        GridBagConstraints gbc_search = new GridBagConstraints();
        gbc_search.gridwidth = 4;
        gbc_search.gridheight = 1;
        gbc_search.insets = new Insets(0,0,0,0);
        gbc_search.fill = GridBagConstraints.HORIZONTAL;
        gbc_search.gridx = 1;
        gbc_search.gridy = 6;
        window.add(search, gbc_search);

        search.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Search"){
            // To start off to test dijkstra, use Junctions as nodes as they have geocoordinates and are easy to
            // find connecting junctions through Road_Net_Elements
            double[] location1 = test_Geocode.Geocode("Canada", city1_tf.getText(), address1_tf.getText());
            double[] location2 = test_Geocode.Geocode("Canada", city2_tf.getText(), address2_tf.getText());
            StreetFinder finder = new StreetFinder();

            // Basic way to remove the house number from the street address
            String street1 = address1_tf.getText().split("[0-9]+ ")[1];
            String street2 = address2_tf.getText().split("[0-9]+ ")[1];

            StreetNode startNode = finder.findStreet(city1_tf.getText(), street1);
            StreetNode destinationNode = finder.findStreet(city2_tf.getText(), street2);

            System.out.println("\n\n" + startNode.toString() + "\n");
            System.out.println("\n\n" + destinationNode.toString() + "\n");

            JunctionNode startJunction = finder.findClosestJunction(startNode, location1, location2);
            JunctionNode destinationJunction = finder.findClosestJunction(destinationNode, location2, location2);

            System.out.println("\n\n" + startJunction.toString() + "\n");
            System.out.println("\n\n" + destinationJunction.toString() + "\n");
            finder.close();
            // Run the dijkstra algorithm
            // Dijkstra(startNode, endNode);
            //map_icon.setImage(test_Geocode.staticMap(location1[0], location1[1], location2[0], location2[1]));
            //map.repaint();
            
        }
        
    }
}
