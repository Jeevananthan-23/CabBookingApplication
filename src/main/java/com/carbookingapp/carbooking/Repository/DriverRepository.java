package com.carbookingapp.carbooking.Repository;

import java.util.ArrayList;
import java.util.List;

import com.carbookingapp.carbooking.Models.Driver;
public class DriverRepository {
    List<Driver> drivers;

    public DriverRepository() {
        this.drivers = new ArrayList<>();
    }
     
    public void addDriver(Driver driver){
        this.drivers.add(driver);
    }
}
