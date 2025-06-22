import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
    private List<Vehicle> vehicles;

    public VehicleManager() {
        this.vehicles = new ArrayList<>();
    }

    // Add a new vehicle to the system
    public boolean addVehicle(Vehicle vehicle) {
        // Check if plate number already exists
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(vehicle.getPlateNumber())) {
                return false;  // vehicle already exists
            }
        }
        vehicles.add(vehicle);
        return true;
    }

    // Remove a vehicle from the system
    public boolean removeVehicle(String plateNumber) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(plateNumber)) {
                if (!v.isAvailable()) {
                    return false;  // can't remove a rented vehicle
                }
                vehicles.remove(v);
                return true;
            }
        }
        return false;  // vehicle not found
    }

    // Get all available vehicles
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                availableVehicles.add(v);
            }
        }
        return availableVehicles;
    }

    // Get vehicles by type
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    // Mark vehicle as rented (not available)
    public boolean rentVehicle(String plateNumber) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(plateNumber)) {
                if (v.isAvailable()) {
                    v.setAvailable(false);
                    return true;
                }
                return false;  // vehicle not available
            }
        }
        return false;  // vehicle not found
    }

    // Mark vehicle as returned (available)
    public boolean returnVehicle(String plateNumber) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(plateNumber)) {
                if (!v.isAvailable()) {
                    v.setAvailable(true);
                    return true;
                }
                return false;  // vehicle already available
            }
        }
        return false;  // vehicle not found
    }

    // Find vehicle by plate number
    public Vehicle findVehicle(String plateNumber) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(plateNumber)) {
                return v;
            }
        }
        return null;  // not found
    }
}
