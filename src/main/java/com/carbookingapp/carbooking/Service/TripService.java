package com.carbookingapp.carbooking.Service;

import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Repository.TripRepository;

public class TripService extends Thread {
  private User name;
  private Driver driverName;
  private Location source;
  private Location destination;
  private static TripRepository tripRepo;

  @Override
  public void run() {
    chooseRider(name, driverName);
  }

  public TripService(User name, Location source, Location destination, Driver driverName) {
    this.name = name;
    this.source = source;
    this.destination = destination;
    this.driverName = driverName;
    tripRepo = new TripRepository();
  }

  public void chooseRider(User username, Driver drivername) {
    tripRepo.chooseRider(username, drivername, source, destination);
  }
}
