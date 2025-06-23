import java.util.ArrayList;
import java.util.List;

public class MainSystem {
    private VehicleManager vehicleManager;
    private ArrayList<Customer> customerList;
    private ArrayList<Rental> rentalList;
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String RENTAL_FILE = "rentals.txt";

    public MainSystem() {
        this.vehicleManager = new VehicleManager();
        this.customerList = new ArrayList<>();
        this.rentalList = new ArrayList<>();
        loadCustomers();
        loadRentals();
    }

    private void loadCustomers() {
        List<String> customerData = FileHandler.loadFromFile(CUSTOMER_FILE);
        for (String data : customerData) {
            customerList.add(Customer.fromFileString(data));
        }
    }

    private void loadRentals() {
        List<String> rentalData = FileHandler.loadFromFile(RENTAL_FILE);
        for (String data : rentalData) {
            rentalList.add(Rental.fromFileString(data));
        }
    }

    public void saveAllData() {
        vehicleManager.saveVehicles();
        
        List<String> customerData = new ArrayList<>();
        for (Customer c : customerList) {
            customerData.add(c.toFileString());
        }
        FileHandler.saveToFile(CUSTOMER_FILE, customerData);
        
        List<String> rentalData = new ArrayList<>();
        for (Rental r : rentalList) {
            rentalData.add(r.toFileString());
        }
        FileHandler.saveToFile(RENTAL_FILE, rentalData);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleManager.addVehicle(vehicle);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public boolean addRental(Rental rental, String plateNumber, String customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) return false;
        
        if (vehicleManager.rentVehicle(plateNumber)) {
            rentalList.add(rental);
            return true;
        }
        return false;
    }

    private Customer findCustomerById(String id) {
        for (Customer c : customerList) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void displayVehicles() {
        vehicleManager.displayVehicles();
    }

    public void displayCustomers() {
        System.out.println("\nCustomer List:");
        for (Customer c : customerList) {
            System.out.println("- " + c);
        }
    }

    public void displayRentals() {
        System.out.println("\nRental List:");
        for (Rental r : rentalList) {
            System.out.println("- " + r);
        }
    }
}