public class Bus extends Vehicle {
    private int passengerCapacity;
    private boolean hasAirConditioning;

    public Bus(String model, String plateNumber, double pricePerDay, 
               int passengerCapacity, boolean hasAirConditioning) {
        super(model, plateNumber, pricePerDay);
        this.vehicleType = "Bus";
        this.passengerCapacity = passengerCapacity;
        this.hasAirConditioning = hasAirConditioning;
    }

    // Bus-specific methods
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public boolean hasAirConditioning() {
        return hasAirConditioning;
    }
}
