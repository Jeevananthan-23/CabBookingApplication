package com.carbookingapp.carbooking.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Service.DriverService;

public class TripRepository {
  private static Map<User, List<Driver>> rider = new HashMap<>();
  public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 5.0;
  private static List<Driver> drivers = DriverService.getDrivers();

  public Map<User, List<Driver>> getRider() {
    return rider;
  }

  public List<Driver> findRide(User name, Location source) {
    List<Driver> result = new ArrayList<>();
    for (Driver cab : drivers) {
      if ((cab.getCurrentlocation().distance(source) <= MAX_ALLOWED_TRIP_MATCHING_DISTANCE)
          && !rider.containsKey(name)) {
        result.add(cab);
        rider.put(name, result);
      }
    }
    return result;
  }

  public synchronized void chooseRider(User username, Driver driver, Location source, Location destination)
      throws NoRideFound {
    List<Driver> driversList;
    driversList = findRide(username, source);
    if (!driversList.isEmpty()) {
      AtomicBoolean isDriverNameAllocated = new AtomicBoolean(false);
      isDriverNameAllocated.set(driversList.stream().anyMatch(x -> {
        return x.getName().equals(driver.getName());
      }));
      if (isDriverNameAllocated.get()) {
        System.out.println("========================================");
        System.out.println("Hello " + username.getName() + ", your booking on " + driver.getName() + " successful 🙂");
        System.out.println("Your location: " + source + " Cab location: " + driver.getCurrentlocation()
            + " Your Destination location: " + destination + "\n" +
            " Distance b/w you and cab: " + driver.getCurrentlocation().calculateDistanceInKilometer(source)
            + "km ETA in mins: " + driver.getCurrentlocation().calculateETAInMins(source) + "\n" +
            " Distance b/w your source and destination: " + source.calculateDistanceInKilometer(destination)
            + "km ETA in mins: " + source.calculateETAInMins(destination));
        System.out.println("========================================");
        rider.get(username).clear();
        rider.get(username).add(driver);
        drivers.remove(rider.get(username).get(0));
      } else {
        System.out.println("Sorry, " + username.getName() + " " + driver.getName() + " not available 😒");
        System.out.println("Instance you can book " + driversList.get(0).getName() + " 👍");
      }
    } else {
      throw new NoRideFound("No ride found for: " + username.getName() + " 🙆");
    }
  }
}