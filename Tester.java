import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        MainSystem system = new MainSystem();
        Scanner scanner = new Scanner(System.in);
        
        
        try {
            boolean running = true;
            while (running) {
            System.out.println("\n==== Vehicle Rental System ====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Add Customer");
            System.out.println("3. Create Rental");
            System.out.println("4. Display All Vehicles");
            System.out.println("5. Display All Customers");
            System.out.println("6. Display All Rentals");
            System.out.println("7. Exit");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addVehicleInteractive(system, scanner);
                    break;
                case 2:
                    addCustomerInteractive(system, scanner);
                    break;
                case 3:
                    createRentalInteractive(system, scanner);
                    break;
                case 4:
                    system.displayVehicles();
                    break;
                case 5:
                    system.displayCustomers();
                    break;
                case 6:
                    system.displayRentals();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        
         if (choice == 7) { // Exit option
                    system.saveAllData();
                    running = false;
                    System.out.println("Data saved. Exiting system...");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static void initializeSampleData(MainSystem system) {
        // Add sample vehicles
        system.addVehicle(new Van("Hyundai Starex", "VAN001", 250.0, true, 200));
        system.addVehicle(new Car("Perodua Bezza", "CAR001", 150.0, true));
        
        // Add sample customers
        system.addCustomer(new Customer("Ali Ahmad", "C001", "0123456789", "Shah Alam"));
        system.addCustomer(new Customer("Siti Aminah", "C002", "0198765432", "Kuala Lumpur"));
    }

    private static void addVehicleInteractive(MainSystem system, Scanner scanner) {
        System.out.println("\nAdd New Vehicle");
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        
        System.out.print("Enter plate number: ");
        String plate = scanner.nextLine();
        
        System.out.print("Enter price per day: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter type (1-Car, 2-Van, 3-Lorry, 4-Bus): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        
        Vehicle vehicle;
        switch (type) {
            case 1:
                vehicle = new Car(model, plate, price, true);
                break;
            case 2:
                System.out.print("Enter cargo capacity: ");
                int capacity = scanner.nextInt();
                scanner.nextLine();
                vehicle = new Van(model, plate, price, true, capacity);
                break;
            // cases for Lorry and Bus would be similar
            default:
                vehicle = new Vehicle(model, plate, price, true);
        }
        
        system.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
    }

    private static void addCustomerInteractive(MainSystem system, Scanner scanner) {
        System.out.println("\nAdd New Customer");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        system.addCustomer(new Customer(name, id, phone, address));
        System.out.println("Customer added successfully!");
    }

    private static void createRentalInteractive(MainSystem system, Scanner scanner) {
        System.out.println("\nCreate New Rental");
        
        // Show available vehicles
        System.out.println("Available Vehicles:");
        system.displayVehicles();
        
        System.out.print("Enter vehicle plate number: ");
        String plate = scanner.nextLine();
        
        // Show customers
        System.out.println("Customers:");
        system.displayCustomers();
        
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        
        System.out.print("Enter rental date (YYYY-MM-DD): ");
        String rentalDate = scanner.nextLine();
        
        System.out.print("Enter return date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();
        
        System.out.print("Enter daily rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter discount (if any): ");
        double discount = scanner.nextDouble();
        scanner.nextLine();
        
        // Calculate total price (simple calculation)
        double totalPrice = rate * 1; // In real system, calculate days between dates
        
        Rental rental = new Rental(rentalDate, returnDate, totalPrice, 0, discount);
        if (system.addRental(rental, plate, customerId)) {
            System.out.println("Rental created successfully!");
        } else {
            System.out.println("Failed to create rental. Vehicle may not be available.");
        }
    }
}