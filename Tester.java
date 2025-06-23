public class Tester {
    public static void main(String[] args) {
        MainSystem system = new MainSystem();

        // Add sample vehicles
        system.addVehicle("Proton Saga 2021");
        system.addVehicle("Perodua Myvi 2022");

        // Add customers
        Customer c1 = new Customer("Ali Ahmad", "C001", "0123456789", "Shah Alam");
        Customer c2 = new Customer("Siti Aminah", "C002", "0198765432", "Kuala Lumpur");
        system.addCustomer(c1);
        system.addCustomer(c2);

        // Add rentals
        Rental r1 = new Rental("2025-06-01", "2025-06-05", 500.00, 0.00, 50.00);
        Rental r2 = new Rental("2025-06-02", "2025-06-04", 400.00, 30.00, 0.00);
        system.addRental(r1);
        system.addRental(r2);

        // Display all
        system.displayVehicles();
        System.out.println();
        system.displayCustomers();
        System.out.println();
        system.displayRentals();
    }
}
