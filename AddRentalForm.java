import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.awt.BorderLayout;

public class AddRentalForm {
    private MainSystem system;

    public AddRentalForm(MainSystem system) {
        this.system = system;

        JFrame frame = new JFrame("Create Rental");
        frame.setSize(500, 450);  // Increased size for better layout
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(null);
        frame.add(panel);

        // Customer Section
        JLabel custSectionLabel = new JLabel("Customer Information:");
        custSectionLabel.setBounds(10, 10, 200, 25);
        panel.add(custSectionLabel);

        JLabel custLabel = new JLabel("Customer ID:");
        custLabel.setBounds(10, 40, 100, 25);
        panel.add(custLabel);

        JTextField custField = new JTextField();
        custField.setBounds(120, 40, 200, 25);
        panel.add(custField);

        JButton findCustomerBtn = new JButton("Find");
        findCustomerBtn.setBounds(330, 40, 80, 25);
        panel.add(findCustomerBtn);

        JTextArea customerInfoArea = new JTextArea();
        customerInfoArea.setBounds(10, 70, 400, 60);
        customerInfoArea.setEditable(false);
        panel.add(customerInfoArea);

        // Vehicle Section
        JLabel vehicleSectionLabel = new JLabel("Vehicle Information:");
        vehicleSectionLabel.setBounds(10, 140, 200, 25);
        panel.add(vehicleSectionLabel);

        JLabel plateLabel = new JLabel("Vehicle Plate:");
        plateLabel.setBounds(10, 170, 100, 25);
        panel.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(120, 170, 200, 25);
        panel.add(plateField);

        JButton findVehicleBtn = new JButton("Find");
        findVehicleBtn.setBounds(330, 170, 80, 25);
        panel.add(findVehicleBtn);

        JTextArea vehicleInfoArea = new JTextArea();
        vehicleInfoArea.setBounds(10, 200, 400, 60);
        vehicleInfoArea.setEditable(false);
        panel.add(vehicleInfoArea);

        // Rental Details Section
        JLabel rentalSectionLabel = new JLabel("Rental Details:");
        rentalSectionLabel.setBounds(10, 270, 200, 25);
        panel.add(rentalSectionLabel);

        JLabel rentDateLabel = new JLabel("Rental Date:");
        rentDateLabel.setBounds(10, 300, 100, 25);
        panel.add(rentDateLabel);

        JTextField rentDateField = new JTextField(LocalDate.now().toString());
        rentDateField.setBounds(120, 300, 150, 25);
        panel.add(rentDateField);

        JLabel returnDateLabel = new JLabel("Return Date:");
        returnDateLabel.setBounds(10, 330, 100, 25);
        panel.add(returnDateLabel);

        JTextField returnDateField = new JTextField(LocalDate.now().plusDays(1).toString());
        returnDateField.setBounds(120, 330, 150, 25);
        panel.add(returnDateField);

        // Create Button
        JButton addButton = new JButton("Create Rental");
        addButton.setBounds(180, 370, 150, 30);
        panel.add(addButton);

        // Find Customer Button Action
        findCustomerBtn.addActionListener(e -> {
            String custId = custField.getText().trim();
            if (custId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a customer ID");
                return;
            }

            Customer customer = Customer.findById(system.getCustomerList(), custId);
            if (customer != null) {
                customerInfoArea.setText(customer.toString());
            } else {
                customerInfoArea.setText("Customer not found");
                JOptionPane.showMessageDialog(frame, "Customer with ID " + custId + " not found");
            }
        });

        // Find Vehicle Button Action
        findVehicleBtn.addActionListener(e -> {
            String plate = plateField.getText().trim();
            if (plate.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a plate number");
                return;
            }

            Vehicle vehicle = system.getVehicleManager().findVehicleByPlate(plate);
            if (vehicle != null) {
                vehicleInfoArea.setText(vehicle.toString());
                if (!vehicle.isAvailable()) {
                    vehicleInfoArea.append("\n(WARNING: Vehicle is currently rented)");
                }
            } else {
                vehicleInfoArea.setText("Vehicle not found");
                JOptionPane.showMessageDialog(frame, "Vehicle with plate " + plate + " not found");
            }
        });

        // Create Rental Button Action
        addButton.addActionListener(e -> {
            try {
                // Validate customer
                String custId = custField.getText().trim();
                Customer customer = Customer.findById(system.getCustomerList(), custId);
                if (customer == null) {
                    JOptionPane.showMessageDialog(frame, "Please find a valid customer first");
                    return;
                }

                // Validate vehicle
                String plate = plateField.getText().trim();
                Vehicle vehicle = system.getVehicleManager().findVehicleByPlate(plate);
                if (vehicle == null) {
                    JOptionPane.showMessageDialog(frame, "Please find a valid vehicle first");
                    return;
                }
                if (!vehicle.isAvailable()) {
                    JOptionPane.showMessageDialog(frame, "Selected vehicle is not available for rent");
                    return;
                }

                // Validate dates
                LocalDate startDate = LocalDate.parse(rentDateField.getText());
                LocalDate endDate = LocalDate.parse(returnDateField.getText());
                
                if (endDate.isBefore(startDate)) {
                    JOptionPane.showMessageDialog(frame, "Return date must be after rental date");
                    return;
                }

                // Calculate days and price
                long days = ChronoUnit.DAYS.between(startDate, endDate);
                double totalPrice = vehicle.getPricePerDay() * days;

                // Create rental
                Rental rental = new Rental(
                    startDate.toString(),
                    endDate.toString(),
                    totalPrice,
                    0,
                    0  // Start with no discount
                );

                if (system.addRental(rental, plate, custId)) {
            // Show receipt
            String receipt = Receipt.generateReceipt(rental, customer, vehicle);
            JTextArea receiptArea = new JTextArea(receipt);
            receiptArea.setEditable(false);
            
            // Add feedback button to receipt dialog
            JButton feedbackButton = new JButton("Submit Feedback");
            feedbackButton.addActionListener(fbEvent -> {
                new FeedbackForm(system, custId, plate);
            });
            
            JPanel receiptPanel = new JPanel(new BorderLayout());
            receiptPanel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);
            receiptPanel.add(feedbackButton, BorderLayout.SOUTH);
            
            JOptionPane.showMessageDialog(frame, receiptPanel, 
                "Rental Created Successfully", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            } else {
                    JOptionPane.showMessageDialog(frame, "Failed to create rental");
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}