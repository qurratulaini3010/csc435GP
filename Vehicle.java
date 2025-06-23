public class Vehicle {
    // Attributes
    private String model;
    private String plateNumber;
    private double pricePerDay;
    private boolean available;

    // Constructor
    public Vehicle(String model, String plateNumber, double pricePerDay, boolean available) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.pricePerDay = pricePerDay;
        this.available = available;
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

    // Method to check availability and display price per day
    public void checkAvailability() {
        if(available) {
          System.out.println(model + " (" + plateNumber + ") is available at RM" + pricePerDay + " per day.");
        } else {
          System.out.println(model + " (" + plateNumber + ") is not available.");
        }
    }

}






