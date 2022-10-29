package com.carbookingapp.carbooking.Repository;
import com.carbookingapp.carbooking.Models.User;
import java.util.*;

public class UserRepository {
    List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }
    public List<User> getUser() {
        return users;
    }

    public void setUser(List<User> user) {
        this.users = user;
    }

  public void addUser(User users){
    this.users.add(users);
  }

    
}
