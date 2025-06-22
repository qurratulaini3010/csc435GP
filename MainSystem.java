import java.util.*;
import java.io.*;

// --- VehicleManager.java ---
public class VehicleManager {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle v) { vehicles.add(v); }

    public boolean rentVehicle(String plateNumber) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equalsIgnoreCase(plateNumber) && !v.isRented()) {
                v.setRented(true);
                return true;
            }
        }
        return false;
    }

    public void displayVehicles() {
        System.out.println("Vehicle List:");
        vehicles.forEach(v -> System.out.println("- " + v));
    }

    // Load & save support
    public void load(String filename) throws Exception {
        vehicles = FileHandler.readObjects(filename);
    }
    public void save(String filename) throws Exception {
        FileHandler.writeObjects(filename, vehicles);
    }
}

// --- MainSystem.java ---
public class MainSystem {
    private VehicleManager vehicleManager;
    private List<Customer> customers;
    private List<Rental> rentals;

    public MainSystem() {
        this.vehicleManager = new VehicleManager();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public void loadAll() {
        try { vehicleManager.load("data/vehicles.dat"); }
        catch (Exception e) { System.err.println("Vehicles load: " + e.getMessage()); }

        try { customers = FileHandler.readCsv("data/customers.csv", Customer.class); }
        catch (Exception e) { System.err.println("Customers load: " + e.getMessage()); }

        try { rentals = FileHandler.readCsv("data/rentals.csv", Rental.class); }
        catch (Exception e) { System.err.println("Rentals load: " + e.getMessage()); }
    }

    public void saveAll() {
        try { vehicleManager.save("data/vehicles.dat"); }
        catch (Exception e) { System.err.println("Vehicles save: " + e.getMessage()); }

        try { FileHandler.writeCsv("data/customers.csv", customers, Customer.class); }
        catch (Exception e) { System.err.println("Customers save: " + e.getMessage()); }

        try { FileHandler.writeCsv("data/rentals.csv", rentals, Rental.class); }
        catch (Exception e) { System.err.println("Rentals save: " + e.getMessage()); }
    }

    public void addVehicle(Vehicle v) { vehicleManager.addVehicle(v); }
    public void addCustomer(Customer c) { customers.add(c); }
    public void addRental(Rental r) { rentals.add(r); }

    public boolean rentVehicle(String plate, Customer c, Rental r) {
        if (vehicleManager.rentVehicle(plate)) {
            addCustomer(c);
            addRental(r);
            return true;
        }
        return false;
    }

    public void displayAll() {
        vehicleManager.displayVehicles();
        displayCustomers();
        displayRentals();
    }

    private void displayCustomers() {
        System.out.println("\nCustomer List:");
        customers.forEach(c -> System.out.println("- " + c));
    }

    private void displayRentals() {
        System.out.println("\nRental List:");
        rentals.forEach(r -> System.out.println("- " + r));
    }

    // Main
    public static void main(String[] args) {
        MainSystem sys = new MainSystem();
        sys.loadAll();

        // Sample data
        Vehicle v = new Car("ABC123", "Toyota Corolla", false);
        sys.addVehicle(v);
        Customer cust = new Customer("Jane Doe", "jane@example.com");
        Rental rent = new Rental(cust, v, new Date(), null, 50.0);
        boolean ok = sys.rentVehicle("ABC123", cust, rent);
        System.out.println(ok ? "Rental successful!" : "Rental failed.");

        sys.displayAll();
        sys.saveAll();
    }
}

// --- Assumed support classes ---
// (Vehicle base class, subclass Car, Customer, Rental)
// and FileHandler with read/write methods.
