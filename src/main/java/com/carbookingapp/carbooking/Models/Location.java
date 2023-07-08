package com.carbookingapp.carbooking.Models;

public class Location {

  public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
  public final static double AVERAGE_SPEED_OF_CAR_KM = 35;
  public Double x;
  public Double y;

  public Location(double d, double e) {
    this.x = d;
    this.y = e;
  }

  // Euclidean distance formula
  public double distance(Location location2) {
    return Math.sqrt(Math.pow(this.x - location2.x, 2) + Math.pow(this.y - location2.y, 2));
  }

  // Haversine formula
  public int calculateDistanceInKilometer(Location location2) {

    double latDistance = Math.toRadians(this.x - location2.x);
    double lngDistance = Math.toRadians(this.y - location2.y);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(this.x)) * Math.cos(Math.toRadians(location2.y))
            * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
  }

  public int calculateETAInMins(Location location) {
    return (int) ((calculateDistanceInKilometer(location) * 60) / AVERAGE_SPEED_OF_CAR_KM);
  }
  /*
   * avg. speed with car in city 35km/h
   * avg. speed on foot 5km/h
   * avg. speed with bike in city 20km/h
   * avg. speed with public transportation 30km/h
   */
  // eta_in_minutes = (distance_in_km * 60) / constant_estimated_speed_in_km_per_h

  @Override
  public String toString() {
    return "(Lat: " + x + ",Lon: " + y + ")";
  }

}