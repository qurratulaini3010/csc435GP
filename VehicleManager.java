import java.util.ArrayList;

public class VehicleManager {
    private ArrayList<Vehicle> vehicles;
    
    public VehicleManager() {
        this.vehicles = new ArrayList<>();
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    
    public ArrayList<Vehicle> getAvailableVehicles() {
        ArrayList<Vehicle> available = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }
    
    public boolean rentVehicle(String plateNumber) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(plateNumber) && v.isAvailable()) {
                v.setAvailable(false);
                return true;
            }
        }
        return false;
    }
    
    public boolean returnVehicle(String plateNumber) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(plateNumber) && !v.isAvailable()) {
                v.setAvailable(true);
                return true;
            }
        }
        return false;
    }
    
    public void displayVehicles() {
        System.out.println("\nVehicle List:");
        for (Vehicle v : vehicles) {
            System.out.println("- " + v);
        }
    }
}