package com.carbookingapp.carbooking.Service;

import java.util.List;

import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Repository.UserRepository;

public class UserService {
    private  static UserRepository ur;

    public UserService() {
        ur = new UserRepository();
    }

    public User addUser(User user) {
        ur.addUser(user);
        return user;
    }

    public static List<User> getUsers() {
        return ur.getUsers();
      }
}
