package com.supinfo.paradise;

import com.supinfo.paradise.dao.DaoFactory;
import com.supinfo.paradise.dao.PlaceDao;
import com.supinfo.paradise.dao.TripDao;
import com.supinfo.paradise.model.Place;
import com.supinfo.paradise.model.Trip;
import com.supinfo.paradise.util.ConnectionManager;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Launcher {

    private PlaceDao placeDao;
    private TripDao tripDao;

    public Launcher() {
        placeDao = DaoFactory.getPlaceDao();
        tripDao = DaoFactory.getTripDao();
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.start();
    }

    public void start() {
        System.out.println("Welcome aboard!");
        int choice;
        Scanner scanner = new Scanner(System.in);
        String menu = buildMenu();
        do {
            System.out.println(menu);
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addPlace();
                    break;
                case 2:
                    findPlace();
                    break;
                case 3:
                    editPlace();
                    break;
                case 4:
                    removePlace();
                    break;
                case 5:
                    addTrip();
                    break;
                case 6:
                    findTrip();
                    break;
                case 7:
                    removeTrip();
                    break;
                case 8:
                    quit();
                    break;
                default:
                    inputMistake();
            }
        } while (choice != 8);
        ConnectionManager.close();
    }

    private void addPlace() {
        System.out.println("Type the place name: ");
        String name = new Scanner(System.in).nextLine();
        Place place = new Place();
        place.setName(name);
        Long id = placeDao.createPlace(place);
        System.out.println("Place created successfully with ID: " + id);
    }

    private void findPlace() {
        System.out.println("Type the place Id to find: ");
        Long id = new Scanner(System.in).nextLong();
        Place place = placeDao.findPlaceById(id);
        if (place == null) {
            System.out.println("Unable to find place");
        } else {
            System.out.println("Place id: " + place.getId());
            System.out.println("Place name: " + place.getName());
        }
    }

    private void editPlace() {
        System.out.println("Type the place Id to update: ");
        Long id = new Scanner(System.in).nextLong();
        System.out.println("Type the new place name: ");
        String name = new Scanner(System.in).nextLine();
        Place place = new Place();
        place.setId(id);
        place.setName(name);
        if (placeDao.updatePlace(place)) {
            System.out.println("The place has been successfully updated");
        } else {
            System.out.println("Unable to find place with given id");
        }
    }

    private void removePlace() {
        System.out.println("Type the place Id to remove: ");
        Long id = new Scanner(System.in).nextLong();
        Place place = new Place();
        place.setId(id);
        if (placeDao.removePlace(place)) {
            System.out.println("The place has been successfully removed");
        } else {
            System.out.println("Unable to find place with given id");
        }
    }

    private void addTrip() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the id place corresponding of departure: ");
        Long idDeparture = scanner.nextLong();
        System.out.println("Type the id place corresponding of destination: ");
        Long idDestination = scanner.nextLong();
        System.out.println("Type the trip price: ");
        BigDecimal price = scanner.nextBigDecimal();

        Trip trip = new Trip();

        Place departure = new Place();
        departure.setId(idDeparture);
        trip.setDeparture(departure);

        Place destination = new Place();
        destination.setId(idDestination);
        trip.setDestination(destination);

        trip.setPrice(price);
        Long id = tripDao.createTrip(trip);
        System.out.println("Trip created successfully with id: " + id);
    }

    private void findTrip() {
        System.out.println("Type the trip Id to find: ");
        Long id = new Scanner(System.in).nextLong();
        Trip trip = tripDao.findTripById(id);
        if (trip == null) {
            System.out.println("Unable to find trip");
        } else {
            System.out.println("Trip - departure id: " + trip.getDeparture().getId());
            System.out.println("Trip - departure name: " + trip.getDeparture().getName());
            System.out.println("Trip - destination id: " + trip.getDestination().getId());
            System.out.println("Trip - destination name: " + trip.getDestination().getName());
            System.out.println("Trip - price: " + trip.getPrice());
        }
    }

    private void removeTrip() {
        System.out.println("Type the trip id to remove: ");
        Long id = new Scanner(System.in).nextLong();
        Trip trip = new Trip();
        trip.setId(id);
        if (tripDao.removeTrip(trip)) {
            System.out.println("The trip has been successfully removed");
        } else {
            System.out.println("Unable to find trip with given id: " + id);
        }
    }

    private void quit() {
        System.out.println("Bye bye!");
    }

    private void inputMistake() {
        System.out.println("Bad entry");
    }

    public String buildMenu() {
        return "Want do you want to do?" + System.lineSeparator() +
                "1 - Add a place" + System.lineSeparator() +
                "2 - Find a place" + System.lineSeparator() +
                "3 - Edit a place" + System.lineSeparator() +
                "4 - Remove a place" + System.lineSeparator() +
                "5 - Add a trip" + System.lineSeparator() +
                "6 - Find a trip" + System.lineSeparator() +
                "7 - Remove a trip" + System.lineSeparator() +
                "8 - Quit" + System.lineSeparator();
    }
}
