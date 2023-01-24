package com.carbookingapp.carbooking.Models;

public class Location {
  public Double x;
  public Double y;

  public Location(double d, double e) {
    this.x = d;
    this.y = e;
  }

  // Euclidean distance formula
  public Double distance(Location location2) {
    return Math.sqrt(Math.pow(this.x - location2.x, 2) + Math.pow(this.y - location2.y, 2));
  }

  @Override
  public String toString() {
    return "(Lat: " + x + ",Lon: " + y + ")";
  }

}