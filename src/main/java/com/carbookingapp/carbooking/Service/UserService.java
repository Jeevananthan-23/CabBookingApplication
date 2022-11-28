package com.carbookingapp.carbooking.Service;

import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Repository.UserRepository;

public class UserService {
    private UserRepository ur;

    public UserService() {
        ur = new UserRepository();
    }

    public User addUser(User user) {
        ur.addUser(user);
        return user;
    }
}
