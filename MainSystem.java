import java.util.ArrayList;

public class MainSystem {
    private VehicleManager vehicleManager;
    private ArrayList<Customer> customerList;
    private ArrayList<Rental> rentalList;

    public MainSystem() {
        this.vehicleManager = new VehicleManager();
        this.customerList = new ArrayList<>();
        this.rentalList = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleManager.addVehicle(vehicle);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void addRental(Rental rental) {
        rentalList.add(rental);
    }

    public boolean rentVehicle(String plateNumber, Customer customer, Rental rental) {
        if (vehicleManager.rentVehicle(plateNumber)) {
            addRental(rental);
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
        for (Customer c : customerList) {
            System.out.println("- " + c);
        }
    }

    private void displayRentals() {
        System.out.println("\nRental List:");
        for (Rental r : rentalList) {
            System.out.println("- " + r);
        }
    }
}