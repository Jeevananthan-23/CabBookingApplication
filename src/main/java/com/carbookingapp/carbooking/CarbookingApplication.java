package com.carbookingapp.carbooking;


import com.carbookingapp.carbooking.Models.Driver;
import com.carbookingapp.carbooking.Models.Gender;
import com.carbookingapp.carbooking.Models.Location;
import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Models.Vehicel;
import com.carbookingapp.carbooking.Repository.DriverRepository;
import com.carbookingapp.carbooking.Repository.UserRepository;
import com.carbookingapp.carbooking.Service.DriverService;
import com.carbookingapp.carbooking.Service.UserService;

public class CarbookingApplication {

	public static void main(String[] args) {
		
		UserRepository ur= new UserRepository();
		User user=new User("Abhishek", Gender.M, 23);
		User user2=new User("Rahul", Gender.M,29);
		User user3= new User("Nandini",Gender.F, 22);
		UserService.addUser(user,ur);
		UserService.addUser(user2, ur);
		UserService.addUser(user3, ur);
		Driver driver= new Driver("Driver1", Gender.M, 22, new Vehicel("Swift", "KA-01-12345"),new Location(10.0,1.0));
		Driver driver2= new Driver("Driver2", Gender.M, 29, new Vehicel("Swift", "KA-01-12345"),new Location(11.0,10.0));
		Driver driver3= new Driver("Driver3", Gender.M, 24, new Vehicel("Swift", "KA-01-12345"),new Location(5.0,3.0));
		DriverRepository dr = new DriverRepository();
		DriverService.addDriver(driver,dr);
		DriverService.addDriver(driver2, dr);
		DriverService.addDriver(driver3, dr);
		System.out.println(user);
		System.out.println(driver);
		DriverService driverservice= new DriverService(user.getName(), new Location(0.0,0.0),new Location(20.0,1.0));
		DriverService driverservice2= new DriverService(user2.getName() ,new Location(10.0,0.0),new Location(15.0,3.0));
		DriverService driverservice3= new DriverService(user3.getName(), new Location(10.0,6.0), new Location(20.0,4.0));
		driverservice.start();
		driverservice2.start();
		driverservice3.start();
	}

}
