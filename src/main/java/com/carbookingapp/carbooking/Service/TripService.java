package com.carbookingapp.carbooking.Service;


import java.util.List;

import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Repository.TripRepository;

public class TripService extends Thread {
  private String name;
  private Location source;
  private Location destination;
  private TripRepository tripRepo;

  public TripService(String name, Location source, Location destination) {
    this.name=name;
    this.source=source;
    this.destination=destination;
    tripRepo = new TripRepository();
  }
    
  public TripService() {
      tripRepo = new TripRepository();
  }
  @Override
  public void run(){
    findRide(name, source, destination);
  }
    public String chooseRider(String username, Driver drivername){
        if(tripRepo.getRider().containsKey(username)&& tripRepo.getRider().containsValue(drivername))
          System.out.println(drivername);
       return null;
       }
     public synchronized List<Driver> findRide(String name, Location source, Location destination){
      return TripRepository.findRide(name, source, destination);
     }
}
