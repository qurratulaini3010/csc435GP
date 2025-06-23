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

        // Title
        JLabel titleLabel = new JLabel("Vehicle Rental System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        homePanel.add(titleLabel, BorderLayout.NORTH);

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel infoLabel = new JLabel("<html><center><h2>Welcome to Vehicle Rental System</h2>"
                + "<p>This system allows you to manage vehicle rentals efficiently.</p>"
                + "<p>Features include:</p>"
                + "<ul>"
                + "<li>Vehicle management</li>"
                + "<li>Customer registration</li>"
                + "<li>Rental processing</li>"
                + "<li>Reports generation</li>"
                + "</ul></center></html>");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(infoLabel);

        homePanel.add(infoPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        JButton adminButton = new JButton("Admin Login");
        adminButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            system.saveAllData();
            System.exit(0);
        });

        buttonPanel.add(adminButton);
        buttonPanel.add(exitButton);
        homePanel.add(buttonPanel, BorderLayout.SOUTH);
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

    // Updated methods to use Mira's forms
    private void showVehicleManagement() {
        // Show vehicle management options
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
                system.displayVehicles();
                break;
            default:
                // Back or closed
                break;
        }
    }

    private void showCustomerManagement() {
        // Show customer management options
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
                system.displayCustomers();
                break;
            default:
                // Back or closed
                break;
        }
    }

    private void showRentalManagement() {
        // Show rental management options
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
                system.displayRentals();
                break;
            default:
                // Back or closed
                break;
        }
    }

    private void showReports() {
        // Show report generation options
        Object[] options = {"Vehicle Report", "Customer Report", "Rental Report", "Back"};
        int choice = JOptionPane.showOptionDialog(mainFrame,
                "Generate Reports",
                "Report Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                system.displayVehicles();
                JOptionPane.showMessageDialog(mainFrame, "Vehicle report displayed in console");
                break;
            case 1:
                system.displayCustomers();
                JOptionPane.showMessageDialog(mainFrame, "Customer report displayed in console");
                break;
            case 2:
                system.displayRentals();
                JOptionPane.showMessageDialog(mainFrame, "Rental report displayed in console");
                break;
            default:
                // Back or closed
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainSystem system = new MainSystem();
            new MainGUI(system);
        });
    }
}
