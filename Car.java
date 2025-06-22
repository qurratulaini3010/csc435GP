public class Car extends Vehicle {
    private int seatingCapacity;
    private String transmissionType;  // "Automatic" or "Manual"

    public Car(String model, String plateNumber, double pricePerDay, 
               int seatingCapacity, String transmissionType) {
        super(model, plateNumber, pricePerDay);
        this.vehicleType = "Car";
        this.seatingCapacity = seatingCapacity;
        this.transmissionType = transmissionType;
    }

    // Car-specific methods
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getTransmissionType() {
        return transmissionType;
    }
}