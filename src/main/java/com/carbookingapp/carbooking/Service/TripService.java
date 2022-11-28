package com.carbookingapp.carbooking.Service;

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

  public synchronized void chooseRider(String username, String drivername) {
    tripRepo.chooseRider(username, drivername, source, destination);
  }
}
