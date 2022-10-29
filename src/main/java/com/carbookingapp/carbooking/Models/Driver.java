package com.carbookingapp.carbooking.Models;



public class Driver {
    private String name;
    private Gender gender;
    private int age;
    private Vehicel car;
    private Location currentlocation;

  
    public Driver(String name, Gender gender, int age, Vehicel car, Location currentlocation) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.car = car;
        this.currentlocation = currentlocation;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Vehicel getCar() {
        return car;
    }
    public void setCar(Vehicel car) {
        this.car = car;
    }
    public Location getCurrentlocation() {
        return currentlocation;
    }
    public void setCurrentlocation(Location currentlocation) {
        this.currentlocation = currentlocation;
    }
@Override
public String toString(){
    return "{ Drivername='" + name + '\'' +
    ", gender=" + gender +
    ", age=" + age +
    ",Vehicel="+car+
    ",distance="+currentlocation+
    '}';
}

}
