import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Receipt {
    
    public static String generateReceipt(Rental rental, Customer customer, Vehicle vehicle) {
        if (rental == null) {
            return "Error: No rental data provided for receipt.";
        }

        StringBuilder receipt = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Header
        receipt.append("=================================================\n");
        receipt.append("           VEHICLE RENTAL RECEIPT              \n");
        receipt.append("=================================================\n\n");

        // Receipt info
        receipt.append("Receipt Date: ").append(LocalDate.now().format(dateFormatter)).append("\n");
        receipt.append("Rental ID: ").append(rental.getCustomerId()).append("\n\n");

        // Customer Details
        receipt.append("--- Customer Details ---\n");
        if (customer != null) {
            receipt.append("Name:    ").append(customer.getName()).append("\n");
            receipt.append("ID:      ").append(customer.getId()).append("\n");
            receipt.append("Phone:   ").append(customer.getPhone()).append("\n");
            receipt.append("Address: ").append(customer.getAddress()).append("\n");
        } else {
            receipt.append("Customer information not available\n");
        }
        receipt.append("\n");

        // Vehicle Details
        receipt.append("--- Vehicle Details ---\n");
        if (vehicle != null) {
            receipt.append("Model:       ").append(vehicle.getModel()).append("\n");
            receipt.append("Plate No:    ").append(vehicle.getPlateNumber()).append("\n");
            receipt.append("Price/Day:   RM").append(String.format("%.2f", vehicle.getPricePerDay())).append("\n");
        } else {
            receipt.append("Vehicle information not available\n");
        }
        receipt.append("\n");

        // Rental Details
        receipt.append("--- Rental Details ---\n");
        receipt.append("Rental Date: ").append(rental.getRentalDate()).append("\n");
        receipt.append("Return Date: ").append(rental.getReturnDate()).append("\n");
        receipt.append("Rental Days: ").append(rental.getRentalDays()).append("\n\n");

        // Charges
        receipt.append("--- Charges ---\n");
        receipt.append(String.format("%-20s RM %10.2f\n", "Subtotal:", rental.getTotalPrice()));
        receipt.append(String.format("%-20s RM -%9.2f\n", "Discount:", rental.getDiscount()));
        receipt.append(String.format("%-20s RM %10.2f\n", "Penalty:", rental.getPenalty()));
        receipt.append("-------------------------------------------------\n");
        receipt.append(String.format("%-20s RM %10.2f\n", "GRAND TOTAL:", rental.calculateFinalAmount()));
        receipt.append("\n");

        // Footer
        receipt.append("=================================================\n");
        receipt.append("       Thank you for renting with us!            \n");
        receipt.append("=================================================\n");

        return receipt.toString();
    }
}