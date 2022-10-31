package com.carbookingapp.carbooking.Repository;

import java.util.*;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;

public class TripRepository{
 static Map<Driver, String> rider= new HashMap<>();
  private int userSearchRadius=5;
  private List<Driver> drivers= DriverRepository.getDrivers();
  public static Map<Driver, String> getRider() {
    return rider;
    }  
    public synchronized List<Driver> findRide(String name, Location source, Location destination)throws NoRideFound{
      List<Driver> result = new ArrayList<>();
      for (Driver cab : drivers) {
        if (cab.getCurrentlocation().distance(source) <=userSearchRadius && !rider.containsKey(cab)) {
          result.add(cab);
          rider.put(cab,name);
          System.out.println(name+" booked "+cab.getName());
        }
      }
      try {
        if(!rider.containsValue(name)){
          throw new NoRideFound("No Ride found");
        }
      } catch (NoRideFound e) {
       System.out.println(e.getMessage());
      }
      return result;
      }
      public String chooseRider(String username, Driver drivername){
        if(getRider().containsValue(username)&& getRider().containsKey(drivername))
        System.out.println(username+" booked "+drivername);
     return null;
         }
}