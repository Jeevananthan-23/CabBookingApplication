package com.carbookingapp.carbooking.Repository;

import com.carbookingapp.carbooking.Models.User;
import java.util.*;

public class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User users) {
        this.users.add(users);
    }

}
