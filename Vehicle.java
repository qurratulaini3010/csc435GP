package com.rentalsystem;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @CsvBindByName
    private String model;

    @CsvBindByName
    private String plateNumber;

    @CsvBindByName
    private double pricePerDay;

    @CsvBindByName
    private boolean available;

    @CsvBindByName
    public String vehicleType;

    // Default constructor
    public Vehicle() {
        this.available = true; // default to available
        this.vehicleType = "Generic Vehicle";
    }

    // Constructor with parameters
    public Vehicle(String model, String plateNumber, double pricePerDay) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.pricePerDay = pricePerDay;
        this.available = true;
        this.vehicleType = "Generic Vehicle";
    }

    // Getters
    public String getModel() {
        return model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    // Setters
    public void setModel(String model) {
        this.model = model;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s: %s (%s) - RM%.2f/day - %s", 
            vehicleType, model, plateNumber, pricePerDay, 
            available ? "Available" : "Rented");
    }

    // Method to check availability and display price per day
    public void checkAvailability() {
        if(available) {
          System.out.println(model + " (" + plateNumber + ") is available at RM" + pricePerDay + " per day.");
        } else {
          System.out.println(model + " (" + plateNumber + ") is not available.");
        }
    }

}






