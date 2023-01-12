package com.carbookingapp.carbooking.Repository;

import java.util.ArrayList;
import java.util.List;

import com.carbookingapp.carbooking.Models.Driver;

public class DriverRepository {
    public List<Driver> drivers;

    public DriverRepository() {
        drivers = new ArrayList<>();
    }

    public void addDrivers(Driver driver) {
        drivers.add(driver);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

}
