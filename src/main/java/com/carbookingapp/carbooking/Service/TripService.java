package com.carbookingapp.carbooking.Service;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Repository.TripRepository;

import java.util.concurrent.Callable;

public class TripService implements Callable<Integer> {
  private final User user;
  private final Driver driver;
  private final Location source;
  private final Location destination;
  private static TripRepository tripRepo;

  @Override
  public Integer call() {
    chooseRider(user, driver);
    return 0;
  }

  public TripService(User user, Location source, Location destination, Driver driver) {
    this.user = user;
    this.source = source;
    this.destination = destination;
    this.driver = driver;
    tripRepo = new TripRepository();
  }

  private void chooseRider(User user, Driver driver) throws NoRideFound{
    try {
      tripRepo.chooseRider(user, driver, source, destination);
    } catch (NoRideFound e) {
      System.out.println(e.getMessage());
    }
  }

}
