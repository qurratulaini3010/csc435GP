public class Tester {
    public static void main(String[] args) {
        MainSystem system = new MainSystem();

        // Add vehicles
        system.addVehicle(new Van("Hyundai Starex", "VAN001", 300.00, 250));
        system.addVehicle(new Car("Proton Saga", "CAR001", 150.00, 5, "Normal"));

        // Add customers
        Customer c1 = new Customer("Ali Ahmad", "C001", "0123456789", "Shah Alam");
        Customer c2 = new Customer("Siti Aminah", "C002", "0198765432", "Kuala Lumpur");
        system.addCustomer(c1);
        system.addCustomer(c2);

        // Create and process rental
        Rental r1 = new Rental("2025-06-01", "2025-06-05", 1500.00, 0.00, 100.00);
        boolean rented = system.rentVehicle("VAN001", c1, r1);
        System.out.println("Rental " + (rented ? "successful" : "failed"));

        // Display all
        system.displayAll();
    }
}