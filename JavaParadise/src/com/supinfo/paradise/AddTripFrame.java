package com.supinfo.paradise;

import com.supinfo.paradise.dao.DaoFactory;
import com.supinfo.paradise.dao.PlaceDao;
import com.supinfo.paradise.model.Place;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class AddTripFrame extends JFrame {

    private JComboBox<Place> departureComboBox;
    private JComboBox<Place> destinationComboBox;
    private JTextField priceField;
    private JButton tripSubmit;
    private PlaceDao placeDao;

    public AddTripFrame() {
        placeDao = DaoFactory.getPlaceDao();
        init();
    }

    private void init() {
        List<Place> places = placeDao.findAllPlaces();
        departureComboBox = new JComboBox<>(places.toArray(new Place[places.size()]));
        destinationComboBox = new JComboBox<>(places.toArray(new Place[places.size()]));
        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(60, (int) priceField.getPreferredSize().getHeight()));
        JLabel euroLabel = new JLabel("€");
        tripSubmit = new JButton("Add trip");

        JPanel rootPane = new JPanel();
        rootPane.add(departureComboBox);
        rootPane.add(destinationComboBox);
        rootPane.add(priceField);
        rootPane.add(euroLabel);
        rootPane.add(tripSubmit);

        this.setTitle("Add trip");
        this.setSize(260, 100);
        this.setMinimumSize(new Dimension(260, 100));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(rootPane);
        this.setVisible(true);
    }
}
