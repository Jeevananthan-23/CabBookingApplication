package com.carbookingapp.carbooking.Exceptions;

public class NoRideFound extends RuntimeException {
    public NoRideFound(String str) {
        super(str);
    }
}