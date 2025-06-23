public class Van extends Vehicle {
    private int cargoCapacity;
    
    public Van(String model, String plateNumber, double pricePerDay, boolean available, int cargoCapacity) {
        super(model, plateNumber, pricePerDay, available);
        this.vehicleType = "Van";
        this.cargoCapacity = cargoCapacity;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" - Capacity: %d cu.ft", cargoCapacity);
    }
    @Override
    public String toFileString() {
        return super.toFileString() + "," + cargoCapacity;
    }
}
