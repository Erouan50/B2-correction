package com.supinfo.paradise.model;

import java.math.BigDecimal;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Trip {

    private Long id;
    private Place departure;
    private Place destination;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getDeparture() {
        return departure;
    }

    public void setDeparture(Place departure) {
        this.departure = departure;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (departure != null ? !departure.equals(trip.departure) : trip.departure != null) return false;
        if (destination != null ? !destination.equals(trip.destination) : trip.destination != null) return false;
        if (id != null ? !id.equals(trip.id) : trip.id != null) return false;
        if (price != null ? !price.equals(trip.price) : trip.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
