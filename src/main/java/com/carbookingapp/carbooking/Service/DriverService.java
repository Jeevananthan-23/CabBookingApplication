package com.carbookingapp.carbooking.Service;

import java.util.*;

import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Repository.DriverRepository;
import com.carbookingapp.carbooking.Repository.TripRepository;

public class DriverService extends Thread {
    static Map<String, Driver> drivers = new HashMap<>();
    private String name;
    private Location source;
    private Location destination;

    public static void addDriver(Driver driver, DriverRepository dr){
        dr.addDriver(driver);
        drivers.put(driver.getName(), driver);
    }

    public DriverService(String name, Location source, Location destination) {
      this.name=name;
      this.source=source;
      this.destination=destination;
    }
    @Override
    public void run(){
      TripRepository.findRide(name, source, destination, drivers);
    }
    public String chooseRider(String username, String drivername){
     if(TripRepository.getRider().containsKey(username)&& TripRepository.getRider().containsValue(drivername))
       return drivername;
    return null;
    }

}
