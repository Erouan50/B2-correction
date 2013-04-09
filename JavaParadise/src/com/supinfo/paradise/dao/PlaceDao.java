package com.supinfo.paradise.dao;

import com.supinfo.paradise.model.Place;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public interface PlaceDao {

    Long createPlace(Place place);

    Place findPlaceById(Long id);

    boolean updatePlace(Place place);

    boolean removePlace(Place place);
}