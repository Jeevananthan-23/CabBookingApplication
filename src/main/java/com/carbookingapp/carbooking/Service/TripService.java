package com.carbookingapp.carbooking.Service;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Repository.TripRepository;

import java.util.concurrent.Callable;

public class TripService implements Callable<Integer> {
  private User name;
  private Driver driverName;
  private Location source;
  private Location destination;
  private static TripRepository tripRepo;

  @Override
  public Integer call() throws Exception {
    chooseRider(name, driverName);
    return 0;
  }

  public TripService(User name, Location source, Location destination, Driver driverName) {
    this.name = name;
    this.source = source;
    this.destination = destination;
    this.driverName = driverName;
    tripRepo = new TripRepository();
  }

  private void chooseRider(User username, Driver drivername) {
    try {
      tripRepo.chooseRider(username, drivername, source, destination);
    } catch (NoRideFound e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
