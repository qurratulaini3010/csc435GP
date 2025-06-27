import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
        homePanel.setBackground(new Color(240, 240, 240));

        // Main content panel (centered)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.setBackground(new Color(240, 240, 240));

        // Title with logo
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(240, 240, 240));
        
        ImageIcon originalLogo = new ImageIcon("car rental.jpg");
        Image scaledLogo = originalLogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        
        JLabel titleLabel = new JLabel("Vehicle Rental System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        
        titlePanel.add(logoLabel);
        titlePanel.add(titleLabel);
        centerPanel.add(titlePanel);

        // System Description
        JTextArea description = new JTextArea(
            "Welcome to our premium vehicle rental service!\n\n" +
            "We offer a wide range of vehicles for all your transportation needs.\n" +
            "Our system provides seamless rental management for both customers and administrators."
        );
        styleTextArea(description);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(description);

        // Vehicle Showcase
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
        addVehicleCard(vehiclesPanel, "lorry.jpg", "New Lorry", "RM450/day");
        
        centerPanel.add(vehiclesPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        JButton adminButton = new JButton("Admin Login");
        styleButton(adminButton);
        adminButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
        
        JButton userButton = new JButton("User Login");
        styleButton(userButton);
        userButton.addActionListener(e -> {
            String customerId = JOptionPane.showInputDialog(mainFrame, "Enter your Customer ID:");
            if (customerId != null && !customerId.trim().isEmpty()) {
                Customer customer = Customer.findById(system.getCustomerList(), customerId);
                if (customer != null) {
                    new UserGUI(system, customerId);
                    mainFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Customer ID not found", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JButton exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(e -> {
            system.saveAllData();
            System.exit(0);
        });
        
        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);
        buttonPanel.add(exitButton);
        
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonPanel);

        // Add everything to main panel
        homePanel.add(centerPanel, BorderLayout.CENTER);
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

        JButton feedbackButton = new JButton("View Feedback");
        feedbackButton.addActionListener(e -> showFeedback());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> cardLayout.show(cardPanel, "Home"));

        menuPanel.add(vehicleButton);
        menuPanel.add(customerButton);
        menuPanel.add(rentalButton);
        menuPanel.add(reportButton);
        menuPanel.add(feedbackButton);
        menuPanel.add(logoutButton);

        adminPanel.add(menuPanel, BorderLayout.CENTER);
    }

    private void showVehicleManagement() {
        Object[] options = {"Add Vehicle", "View Vehicles", "Back"};
        int choice = JOptionPane.showOptionDialog(mainFrame,
                "Vehicle Management",
                "Vehicle Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                new AddVehicleForm(system);
                break;
            case 1:
                displayVehiclesInWindow();
                break;
            default:
                break;
        }
    }

    private void displayVehiclesInWindow() {
        List<Vehicle> vehicles = system.getVehicleManager().getVehicles();
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALL VEHICLES ===\n\n");
        sb.append("Total Vehicles: ").append(vehicles.size()).append("\n");
        sb.append("Available: ").append(system.getVehicleManager().getAvailableVehicles().size()).append("\n\n");
        
        for (Vehicle v : vehicles) {
            sb.append(v.toString()).append("\n\n");
        }
        
        displayTextInScrollableWindow(sb.toString(), "Vehicle List", 600, 400);
    }

    private void showCustomerManagement() {
        Object[] options = {"Add Customer", "View Customers", "Back"};
        int choice = JOptionPane.showOptionDialog(mainFrame,
                "Customer Management",
                "Customer Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                new AddCustomerForm(system);
                break;
            case 1:
                displayCustomersInWindow();
                break;
            default:
                break;
        }
    }

    private void displayCustomersInWindow() {
        List<Customer> customers = system.getCustomerList();
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALL CUSTOMERS ===\n\n");
        sb.append("Total Customers: ").append(customers.size()).append("\n\n");
        
        for (Customer c : customers) {
            sb.append(c.toString()).append("\n\n");
        }
        
        displayTextInScrollableWindow(sb.toString(), "Customer List", 600, 400);
    }

    private void showRentalManagement() {
        Object[] options = {"Create Rental", "View Rentals", "Back"};
        int choice = JOptionPane.showOptionDialog(mainFrame,
                "Rental Management",
                "Rental Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                new AddRentalForm(system);
                break;
            case 1:
                displayRentalsInWindow();
                break;
            default:
                break;
        }
    }

    private void displayRentalsInWindow() {
        List<Rental> rentals = system.getRentalList();
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALL RENTALS ===\n\n");
        sb.append("Total Rentals: ").append(rentals.size()).append("\n");
        
        long activeRentals = rentals.stream().filter(r -> !r.isReturned()).count();
        sb.append("Active Rentals: ").append(activeRentals).append("\n\n");
        
        for (Rental r : rentals) {
            sb.append(r.toString()).append("\n\n");
        }
        
        displayTextInScrollableWindow(sb.toString(), "Rental List", 600, 400);
    }

    private void showReports() {
        JDialog reportDialog = new JDialog(mainFrame, "Generate Reports", true);
        reportDialog.setSize(400, 300);
        reportDialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton vehicleReportBtn = new JButton("Vehicle Report");
        vehicleReportBtn.addActionListener(e -> {
            generateVehicleReport();
            reportDialog.dispose();
        });
        
        JButton rentalReportBtn = new JButton("Rental Report");
        rentalReportBtn.addActionListener(e -> {
            generateRentalReport();
            reportDialog.dispose();
        });
        
        JButton financialReportBtn = new JButton("Financial Report");
        financialReportBtn.addActionListener(e -> {
            generateFinancialReport();
            reportDialog.dispose();
        });
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> reportDialog.dispose());
        
        panel.add(vehicleReportBtn);
        panel.add(rentalReportBtn);
        panel.add(financialReportBtn);
        panel.add(cancelBtn);
        
        reportDialog.add(panel, BorderLayout.CENTER);
        reportDialog.setLocationRelativeTo(mainFrame);
        reportDialog.setVisible(true);
    }

    private void generateVehicleReport() {
        List<Vehicle> vehicles = system.getVehicleManager().getVehicles();
        StringBuilder report = new StringBuilder("=== VEHICLE REPORT ===\n\n");
        report.append("Total Vehicles: ").append(vehicles.size()).append("\n");
        report.append("Available: ").append(system.getVehicleManager().getAvailableVehicles().size()).append("\n\n");
        
        report.append("=== Vehicle Types ===\n");
        report.append("Cars: ").append(countVehiclesByType(vehicles, "Car")).append("\n");
        report.append("Vans: ").append(countVehiclesByType(vehicles, "Van")).append("\n");
        report.append("Buses: ").append(countVehiclesByType(vehicles, "Bus")).append("\n");
        report.append("Lorries: ").append(countVehiclesByType(vehicles, "Lorry")).append("\n\n");
        
        report.append("=== Vehicle Details ===\n");
        for (Vehicle v : vehicles) {
            report.append(v.toString()).append("\n\n");
        }
        
        displayTextInScrollableWindow(report.toString(), "Vehicle Report", 700, 500);
    }

    private int countVehiclesByType(List<Vehicle> vehicles, String type) {
        return (int) vehicles.stream()
                .filter(v -> v.getClass().getSimpleName().equals(type))
                .count();
    }

    private void generateRentalReport() {
        List<Rental> rentals = system.getRentalList();
        StringBuilder report = new StringBuilder("=== RENTAL REPORT ===\n\n");
        report.append("Total Rentals: ").append(rentals.size()).append("\n");
        
        long activeRentals = rentals.stream().filter(r -> !r.isReturned()).count();
        report.append("Active Rentals: ").append(activeRentals).append("\n");
        
        double totalRevenue = rentals.stream().mapToDouble(Rental::calculateFinalAmount).sum();
        report.append("Total Revenue: RM").append(String.format("%.2f", totalRevenue)).append("\n\n");
        
        report.append("=== Rental Details ===\n");
        for (Rental r : rentals) {
            report.append(r.toString()).append("\n\n");
        }
        
        displayTextInScrollableWindow(report.toString(), "Rental Report", 700, 500);
    }

    private void generateFinancialReport() {
        List<Rental> rentals = system.getRentalList();
        StringBuilder report = new StringBuilder("=== FINANCIAL REPORT ===\n\n");
        
        double totalRevenue = rentals.stream().mapToDouble(Rental::getTotalPrice).sum();
        double totalDiscounts = rentals.stream().mapToDouble(Rental::getDiscount).sum();
        double totalPenalties = rentals.stream().mapToDouble(Rental::getPenalty).sum();
        double netRevenue = totalRevenue + totalPenalties - totalDiscounts;
        
        report.append("Total Rental Income: RM").append(String.format("%.2f", totalRevenue)).append("\n");
        report.append("Total Discounts Given: RM").append(String.format("%.2f", totalDiscounts)).append("\n");
        report.append("Total Penalties Collected: RM").append(String.format("%.2f", totalPenalties)).append("\n");
        report.append("Net Revenue: RM").append(String.format("%.2f", netRevenue)).append("\n\n");
        
        report.append("=== Financial Summary ===\n");
        report.append("Average Rental Income: RM").append(String.format("%.2f", totalRevenue/rentals.size())).append("\n");
        report.append("Average Discount: RM").append(String.format("%.2f", totalDiscounts/rentals.size())).append("\n");
        report.append("Average Penalty: RM").append(String.format("%.2f", totalPenalties/rentals.size())).append("\n");
        
        displayTextInScrollableWindow(report.toString(), "Financial Report", 600, 400);
    }

    private void showFeedback() {
        List<Feedback> feedbackList = system.getFeedbackList();
        StringBuilder sb = new StringBuilder("=== CUSTOMER FEEDBACK ===\n\n");
        sb.append("Total Feedback Entries: ").append(feedbackList.size()).append("\n\n");
        
        if (feedbackList.isEmpty()) {
            sb.append("No feedback available.\n");
        } else {
            double avgRating = feedbackList.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0);
            
            sb.append("Average Rating: ").append(String.format("%.1f", avgRating)).append("/5\n\n");
            
            for (Feedback f : feedbackList) {
                sb.append(f.toString()).append("\n\n");
            }
        }
        
        displayTextInScrollableWindow(sb.toString(), "Customer Feedback", 700, 500);
    }

    private void displayTextInScrollableWindow(String text, String title, int width, int height) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(width, height));
        
        JOptionPane.showMessageDialog(mainFrame, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
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
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainSystem system = new MainSystem();
            new MainGUI(system);
        });
    }
}