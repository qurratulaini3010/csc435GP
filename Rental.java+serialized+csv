package com.rentalsystem;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;
import java.time.LocalDate;

public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;

    @CsvBindByName
    private String customerId;

    @CsvBindByName
    private String plateNumber;

    @CsvBindByName
    private LocalDate rentalDate;

    @CsvBindByName
    private LocalDate returnDate;

    @CsvBindByName
    private double totalPrice;

    @CsvBindByName
    private double penalty;

    @CsvBindByName
    private double discount;

    // Default constructor
    public Rental() { }

    // Constructor with all fields
    public Rental(String customerId,
                  String plateNumber,
                  LocalDate rentalDate,
                  LocalDate returnDate,
                  double totalPrice,
                  double penalty,
                  double discount) {
        this.customerId = customerId;
        this.plateNumber = plateNumber;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
        this.penalty = penalty;
        this.discount = discount;
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPenalty() {
        return penalty;
    }

    public double getDiscount() {
        return discount;
    }

    // Setters
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // Method to calculate final amount
    public double calculateFinalAmount() {
        return totalPrice + penalty - discount;
    }

    // toString method
    @Override
    public String toString() {
        return "Rental Date: " + rentalDate + 
               ", Return Date: " + returnDate +
               ", Total Price: RM" + totalPrice + 
               ", Penalty: RM" + penalty +
               ", Discount: RM" + discount + 
               ", Final Amount: RM" + calculateFinalAmount();
    }
}
