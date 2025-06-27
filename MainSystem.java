import java.util.ArrayList;
import java.util.List;

public class MainSystem {
    private VehicleManager vehicleManager;
    private ArrayList<Customer> customerList;
    private ArrayList<Rental> rentalList;
    private ArrayList<Feedback> feedbackList;
    private static final String FEEDBACK_FILE = "feedback.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String RENTAL_FILE = "rentals.txt";

    public MainSystem() {
        this.vehicleManager = new VehicleManager();
        this.customerList = new ArrayList<>();
        this.rentalList = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
        loadFeedback();
        loadCustomers();
        loadRentals();
    }
    
    private void loadFeedback() {
        List<String> feedbackData = FileHandler.loadFromFile(FEEDBACK_FILE);
        for (String data : feedbackData) {
            if (!data.trim().isEmpty()) {
                feedbackList.add(Feedback.fromFileString(data));
            }
        }
    }

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
        saveFeedback();
    }

    private void saveFeedback() {
        List<String> feedbackData = new ArrayList<>();
        for (Feedback f : feedbackList) {
            feedbackData.add(f.toFileString());
        }
        FileHandler.saveToFile(FEEDBACK_FILE, feedbackData);
    }

    public List<Feedback> getFeedbackList() {
        return new ArrayList<>(feedbackList);
    }

    private void loadCustomers() {
        List<String> customerData = FileHandler.loadFromFile(CUSTOMER_FILE);
        for (String data : customerData) {
            customerList.add(Customer.fromFileString(data));
        }
    }
     public VehicleManager getVehicleManager() {
        return this.vehicleManager;
    }
    public ArrayList<Vehicle> getAllVehicles() {
        return vehicleManager.getVehicles();
    }

    private void loadRentals() {
        List<String> rentalData = FileHandler.loadFromFile(RENTAL_FILE);
        for (String data : rentalData) {
            rentalList.add(Rental.fromFileString(data));
        }
    }

    public void saveAllData() {
        vehicleManager.saveVehicles();
    
    // Save customers
    List<String> customerData = new ArrayList<>();
    for (Customer c : customerList) {
        customerData.add(c.toFileString());
    }
    FileHandler.saveToFile(CUSTOMER_FILE, customerData);
    
    // Save rentals
    List<String> rentalData = new ArrayList<>();
    for (Rental r : rentalList) {
        rentalData.add(r.toFileString());
    }
    FileHandler.saveToFile(RENTAL_FILE, rentalData);
    
    // Save feedback
    saveFeedback();
    }
    public List<Rental> getRentalList() {
    return new ArrayList<>(rentalList);
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
        return Customer.findById(customerList, id);
    }
    public List<Customer> getCustomerList() {
    return new ArrayList<>(this.customerList); // Return a copy for encapsulation
}

    
}