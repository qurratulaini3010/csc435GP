import javax.swing.*;
import java.awt.event.*;

public class AddCustomerForm {
    private MainSystem system;  // Reference to your system

    public AddCustomerForm(MainSystem system) {
        this.system = system;

        JFrame frame = new JFrame("Add Customer");
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 25);
        panel.add(nameField);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 60, 80, 25);
        panel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(100, 60, 200, 25);
        panel.add(idField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(10, 100, 80, 25);
        panel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(100, 100, 200, 25);
        panel.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10, 140, 80, 25);
        panel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(100, 140, 200, 25);
        panel.add(addressField);

        JButton addButton = new JButton("Add Customer");
        addButton.setBounds(100, 190, 150, 30);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();

                if (name.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name and ID are required.");
                    return;
                }

                Customer newCustomer = new Customer(name, id, phone, address);
                system.addCustomer(newCustomer);

                JOptionPane.showMessageDialog(frame, "Customer added successfully.");
                frame.dispose(); // Close window
            }
        });

        frame.setVisible(true);
    }
}
