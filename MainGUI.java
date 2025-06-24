import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JFrame mainFrame;
    private MainSystem system;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Panels
    private JPanel homePanel;
    private JPanel loginPanel;
    private JPanel adminPanel;

    public MainGUI(MainSystem system) {
        this.system = system;
        initialize();
    }

    private void initialize() {
        // Main frame setup
        mainFrame = new JFrame("Vehicle Rental System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);

        // Card layout for switching between panels
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels
        createHomePanel();
        createLoginPanel();
        createAdminPanel();

        // Add panels to card layout
        cardPanel.add(homePanel, "Home");
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(adminPanel, "Admin");

        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
    }

    private void createHomePanel() {
    homePanel = new JPanel(new BorderLayout());
    homePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    homePanel.setBackground(new Color(240, 240, 240)); // Light gray background

    // Main content panel (centered)
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.setBackground(new Color(240, 240, 240));

    // 1. Title with logo
    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    titlePanel.setBackground(new Color(240, 240, 240));
    
    // Load and resize logo
    ImageIcon originalLogo = new ImageIcon("car rental.jpg");
    Image scaledLogo = originalLogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
    
    JLabel titleLabel = new JLabel("Vehicle Rental System");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
    
    titlePanel.add(logoLabel);
    titlePanel.add(titleLabel);
    centerPanel.add(titlePanel);

    // 2. System Description
    JTextArea description = new JTextArea(
        "Welcome to our premium vehicle rental service!\n\n" +
        "We offer a wide range of vehicles for all your transportation needs.\n" +
        "Our system provides seamless rental management for both customers and administrators."
    );
    styleTextArea(description);
    centerPanel.add(Box.createVerticalStrut(20));
    centerPanel.add(description);

    // 3. Vehicle Showcase
    centerPanel.add(Box.createVerticalStrut(30));
    JLabel vehiclesTitle = new JLabel("Our Fleet", JLabel.CENTER);
    vehiclesTitle.setFont(new Font("Arial", Font.BOLD, 22));
    vehiclesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(vehiclesTitle);

    JPanel vehiclesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
    vehiclesPanel.setBackground(new Color(240, 240, 240));
    
    // Add vehicle cards
    addVehicleCard(vehiclesPanel, "car.jpg", "Economy Car", "RM150/day");
    addVehicleCard(vehiclesPanel, "van.jpg", "Family Van", "RM250/day");
    addVehicleCard(vehiclesPanel, "bus.jpg", "Tourist Bus", "RM350/day");
    
    centerPanel.add(vehiclesPanel);

    // 4. Button Panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
    buttonPanel.setBackground(new Color(240, 240, 240));
    
    JButton adminButton = new JButton("Admin Login");
    styleButton(adminButton);
    adminButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
    
    JButton exitButton = new JButton("Exit");
    styleButton(exitButton);
    exitButton.addActionListener(e -> {
        system.saveAllData();
        System.exit(0);
    });
    
    buttonPanel.add(adminButton);
    buttonPanel.add(exitButton);
    
    centerPanel.add(Box.createVerticalStrut(20));
    centerPanel.add(buttonPanel);

    // Add everything to main panel
    homePanel.add(centerPanel, BorderLayout.CENTER);
}

// Helper methods
private void addVehicleCard(JPanel parent, String imagePath, String title, String price) {
    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200)),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));
    card.setBackground(Color.WHITE);
    card.setMaximumSize(new Dimension(250, 300));

    // Image
    ImageIcon originalIcon = new ImageIcon(imagePath);
    Image scaledImage = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
    JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Title
    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Price
    JLabel priceLabel = new JLabel(price);
    priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    card.add(Box.createVerticalStrut(10));
    card.add(imageLabel);
    card.add(Box.createVerticalStrut(15));
    card.add(titleLabel);
    card.add(Box.createVerticalStrut(5));
    card.add(priceLabel);
    
    parent.add(card);
}

private void styleTextArea(JTextArea area) {
    area.setEditable(false);
    area.setLineWrap(true);
    area.setWrapStyleWord(true);
    area.setBackground(new Color(240, 240, 240));
    area.setFont(new Font("Arial", Font.PLAIN, 16));
    area.setAlignmentX(Component.CENTER_ALIGNMENT);
    area.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
}

