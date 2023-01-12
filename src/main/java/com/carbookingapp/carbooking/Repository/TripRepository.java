package com.carbookingapp.carbooking.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Service.DriverService;

public class TripRepository {
  private final Object lock = new Object();
  private static Map<Driver, String> rider = new ConcurrentHashMap<>();
  private int userSearchRadius = 5;
  private static List<Driver> drivers = DriverService.getDrivers();

  public Map<Driver, String> getRider() {
    return rider;
  }

  public List<Driver> findRide(String name, Location source, Location destination) throws NoRideFound {
    List<Driver> result = new ArrayList<>();
    synchronized (lock) {
      for (Driver cab : drivers) {
        if (cab.getCurrentlocation().distance(source) <= userSearchRadius && !rider.containsKey(cab)) {
          result.add(cab);
          rider.put(cab, name);
        }
      }
    }

    try {
      if (!rider.containsValue(name)) {
        throw new NoRideFound("No ride found for: " + name + " 🙆");
      }
    } catch (NoRideFound e) {
      System.out.println("========================");
      System.out.println(e.getMessage());
      System.out.println("========================");
    }

    return result;
  }

  public void chooseRider(String username, String drivername, Location source, Location destination) {
    List<Driver> driversList = new LinkedList<>();
    driversList = findRide(username, source, destination);
    if (!driversList.isEmpty()) {
      boolean allocedDriverName = false;
      synchronized (lock) {
        allocedDriverName = driversList.stream().anyMatch(x -> x.getName().equals(drivername));
        if (allocedDriverName) {
          System.out.println("========================");
          System.out.println("Hello " + username + ", your booking on " + drivername + " successfull 🙂");
        } else {
          System.out.println("========================");
          System.out.println("Sorry, " + username + " " + drivername + " not available 😒");
          System.out.println("Instance you can book " + driversList.get(0).getName() + " 👍");
        }
      }
    }
  }
}

// static Map<Driver, String> rider = new HashMap<>();
// private int userSearchRadius = 5;
// private List<Driver> drivers = DriverRepository.getDrivers();

// public static Map<Driver, String> getRider() {
// return rider;
// }

// public List<Driver> findRide(String name, Location source, Location
// destination) throws NoRideFound {
// List<Driver> result = new ArrayList<>();
// for (Driver cab : drivers) {
// if (cab.getCurrentlocation().distance(source) <= userSearchRadius &&
// !rider.containsKey(cab)) {
// result.add(cab);
// rider.put(cab, name);
// }
// }
// try {
// if (!rider.containsValue(name)) {
// throw new NoRideFound("No Ride found");
// }
// } catch (NoRideFound e) {
// System.out.println(e.getMessage());
// }
// return result;
// }

// public synchronized void chooseRider(String username, String drivername,
// Location source, Location destination) {
// List<Driver> driversList = new LinkedList<>();
// driversList = findRide(username, source, destination);
// if (!driversList.isEmpty()) {
// final boolean allocedDriverName = driversList.stream().anyMatch(x ->
// x.getName().equals(drivername));
// if (allocedDriverName) {
// System.out.println("Hello " + username + " your booking on " + drivername + "
// successfull");
// }
// else {
// System.out.println("Sorry, " + username + " " + drivername + " not
// available");
// System.out.println("Instance you can book " + driversList.get(0).getName());
// }
// }
// }
// }