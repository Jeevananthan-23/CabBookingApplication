package com.carbookingapp.carbooking.Repository;

import java.util.*;

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

  public List<Driver> findRide(User name, Location source, Location destination) {
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

  public synchronized void chooseRider(User username, Driver drivername, Location source, Location destination) throws NoRideFound {
    List<Driver> driversList;
    driversList = findRide(username, source, destination);
    if (!driversList.isEmpty()) {
      boolean allocedDriverName = false;
      allocedDriverName = driversList.stream().anyMatch(x -> x.getName().equals(drivername.getName()));
      if (allocedDriverName) {
        System.out.println("Hello " + username.getName() + ", your booking on " + drivername.getName() + " successful 🙂");
        rider.get(username).clear();
        rider.get(username).add(drivername);
        drivers.remove(rider.get(username).get(0));
      } 
      else {
        System.out.println("Sorry, " + username.getName() + " " + drivername.getName() + " not available 😒");
        System.out.println("Instance you can book " + driversList.get(0).getName() + " 👍");
      }
    }
    else{
      throw new NoRideFound("No ride found for: " + username.getName()+ " 🙆");
    }
  }
}