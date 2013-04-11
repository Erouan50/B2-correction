package com.supinfo.paradise.gui;

import com.supinfo.paradise.dao.DaoFactory;
import com.supinfo.paradise.dao.PlaceDao;
import com.supinfo.paradise.model.Place;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class AddPlaceFrame extends JFrame {

    private JTextField placeName;
    private JButton placeSubmit;
    private PlaceDao placeDao;

    public AddPlaceFrame() {
        placeDao = DaoFactory.getPlaceDao();
        init();
    }

    private void init() {
        placeName = new JTextField();
        placeName.setPreferredSize(new Dimension(150, (int) placeName.getPreferredSize().getHeight()));
        placeSubmit = new JButton("Add place");
        placeSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Place place = new Place();
                place.setName(placeName.getText());
                placeDao.createPlace(place);
            }
        });

        JPanel rootPane = new JPanel();
        rootPane.add(placeName);
        rootPane.add(placeSubmit);

        this.setTitle("Add place");
        this.setSize(280, 70);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(rootPane);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
