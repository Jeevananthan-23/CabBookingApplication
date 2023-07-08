package com.carbookingapp.carbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Gender;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Models.Vehicel;
import com.carbookingapp.carbooking.Repository.TripRepository;
import com.carbookingapp.carbooking.Service.DriverService;
import com.carbookingapp.carbooking.Service.UserService;

class CarBookingApplicationTests {
	@Test
	void testChooseRider() {

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

		List<Driver> abiDrivers = repository.findRide(UserService.getUsers().get(0), new Location(0, 0));
		List<Driver> rahulDrivers = repository.findRide(UserService.getUsers().get(1), new Location(10, 0));
		List<Driver> NandiniDrivers = repository.findRide(UserService.getUsers().get(2), new Location(15,
				6));

		// User trying to book a ride.
		repository.chooseRider(uService.getUsers().get(0), driver, new Location(10.0, .0), new Location(20, 1));
		assertEquals(repository.getRider().containsKey(uService.getUsers().get(0)), true);
		repository.chooseRider(uService.getUsers().get(1), driver, new Location(10, 0), new Location(15, 3));
		assertNotEquals(repository.getRider().containsKey(uService.getUsers().get(0)), false);
		repository.chooseRider(uService.getUsers().get(2), driver, new Location(15, 6), new Location(20, 4));
		assertEquals(repository.getRider().containsKey(uService.getUsers().get(2)), true);
	}

	@Test
	public void testFindRide() {

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
		List<Driver> ride1 = repository.findRide(UserService.getUsers().get(0), new Location(0, 0));
		assertTrue(ride1.isEmpty());

		List<Driver> ride2 = repository.findRide(UserService.getUsers().get(1), new Location(10, 0));
		assertEquals(ride2.get(0).getName(), "Driver1");

		List<Driver> ride3 = repository.findRide(UserService.getUsers().get(2), new Location(15, 6));
		assertTrue(ride3.isEmpty());
	}
}