private void styleButton(JButton button) {
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setBackground(new Color(70, 130, 180)); // Steel blue
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setPreferredSize(new Dimension(120, 40));
}
    private void createLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Admin Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        JTextField userText = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordText, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            
            // Simple authentication (in real system, use proper authentication)
            if (username.equals("admin") && password.equals("admin123")) {
                cardLayout.show(cardPanel, "Admin");
            } else {
                JOptionPane.showMessageDialog(loginPanel, 
                    "Invalid username or password", 
                    "Login Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home"));

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(buttonPanel, gbc);
    }

    private void createAdminPanel() {
        adminPanel = new JPanel(new BorderLayout());
        adminPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminPanel.add(titleLabel, BorderLayout.NORTH);

        // Menu panel
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Create buttons for each function
        JButton vehicleButton = new JButton("Manage Vehicles");
        vehicleButton.addActionListener(e -> showVehicleManagement());

        JButton customerButton = new JButton("Manage Customers");
        customerButton.addActionListener(e -> showCustomerManagement());

        JButton rentalButton = new JButton("Manage Rentals");
        rentalButton.addActionListener(e -> showRentalManagement());

        JButton reportButton = new JButton("Generate Reports");
        reportButton.addActionListener(e -> showReports());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Home");
            // Clear any sensitive data if needed
        });

        menuPanel.add(vehicleButton);
        menuPanel.add(customerButton);
        menuPanel.add(rentalButton);
        menuPanel.add(reportButton);
        menuPanel.add(logoutButton);

        adminPanel.add(menuPanel, BorderLayout.CENTER);
    }

    // Placeholder methods for different functionalities
    private void showVehicleManagement() {
        // This would open a new dialog or switch to a vehicle management panel
        VehicleManagementDialog dialog = new VehicleManagementDialog(mainFrame, system);
        dialog.setVisible(true);
    }

    private void showCustomerManagement() {
        CustomerManagementDialog dialog = new CustomerManagementDialog(mainFrame, system);
        dialog.setVisible(true);
    }

    private void showRentalManagement() {
        RentalManagementDialog dialog = new RentalManagementDialog(mainFrame, system);
        dialog.setVisible(true);
    }

    private void showReports() {
        ReportDialog dialog = new ReportDialog(mainFrame, system);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainSystem system = new MainSystem();
            new MainGUI(system);
        });
    }
}

