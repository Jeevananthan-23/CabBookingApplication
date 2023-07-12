package com.carbookingapp.carbooking.Models;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Location {

  public static final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
  public static final double AVERAGE_SPEED_OF_CAR_KM = 35;
  public Double x;
  public Double y;

  public Location(double lat, double lon) {
    this.x = lat;
    this.y = lon;
  }

  // Euclidean distance formula
  public double distance(Location location2) {
    return Math.sqrt(Math.pow(this.x - location2.x, 2) + Math.pow(this.y - location2.y, 2));
  }

  // Haversine formula
  public long calculateDistanceInKilometer(Location location2) {

    double latDistance = Math.toRadians(this.x - location2.x);
    double lngDistance = Math.toRadians(this.y - location2.y);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(this.x)) * Math.cos(Math.toRadians(location2.y))
            * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c);
  }

  /*
   * avg. speed with car in city 35km/h
   * avg. speed on foot 5km/h
   * avg. speed with bike in city 20km/h
   * avg. speed with public transportation 30km/h
   */
  // eta_in_minutes = (distance_in_km * 60) / constant_estimated_speed_in_km_per_h
  public double calculateETAInMinutes(Location location) {
    return  (calculateDistanceInKilometer(location) * 60) / AVERAGE_SPEED_OF_CAR_KM;
  }

  @Override
  public String toString() {
    return "(Lat: " + x + ", Lon: " + y + ")";
  }

}