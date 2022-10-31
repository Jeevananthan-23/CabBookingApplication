package com.carbookingapp.carbooking.Service;


import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Repository.TripRepository;

public class TripService extends Thread {
  private String name;
  private Location source;
  private Location destination;
  private static TripRepository tripRepo;

  @Override
  public void run(){
    tripRepo.findRide(name, source, destination);
  }
  public TripService(String name, Location source, Location destination) {
    this.name=name;
    this.source=source;
    this.destination=destination;
    tripRepo = new TripRepository();
  }
    
  public TripService() {
      tripRepo = new TripRepository();
  }

    public String chooseRider(String username, Driver drivername){
      return tripRepo.chooseRider(username, drivername);
       }
}
