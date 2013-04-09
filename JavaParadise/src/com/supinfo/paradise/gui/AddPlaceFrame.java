package com.supinfo.paradise.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class AddPlaceFrame extends JFrame {

    private JTextField placeName;
    private JButton placeSubmit;

    public AddPlaceFrame() {
        init();
    }

    private void init() {
        placeName = new JTextField();
        placeName.setPreferredSize(new Dimension(150, (int) placeName.getPreferredSize().getHeight()));
        placeSubmit = new JButton("Add place");

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
