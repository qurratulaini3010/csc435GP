public class Lorry extends Vehicle {
    private double maxLoad;  // in tons
    private boolean hasTailLift;

    public Lorry(String model, String plateNumber, double pricePerDay, 
                 double maxLoad, boolean hasTailLift) {
        super(model, plateNumber, pricePerDay);
        this.vehicleType = "Lorry";
        this.maxLoad = maxLoad;
        this.hasTailLift = hasTailLift;
    }

    // Lorry-specific methods
    public double getMaxLoad() {
        return maxLoad;
    }

    public boolean hasTailLift() {
        return hasTailLift;
    }
}
