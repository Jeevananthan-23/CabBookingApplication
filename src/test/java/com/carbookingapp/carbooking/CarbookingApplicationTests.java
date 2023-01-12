package com.carbookingapp.carbooking;

import java.util.List;

import com.carbookingapp.carbooking.Repository.DriverRepository;
import org.junit.jupiter.api.Test;

import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Gender;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Models.Vehicel;
import com.carbookingapp.carbooking.Repository.TripRepository;
import com.carbookingapp.carbooking.Service.DriverService;
import com.carbookingapp.carbooking.Service.UserService;

import static org.junit.jupiter.api.Assertions.*;

class CarbookingApplicationTests {

	@Test
	public void testChooseRider() throws Exception {

		UserService uService = new UserService();

		// Onboard 3 users.
		uService.addUser(new User("Abhishek", Gender.M, 23));
		uService.addUser(new User("Rahul", Gender.M, 29));
		uService.addUser(new User("Nandini", Gender.F, 22));

		// Onboard 3 drivers to the application.
		Driver driver = new Driver("Driver1", Gender.M, 22, new Vehicel("Swift", "KA-01-12345"),
				new Location(10.0, 1.0));
		Driver driver2 = new Driver("Driver2", Gender.M, 29, new Vehicel("Swift", "KA-01-12345"),
				new Location(11.0, 10.0));
		Driver driver3 = new Driver("Driver3", Gender.M, 24, new Vehicel("Swift", "KA-01-12345"),
				new Location(5.0, 3.0));
		DriverService dService = new DriverService();
		dService.addDriver(driver);
		dService.addDriver(driver2);
		dService.addDriver(driver3);

		// Create a new trip repository.
		TripRepository repository = new TripRepository();
		// User trying to get a ride.
		/*
		 * List<Driver> abiDrivers= repository.findRide("Abhishek", new Location(0, 0),
		 * new Location(20, 1));
		 * List<Driver> rahulDrivers= repository.findRide("Rahul", new Location(10, 0),
		 * new Location(15, 3));
		 * List<Driver> NandiniDrivers=repository.findRide("Nandini", new Location(15,
		 * 6), new Location(20, 4));
		 */

		// User trying to book a ride.
		repository.chooseRider("Abhishek", "Driver1", new Location(10.0, .0), new Location(20, 1));
		assertEquals(repository.getRider().values().toString(), "[Abhishek]");

		repository.chooseRider("Rahul", "Driver1", new Location(10, 0), new Location(15, 3));
		assertNotEquals(repository.getRider().values().toString(), "[Rahul]");

		repository.chooseRider("Nandini", "Driver1", new Location(15, 6), new Location(20, 4));
		assertNotEquals(repository.getRider().values().toString(), "[Nandini]");
	}

	@Test
	public void testFindRide() throws Exception {

		UserService uService = new UserService();

		// Onboard 3 users.
		uService.addUser(new User("Abhishek", Gender.M, 23));
		uService.addUser(new User("Rahul", Gender.M, 29));
		uService.addUser(new User("Nandini", Gender.F, 22));

		// Onboard 3 drivers to the application.
		Driver driver = new Driver("Driver1", Gender.M, 22, new Vehicel("Swift", "KA-01-12345"),
				new Location(10.0, 1.0));
		Driver driver2 = new Driver("Driver2", Gender.M, 29, new Vehicel("Swift", "KA-01-12345"),
				new Location(11.0, 10.0));
		Driver driver3 = new Driver("Driver3", Gender.M, 24, new Vehicel("Swift", "KA-01-12345"),
				new Location(5.0, 3.0));
		DriverService dService = new DriverService();
		dService.addDriver(driver);
		dService.addDriver(driver2);
		dService.addDriver(driver3);

		// Create a new trip repository.
		TripRepository repository = new TripRepository();
		// User trying to get a ride.
		List<Driver> ride1 = repository.findRide("Abhishek", new Location(0, 0), new Location(20, 1));
		assertTrue(ride1.isEmpty());

		List<Driver> ride2 = repository.findRide("Rahul", new Location(10, 0), new Location(15, 3));
		assertEquals(ride2.get(0).getName(), "Driver1");

		List<Driver> ride3 = repository.findRide("Nandini", new Location(15, 6), new Location(20, 4));
		assertTrue(ride3.isEmpty());
	}

}
