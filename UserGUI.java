// UserGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGUI {
    private JFrame frame;
    private MainSystem system;
    private String customerId;

    public UserGUI(MainSystem system, String customerId) {
        this.system = system;
        this.customerId = customerId;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("User Dashboard - Customer ID: " + customerId);
        frame.setSize(800, 700); // Adjusted size
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 239, 213));

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(255, 239, 213));

        // 1. Title Panel (North)
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 239, 213));
        JLabel titleLabel = new JLabel("USER DASHBOARD", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(205, 92, 92));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // 2. Center Content Panel (Now contains BOTH welcome text AND image)
        JPanel centerPanel = new JPanel(new BorderLayout(0, 20));
        centerPanel.setBackground(new Color(255, 239, 213));

        // Welcome message (NORTH of center panel)
        JLabel welcomeLabel = new JLabel("Hello there! What would you like to do today?", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        welcomeLabel.setForeground(new Color(160, 82, 45));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        centerPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Image panel (CENTER of center panel)
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(new Color(255, 239, 213));
        
        try {
            ImageIcon originalIcon = new ImageIcon("user_dashboard_image.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            JLabel noImageLabel = new JLabel("🚗 Welcome to Vehicle Rental! 🚙");
            noImageLabel.setFont(new Font("Arial", Font.BOLD, 24));
            noImageLabel.setForeground(new Color(205, 92, 92));
            imagePanel.add(noImageLabel);
        }
        
        centerPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 3. Button Panel (South)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.setBackground(new Color(255, 239, 213));

        // Create buttons
        JButton viewVehiclesButton = createStyledButton("View Available Vehicles", new Color(255, 218, 185), new Color(160, 82, 45));
        viewVehiclesButton.addActionListener(e -> viewAvailableVehicles());

        JButton rentVehicleButton = createStyledButton("Rent a Vehicle", new Color(255, 204, 153), new Color(139, 69, 19));
        rentVehicleButton.addActionListener(e -> new AddRentalForm(system));

        JButton giveFeedbackButton = createStyledButton("Give Feedback", new Color(255, 218, 185), new Color(160, 82, 45));
        giveFeedbackButton.addActionListener(e -> new FeedbackForm(system, customerId, null));

        JButton logoutButton = createStyledButton("Logout", new Color(255, 204, 153), new Color(139, 69, 19));
        logoutButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(viewVehiclesButton);
        buttonPanel.add(rentVehicleButton);
        buttonPanel.add(giveFeedbackButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        button.setPreferredSize(new Dimension(200, 60));
        return button;
    }

    private void viewAvailableVehicles() {
        StringBuilder sb = new StringBuilder("Available Vehicles:\n\n");
        for (Vehicle v : system.getVehicleManager().getAvailableVehicles()) {
            sb.append(v.toString()).append("\n\n");
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setBackground(new Color(255, 239, 213));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        
        JOptionPane.showMessageDialog(frame, scrollPane, "Available Vehicles", JOptionPane.INFORMATION_MESSAGE);
    }
}