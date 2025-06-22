
/**
 * Write a description of class Van here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Van extends Vehicle {
    private int cargoCapacity;  // in cubic feet
    private boolean hasSlidingDoor;

    public Van(String model, String plateNumber, double pricePerDay, 
               int cargoCapacity, boolean hasSlidingDoor) {
        super(model, plateNumber, pricePerDay);
        this.vehicleType = "Van";
        this.cargoCapacity = cargoCapacity;
        this.hasSlidingDoor = hasSlidingDoor;
    }

    // Van-specific methods
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public boolean hasSlidingDoor() {
        return hasSlidingDoor;
    }
}
