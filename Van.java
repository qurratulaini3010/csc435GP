public class Van extends Vehicle {
    private int cargoCapacity;
    
    public Van(String model, String plateNumber, double pricePerDay, int cargoCapacity) {
        super(model, plateNumber, pricePerDay);
        this.cargoCapacity = cargoCapacity;
        super.vehicleType = "Van";
    }
    
    public int getCargoCapacity() { return cargoCapacity; }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" - Capacity: %d cu.ft", cargoCapacity);
    }
}