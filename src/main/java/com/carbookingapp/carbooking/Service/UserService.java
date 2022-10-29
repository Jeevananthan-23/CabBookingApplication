package com.carbookingapp.carbooking.Service;

import com.carbookingapp.carbooking.Models.User;
import com.carbookingapp.carbooking.Repository.UserRepository;

public class UserService {
    

    public static void addUser(User user, UserRepository ur) {
        ur.addUser(user);
    }
}
