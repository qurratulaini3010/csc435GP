public class Car extends Vehicle {
    private int seatingCapacity;
    private String transmissionType;

    public Car(String model, String plateNumber, double pricePerDay, 
               int seatingCapacity, String transmissionType) {
        super(model, plateNumber, pricePerDay);
        this.vehicleType = "Car";
        this.seatingCapacity = seatingCapacity;
        this.transmissionType = transmissionType;
    }

    public int getSeatingCapacity() { return seatingCapacity; }
    public String getTransmissionType() { return transmissionType; }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" - Seats: %d - Transmission: %s",
            seatingCapacity, transmissionType);
    }
}