class VehicleManagementDialog extends JDialog {
    public VehicleManagementDialog(JFrame parent, MainSystem system) {
        super(parent, "Vehicle Management", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        
        // Add components for vehicle management
        JButton addButton = new JButton("Add Vehicle");
        addButton.addActionListener(e -> {
            AddVehicleDialog addDialog = new AddVehicleDialog(this, system);
            addDialog.setVisible(true);
        });

        JButton viewButton = new JButton("View Vehicles");
        viewButton.addActionListener(e -> system.displayVehicles());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        add(panel);
    }
}

class AddVehicleDialog extends JDialog {
    public AddVehicleDialog(JDialog parent, MainSystem system) {
        super(parent, "Add New Vehicle", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form fields
        panel.add(new JLabel("Model:"));
        JTextField modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Plate Number:"));
        JTextField plateField = new JTextField();
        panel.add(plateField);

        panel.add(new JLabel("Price Per Day:"));
        JTextField priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Vehicle Type:"));
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Car", "Van", "Lorry", "Bus"});
        panel.add(typeCombo);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            // Get values and add vehicle
            String model = modelField.getText();
            String plate = plateField.getText();
            double price = Double.parseDouble(priceField.getText());
            String type = (String) typeCombo.getSelectedItem();

            Vehicle vehicle;
            switch (type) {
                case "Car":
                    vehicle = new Car(model, plate, price, true);
                    break;
                case "Van":
                    vehicle = new Van(model, plate, price, true, 0); // Default capacity
                    break;
                case "Lorry":
                    vehicle = new Lorry(model, plate, price, true);
                    break;
                case "Bus":
                    vehicle = new Bus(model, plate, price, true);
                    break;
                default:
                    vehicle = new Vehicle(model, plate, price, true);
            }

            system.addVehicle(vehicle);
            JOptionPane.showMessageDialog(this, "Vehicle added successfully!");
            dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

class CustomerManagementDialog extends JDialog {
    public CustomerManagementDialog(JFrame parent, MainSystem system) {
        super(parent, "Customer Management", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        
        // Add components for customer management
        JButton addButton = new JButton("Add Customer");
        addButton.addActionListener(e -> {
            AddCustomerDialog addDialog = new AddCustomerDialog(this, system);
            addDialog.setVisible(true);
        });

        JButton viewButton = new JButton("View Customers");
        viewButton.addActionListener(e -> system.displayCustomers());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        add(panel);
    }
}

class AddCustomerDialog extends JDialog {
    public AddCustomerDialog(JDialog parent, MainSystem system) {
        super(parent, "Add New Customer", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form fields
        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Address:"));
        JTextField addressField = new JTextField();
        panel.add(addressField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            // Get values and add customer
            String name = nameField.getText();
            String id = idField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            system.addCustomer(new Customer(name, id, phone, address));
            JOptionPane.showMessageDialog(this, "Customer added successfully!");
            dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

class RentalManagementDialog extends JDialog {
    public RentalManagementDialog(JFrame parent, MainSystem system) {
        super(parent, "Rental Management", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        
        // Add components for rental management
        JButton addButton = new JButton("Create Rental");
        addButton.addActionListener(e -> {
            AddRentalDialog addDialog = new AddRentalDialog(this, system);
            addDialog.setVisible(true);
        });

        JButton viewButton = new JButton("View Rentals");
        viewButton.addActionListener(e -> system.displayRentals());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        add(panel);
    }
}

class AddRentalDialog extends JDialog {
    public AddRentalDialog(JDialog parent, MainSystem system) {
        super(parent, "Create New Rental", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form fields
        panel.add(new JLabel("Customer ID:"));
        JTextField customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("Vehicle Plate:"));
        JTextField plateField = new JTextField();
        panel.add(plateField);

        panel.add(new JLabel("Rental Date:"));
        JTextField rentalDateField = new JTextField();
        panel.add(rentalDateField);

        panel.add(new JLabel("Return Date:"));
        JTextField returnDateField = new JTextField();
        panel.add(returnDateField);

        panel.add(new JLabel("Daily Rate:"));
        JTextField rateField = new JTextField();
        panel.add(rateField);

        panel.add(new JLabel("Discount:"));
        JTextField discountField = new JTextField("0");
        panel.add(discountField);

        JButton createButton = new JButton("Create Rental");
        createButton.addActionListener(e -> {
            // Get values and create rental
            String customerId = customerIdField.getText();
            String plate = plateField.getText();
            String rentalDate = rentalDateField.getText();
            String returnDate = returnDateField.getText();
            double rate = Double.parseDouble(rateField.getText());
            double discount = Double.parseDouble(discountField.getText());

            // Simple calculation - in real system, calculate days between dates
            double totalPrice = rate * 1; 

            Rental rental = new Rental(rentalDate, returnDate, totalPrice, 0, discount);
            if (system.addRental(rental, plate, customerId)) {
                JOptionPane.showMessageDialog(this, "Rental created successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to create rental. Check vehicle availability or customer ID.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

class ReportDialog extends JDialog {
    public ReportDialog(JFrame parent, MainSystem system) {
        super(parent, "Generate Reports", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        
        // Add components for report generation
        JButton vehicleReportButton = new JButton("Vehicle Report");
        vehicleReportButton.addActionListener(e -> {
            system.displayVehicles();
            JOptionPane.showMessageDialog(this, "Vehicle report displayed in console");
        });

        JButton customerReportButton = new JButton("Customer Report");
        customerReportButton.addActionListener(e -> {
            system.displayCustomers();
            JOptionPane.showMessageDialog(this, "Customer report displayed in console");
        });

        JButton rentalReportButton = new JButton("Rental Report");
        rentalReportButton.addActionListener(e -> {
            system.displayRentals();
            JOptionPane.showMessageDialog(this, "Rental report displayed in console");
        });

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        buttonPanel.add(vehicleReportButton);
        buttonPanel.add(customerReportButton);
        buttonPanel.add(rentalReportButton);

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);
    }
}
