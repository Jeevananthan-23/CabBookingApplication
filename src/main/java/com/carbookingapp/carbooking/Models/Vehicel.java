package com.carbookingapp.carbooking.Models;

public class Vehicel {
    private String model;
    private String registrationnumber;

    public Vehicel(String model, String registrationnumber) {
        this.model = model;
        this.registrationnumber = registrationnumber;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
  
    public String getRegistrationnumber() {
        return registrationnumber;
    }
    public void setRegistrationnumber(String registrationnumber) {
        this.registrationnumber = registrationnumber;
    }

    @Override
public String toString(){
    return "Model: "+model+" Registrationnum: "+registrationnumber;
}
}
