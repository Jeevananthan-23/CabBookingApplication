package com.carbookingapp.carbooking.Service;

import java.util.*;

import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Repository.DriverRepository;

public class DriverService {
  private static DriverRepository driverRepo;

  public DriverService() {
    driverRepo = new DriverRepository();
  }

  public boolean addDriver(Driver driver) {
    driverRepo.addDrivers(driver);
    return true;
  }

  public static List<Driver> getDrivers() {
    return driverRepo.getDrivers();
  }

}
