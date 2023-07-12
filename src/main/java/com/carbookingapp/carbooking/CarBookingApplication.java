package com.carbookingapp.carbooking;

import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Gender;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Models.Vehicle;
import com.carbookingapp.carbooking.Service.DriverService;
import com.carbookingapp.carbooking.Service.TripService;
import com.carbookingapp.carbooking.Service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CarBookingApplication {
	public static void main(String[] args) throws InterruptedException {

		User user = new User("Abhishek", Gender.M, 23);
		User user2 = new User("Rahul", Gender.M, 29);
		User user3 = new User("Nandini", Gender.F, 22);
		UserService uService = new UserService();
		uService.addUser(user);
		uService.addUser(user2);
		uService.addUser(user3);

		Driver driver = new Driver("Driver1", Gender.M, 22, new Vehicle("Swift", "KA-01-12345"),
				new Location(10.0, 1.0));
		Driver driver2 = new Driver("Driver2", Gender.M, 29, new Vehicle("Swift", "KA-01-12345"),
				new Location(11.0, 10.0));
		Driver driver3 = new Driver("Driver3", Gender.M, 24, new Vehicle("Swift", "KA-01-12345"),
				new Location(5.0, 3.0));
		DriverService dService = new DriverService();
		dService.addDriver(driver);
		dService.addDriver(driver2);
		dService.addDriver(driver3);

		System.out.println("===============Sample User and Driver Data===============");
		System.out.println(user);
		System.out.println(driver);

		TripService trip1 = new TripService(user, new Location(10.0, 6.0), new Location(20.0, 1.0),
				driver3);
		TripService trip2 = new TripService(user2, new Location(10.0, 6.0), new Location(15.0, 3.0),
				driver);
		TripService trip3 = new TripService(user3, new Location(10.0, 6.0), new Location(20.0, 4.0),
				driver);

		List<Callable<Integer>> callables = new ArrayList<>();
		callables.add(trip1);
		callables.add(trip2);
		callables.add(trip3);

		executor(callables);

	}

	private static void executor(List<Callable<Integer>> callables) throws InterruptedException {

		ExecutorService executorService = new ThreadPoolExecutor(callables.size(), callables.size(), 0L,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		try {
			executorService.invokeAll(callables);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			executorService.shutdown();
		}

		executorService.awaitTermination(100,TimeUnit.MILLISECONDS);
	}
}
