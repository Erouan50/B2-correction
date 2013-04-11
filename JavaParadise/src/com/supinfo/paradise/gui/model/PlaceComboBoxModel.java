package com.supinfo.paradise.gui.model;

import com.supinfo.paradise.model.Place;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class PlaceComboBoxModel implements ComboBoxModel<Place> {

    private List<Place> places;
    private Place selectedPlace;

    public PlaceComboBoxModel(List<Place> places) {
        this.places = places;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedPlace = (Place) anItem;
    }

    @Override
    public Place getSelectedItem() {
        return selectedPlace;
    }

    @Override
    public int getSize() {
        return places.size();
    }

    @Override
    public Place getElementAt(int index) {
        return places.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
