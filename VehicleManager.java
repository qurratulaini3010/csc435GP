import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
    private ArrayList<Vehicle> vehicles;
    private static final String VEHICLE_FILE = "vehicles.txt";

    public VehicleManager() {
        this.vehicles = new ArrayList<>();
        loadVehicles();
    }
    public ArrayList<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles); 
    }

    private void loadVehicles() {
        List<String> vehicleData = FileHandler.loadFromFile(VEHICLE_FILE);
        for (String data : vehicleData) {
            vehicles.add(Vehicle.fromFileString(data));
        }
    }

    public void saveVehicles() {
        List<String> vehicleData = new ArrayList<>();
        for (Vehicle v : vehicles) {
            vehicleData.add(v.toFileString());
        }
        FileHandler.saveToFile(VEHICLE_FILE, vehicleData);
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
    public Vehicle findVehicleByPlate(String plateNumber) {
    for (Vehicle vehicle : vehicles) {
        if (vehicle.getPlateNumber().equalsIgnoreCase(plateNumber)) {
            return vehicle;
        }
    }
    return null; // Return null if vehicle not found
}
}