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
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("User Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Menu panel
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Menu buttons
        JButton viewVehiclesButton = new JButton("View Available Vehicles");
        viewVehiclesButton.addActionListener(e -> viewAvailableVehicles());

        JButton rentVehicleButton = new JButton("Rent a Vehicle");
        rentVehicleButton.addActionListener(e -> new AddRentalForm(system));

        JButton giveFeedbackButton = new JButton("Give Feedback");
        giveFeedbackButton.addActionListener(e -> new FeedbackForm(system, customerId, null));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> frame.dispose());

        menuPanel.add(viewVehiclesButton);
        menuPanel.add(rentVehicleButton);
        menuPanel.add(giveFeedbackButton);
        menuPanel.add(logoutButton);

        panel.add(menuPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void viewAvailableVehicles() {
        StringBuilder sb = new StringBuilder("Available Vehicles:\n\n");
        for (Vehicle v : system.getVehicleManager().getAvailableVehicles()) {
            sb.append(v.toString()).append("\n\n");
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(frame, scrollPane, "Available Vehicles", JOptionPane.INFORMATION_MESSAGE);
    }
}