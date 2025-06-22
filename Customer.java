package com.rentalsystem;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String id;

    @CsvBindByName
    private String phone;

    @CsvBindByName
    private String address;

    // Default constructor
    public Customer() { }

    // Parameterized constructor
    public Customer(String name, String id, String phone, String address) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Address: " + address;
    }
}
