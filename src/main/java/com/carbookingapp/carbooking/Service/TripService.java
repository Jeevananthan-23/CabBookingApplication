package com.carbookingapp.carbooking.Service;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Repository.TripRepository;

public class TripService extends Thread {
  private String name;
  private String driverName;
  private Location source;
  private Location destination;
  private static TripRepository tripRepo;

  @Override
  public void run() {
    chooseRider(name, driverName);
  }

  public TripService(String name, Location source, Location destination, String driverName) {
    this.name = name;
    this.source = source;
    this.destination = destination;
    this.driverName = driverName;
    tripRepo = new TripRepository();
  }

  public void chooseRider(String username, String drivername) {
    try {
      tripRepo.chooseRider(username, drivername, source, destination);
    } catch (NoRideFound e) {
      System.out.println(e.getMessage());
    }
  }
}
