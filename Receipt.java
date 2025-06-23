import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Receipt {
    
    public static String generateReceipt(Rental rental) {
        if (rental == null) {
            return "Error: No rental data provided for receipt.";
        }

        Customer customer = rental.getRentingCustomer();
        Vehicle vehicle = rental.getRentedVehicle();

        StringBuilder receipt = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        receipt.append("=================================================\n");
        receipt.append("           VEHICLE RENTAL RECEIPT              \n");
        receipt.append("=================================================\n");
        receipt.append("\n");

        receipt.append("Receipt Date: " + LocalDate.now().format(dateFormatter) + "\n");
        receipt.append("Rental ID: " + System.currentTimeMillis() + " (Auto-generated for receipt)\n"); // Unique ID for each receipt print
        receipt.append("\n");

        receipt.append("--- Customer Details ---\n");
        receipt.append("Name:    " + (customer != null ? customer.getName() : "N/A") + "\n");
        receipt.append("ID:      " + (customer != null ? customer.getId() : "N/A") + "\n");
        receipt.append("Phone:   " + (customer != null ? customer.getPhone() : "N/A") + "\n");
        receipt.append("Address: " + (customer != null ? customer.getAddress() : "N/A") + "\n");
        receipt.append("\n");

        receipt.append("--- Vehicle Details ---\n");
        receipt.append("Model:       " + (vehicle != null ? vehicle.getModel() : "N/A") + "\n");
        receipt.append("Plate No:    " + (vehicle != null ? vehicle.getPlateNumber() : "N/A") + "\n");
        receipt.append("Price/Day:   RM " + String.format("%.2f", (vehicle != null ? vehicle.getPricePerDay() : 0.0)) + "\n");
        receipt.append("\n");

        receipt.append("--- Rental Details ---\n");
        receipt.append("Rental Date: " + rental.getRentalDate().format(dateFormatter) + "\n");
        receipt.append("Return Date: " + rental.getReturnDate().format(dateFormatter) + " (Planned)\n");
        receipt.append("\n");

        receipt.append("--- Charges ---\n");
        receipt.append(String.format("%-20s RM %-10.2f\n", "Subtotal:", rental.getTotalPrice() + rental.getDiscount())); // Subtotal before discount
        receipt.append(String.format("%-20s RM -%-9.2f\n", "Discount:", rental.getDiscount()));
        receipt.append(String.format("%-20s RM %-10.2f\n", "Total Rental Fee:", rental.getTotalPrice()));
        receipt.append(String.format("%-20s RM %-10.2f\n", "Late Return Penalty:", rental.getPenalty()));
        receipt.append("-------------------------------------------------\n");
        receipt.append(String.format("%-20s RM %-10.2f\n", "GRAND TOTAL DUE:", rental.getTotalPrice() + rental.getPenalty()));
        receipt.append("\n");

        receipt.append("=================================================\n");
        receipt.append("       Thank you for renting with us!            \n");
        receipt.append("=================================================\n");

        return receipt.toString();
    }
   }
  
 
