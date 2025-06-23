import java.util.ArrayList;

public class MainSystem {
    private ArrayList<String> vehicleList;
    private ArrayList<Customer> customerList;
    private ArrayList<Rental> rentalList;

    public MainSystem() {
        vehicleList = new ArrayList<String>();
        customerList = new ArrayList<Customer>();
        rentalList = new ArrayList<Rental>();
    }

    // Add vehicle
    public void addVehicle(String vehicle) {
        vehicleList.add(vehicle);
    }

    // Add customer
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // Add rental
    public void addRental(Rental rental) {
        rentalList.add(rental);
    }

    // Display all vehicles
    public void displayVehicles() {
        System.out.println("Vehicle List:");
        for (String v : vehicleList) {
            System.out.println("- " + v);
        }
    }

    // Display all customers
    public void displayCustomers() {
        System.out.println("Customer List:");
        for (Customer c : customerList) {
            System.out.println(c);
        }
    }

    // Display all rentals
    public void displayRentals() {
        System.out.println("Rental List:");
        for (Rental r : rentalList) {
            System.out.println(r);
        }
    }
}

