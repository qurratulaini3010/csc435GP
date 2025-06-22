public class Vehicle {
    private String model;
    private String plateNumber;
    private double pricePerDay;
    private boolean available;
    public String vehicleType;
    
    public Vehicle(String model, String plateNumber, double pricePerDay) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.pricePerDay = pricePerDay;
        this.available = true;
        this.vehicleType = "Generic Vehicle";
    }

    // Getters and setters
    public String getModel() { return model; }
    public String getPlateNumber() { return plateNumber; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return available; }
    public String getVehicleType() { return vehicleType; }

    public void setAvailable(boolean available) { this.available = available; }
    
    public String toString() {
        return String.format("%s: %s (%s) - RM%.2f/day - %s", 
            vehicleType, model, plateNumber, pricePerDay, 
            available ? "Available" : "Rented");
    }
}