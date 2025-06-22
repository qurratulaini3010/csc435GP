public class Bus extends Vehicle {
    private int passengerCapacity;
    private boolean hasAirConditioning;

    public Bus(String model, String plateNumber, double pricePerDay, 
              boolean available, int passengerCapacity, boolean hasAirConditioning) {
        super(model, plateNumber, pricePerDay);
        this.vehicleType = "Bus";
        this.passengerCapacity = passengerCapacity;
        this.hasAirConditioning = hasAirConditioning;
    }

    public int getPassengerCapacity() { return passengerCapacity; }
    public boolean hasAirConditioning() { return hasAirConditioning; }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" - Capacity: %d passengers - A/C: %s",
            passengerCapacity, hasAirConditioning ? "Yes" : "No");
    }
}