public class Lorry extends Vehicle {
    private double maxLoad;
    private boolean hasTailLift;

    public Lorry(String model, String plateNumber, double pricePerDay,  double maxLoad, boolean hasTailLift) {
        super(model, plateNumber, pricePerDay);
        this.vehicleType = "Lorry";
        this.maxLoad = maxLoad;
        this.hasTailLift = hasTailLift;
    }

    public double getMaxLoad() { return maxLoad; }
    public boolean hasTailLift() { return hasTailLift; }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" - Max Load: %.1f tons - Tail Lift: %s",
            maxLoad, hasTailLift ? "Yes" : "No");
    }
}