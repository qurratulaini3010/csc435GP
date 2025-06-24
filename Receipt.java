import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Receipt {
    
    public static String generateReceipt(Rental rental, Customer customer, Vehicle vehicle) {
        if (rental == null) {
            return "Error: No rental data provided for receipt.";
        }

        StringBuilder receipt = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");

        // Header
        receipt.add("=================================================\n");
        receipt.add("           VEHICLE RENTAL RECEIPT              \n");
        receipt.add("=================================================\n\n");

        // Receipt info
        receipt.add("Receipt Date: ").add(LocalDate.now().format(dateFormatter)).add("\n");
        receipt.add("Rental ID: ").add(rental.getCustomerId()).add("\n\n");

        // Customer Details
        receipt.add("--- Customer Details ---\n");
        if (customer != null) {
            receipt.add("Name:    ").add(customer.getName()).add("\n");
            receipt.add("ID:      ").add(customer.getId()).add("\n");
            receipt.add("Phone:   ").add(customer.getPhone()).add("\n");
            receipt.add("Address: ").add(customer.getAddress()).add("\n");
        } else {
            receipt.add("Customer information not available\n");
        }
        receipt.add("\n");

        // Vehicle Details
        receipt.append("--- Vehicle Details ---\n");
        if (vehicle != null) {
            receipt.add("Model:       ").add(vehicle.getModel()).add("\n");
            receipt.add("Plate No:    ").add(vehicle.getPlateNumber()).add("\n");
            receipt.add("Price/Day:   RM").add(String.format("%.2f", vehicle.getPricePerDay())).add("\n");
        } else {
            receipt.add("Vehicle information not available\n");
        }
        receipt.add("\n");

        // Rental Details
        receipt.add("--- Rental Details ---\n");
        receipt.add("Rental Date: ").append(rental.getRentalDate()).append("\n");
        receipt.add("Return Date: ").append(rental.getReturnDate()).append("\n");
        receipt.add("Rental Days: ").append(rental.getRentalDays()).append("\n\n");

        // Charges
        receipt.add("--- Charges ---\n");
        receipt.add(String.format("%-20s RM %10.2f\n", "Subtotal:", rental.getTotalPrice()));
        receipt.add(String.format("%-20s RM -%9.2f\n", "Discount:", rental.getDiscount()));
        receipt.add(String.format("%-20s RM %10.2f\n", "Penalty:", rental.getPenalty()));
        receipt.add("-------------------------------------------------\n");
        receipt.add(String.format("%-20s RM %10.2f\n", "GRAND TOTAL:", rental.calculateFinalAmount()));
        receipt.add("\n");

        // Footer
        receipt.add("=================================================\n");
        receipt.add("       Thank you for renting with us!            \n");
        receipt.add("=================================================\n");

        return receipt.toString();
    }
}
