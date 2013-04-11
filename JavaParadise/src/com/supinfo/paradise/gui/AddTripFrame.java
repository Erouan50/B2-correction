package com.supinfo.paradise.gui;

import com.supinfo.paradise.dao.DaoFactory;
import com.supinfo.paradise.dao.PlaceDao;
import com.supinfo.paradise.dao.TripDao;
import com.supinfo.paradise.gui.model.PlaceComboBoxModel;
import com.supinfo.paradise.model.Place;
import com.supinfo.paradise.model.Trip;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class AddTripFrame extends JFrame {

    private JComboBox<Place> departureComboBox;
    private JComboBox<Place> destinationComboBox;
    private JTextField priceField;
    private JButton tripSubmit;
    private JButton tripCancel;
    private PlaceDao placeDao;
    private TripDao tripDao;

    public AddTripFrame() {
        placeDao = DaoFactory.getPlaceDao();
        tripDao = DaoFactory.getTripDao();
        init();
    }

    private void init() {
        List<Place> places = placeDao.findAllPlaces();
        departureComboBox = new JComboBox<>(new PlaceComboBoxModel(places));
        destinationComboBox = new JComboBox<>(new PlaceComboBoxModel(places));
        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(60, (int) priceField.getPreferredSize().getHeight()));
        tripSubmit = new JButton("Add trip");
        tripCancel = new JButton("Cancel");
        initListeners();

        JPanel rootPane = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridBagLayout());

        GridBagConstraints labelConstraints = buildLabelConstraints();
        GridBagConstraints fieldConstraints = buildFieldConstraints();
        GridBagConstraints comboBoxConstraints = buildComboBoxConstraints();

        JLabel fromLabel = new JLabel("From: ");
        topPanel.add(fromLabel, labelConstraints);
        topPanel.add(departureComboBox, comboBoxConstraints);

        incrementConstraintsGridY(labelConstraints, fieldConstraints, comboBoxConstraints);

        JLabel toLabel = new JLabel("To: ");
        topPanel.add(toLabel, labelConstraints);
        topPanel.add(destinationComboBox, comboBoxConstraints);

        incrementConstraintsGridY(labelConstraints, fieldConstraints, comboBoxConstraints);

        JLabel priceLabel = new JLabel("Price: ");
        topPanel.add(priceLabel, labelConstraints);
        topPanel.add(priceField, fieldConstraints);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(tripCancel);
        bottomPanel.add(tripSubmit);

        rootPane.add(topPanel, BorderLayout.NORTH);
        rootPane.add(bottomPanel, BorderLayout.SOUTH);

        this.setTitle("Add trip");
        this.setSize(260, 170);
        this.setMinimumSize(new Dimension(260, 100));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(rootPane);
        this.setVisible(true);
    }

    private void incrementConstraintsGridY(GridBagConstraints labelConstraints, GridBagConstraints fieldConstraints, GridBagConstraints comboBoxConstraints) {
        labelConstraints.gridy++;
        comboBoxConstraints.gridy++;
        fieldConstraints.gridy++;
    }

    private GridBagConstraints buildComboBoxConstraints() {
        GridBagConstraints comboBoxConstraints = new GridBagConstraints();
        comboBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
        comboBoxConstraints.insets = new Insets(5, 5, 5, 5);
        comboBoxConstraints.weightx = 2;
        comboBoxConstraints.gridy = 0;
        return comboBoxConstraints;
    }

    private GridBagConstraints buildFieldConstraints() {
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.anchor = GridBagConstraints.WEST;
        fieldConstraints.insets = new Insets(5, 5, 5, 5);
        fieldConstraints.gridy = 0;
        return fieldConstraints;
    }

    private GridBagConstraints buildLabelConstraints() {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.EAST;
        labelConstraints.insets = new Insets(5, 5, 5, 5);
        labelConstraints.gridy = 0;
        return labelConstraints;
    }

    public void initListeners() {
        tripSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trip trip = new Trip();
                trip.setDeparture((Place) departureComboBox.getSelectedItem());
                trip.setDestination((Place) destinationComboBox.getSelectedItem());
                trip.setPrice(new BigDecimal(priceField.getText()));
                tripDao.createTrip(trip);
            }
        });
        tripCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTripFrame.this.dispose();
                System.exit(0);
            }
        });
    }
}
