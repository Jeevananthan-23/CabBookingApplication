package com.carbookingapp.carbooking.Repository;

import java.util.*;
import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Service.DriverService;

public class TripRepository {
  private final Object lock = new Object();
  private static Map<Driver, String> rider = new HashMap<>();
  public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 5.0;
  private static List<Driver> drivers = DriverService.getDrivers();

  public Map<Driver, String> getRider() {
    return rider;
  }

  public List<Driver> findRide(String name, Location source, Location destination) throws NoRideFound {
    List<Driver> result = new ArrayList<>();
    synchronized (lock) {
      for (Driver cab : drivers) {
        if ((cab.getCurrentlocation().distance(source) <= MAX_ALLOWED_TRIP_MATCHING_DISTANCE)
            && (!rider.containsKey(cab))) {
          result.add(cab);
          rider.put(cab, name);
        }
      }
    }
    if (result.isEmpty()) {
      throw new NoRideFound("No ride found for: " + name + " 🙆");
    }
    return result;
  }

  public void chooseRider(String username, String drivername, Location source, Location destination) {
    List<Driver> driversList;
    driversList = findRide(username, source, destination);
    if (!driversList.isEmpty()) {
      boolean allocedDriverName = false;
      allocedDriverName = driversList.stream().anyMatch(x -> x.getName().equals(drivername));
      if (allocedDriverName) {
        System.out.println("Hello " + username + ", your booking on " + drivername + " successful 🙂");
      } else {
        System.out.println("Sorry, " + username + " " + drivername + " not available 😒");
        System.out.println("Instance you can book " + driversList.get(0).getName() + " 👍");
      }
    }
  }
}