package com.carbookingapp.carbooking.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Service.DriverService;
import org.assertj.core.util.VisibleForTesting;

public class TripRepository {
  private static final Map<User, List<Driver>> rider = new HashMap<>();
  public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 5.0;
  private static final List<Driver> drivers = DriverService.getDrivers();

  @VisibleForTesting
  public Map<User, List<Driver>> getRider() {
    return rider;
  }

  public List<Driver> findRide(User name, Location source) {
    List<Driver> result = new ArrayList<>();
    for (Driver cab : drivers) {
      if (cab.getCurrentlocation().distance(source) <= MAX_ALLOWED_TRIP_MATCHING_DISTANCE) {
        result.add(cab);
        rider.put(name, result);
      }
    }
    return result;
  }

  public synchronized void chooseRider(User user, Driver driver, Location source, Location destination)
      throws NoRideFound {
    List<Driver> driversList;
    driversList = findRide(user, source);
    if (!driversList.isEmpty()) {
      AtomicBoolean isDriverNameAllocated = new AtomicBoolean(false);
      isDriverNameAllocated.set(driversList.stream().anyMatch(x -> x.getName().equals(driver.getName())));
      if (isDriverNameAllocated.get()) {
        System.out.println("========================================");
        System.out.println("Hello " + user.getName() + ", your booking on " + driver.getName() + " successful 🙂");
        System.out.println("Your location: " + source + " Cab location: " + driver.getCurrentlocation()
            + " Your Destination location: " + destination + "\n" +
            " Distance b/w you and cab: " + driver.getCurrentlocation().calculateDistanceInKilometer(source)
            + "km ETA in mins: " + driver.getCurrentlocation().calculateETAInMinutes(source) + "\n" +
            " Distance b/w your source and destination: " + source.calculateDistanceInKilometer(destination)
            + "km ETA in mins: " + source.calculateETAInMinutes(destination));
        System.out.println("========================================");
        rider.get(user).clear();
        rider.get(user).add(driver);
        drivers.remove(rider.get(user).get(0));
      } else {
        System.out.println("========================================");
        System.out.println("Sorry, " + user.getName() + " " + driver.getName() + " not available 😒");
        System.out.println("Instance you can book " + driversList.get(0).getName() + " 👍");
        System.out.println("========================================");
      }
    } else {
      throw new NoRideFound("No ride found for: " + user.getName() + " 🙆");
    }
  }
}