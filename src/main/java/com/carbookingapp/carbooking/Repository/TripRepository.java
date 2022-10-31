package com.carbookingapp.carbooking.Repository;

import java.util.*;

import com.carbookingapp.carbooking.Exceptions.NoRideFound;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Location;

public class TripRepository{
  private static Map<String, Driver> rider= new HashMap<>();
  private static int userSearchRadius=5;
 static List<Driver> driverList = DriverRepository.getDrivers();
  public Map<String, Driver> getRider() {
    return rider;
    }  
    public static synchronized List<Driver> findRide(String name, Location source, Location destination)throws NoRideFound{
      List<Driver> result = new ArrayList<>();
      for (Driver cab : driverList) {
        if (cab.getCurrentlocation().distance(source) <=userSearchRadius && !rider.containsValue(cab)) {
          result.add(cab);
          rider.put(name, cab);
          System.out.println(name+" booked "+cab.getName());
        }
      }
      try {
        if(!rider.containsKey(name)){
          throw new NoRideFound("No Ride found");
        }
      } catch (NoRideFound e) {
       System.out.println(e.getMessage());
      }
      
      return result;
      }
}