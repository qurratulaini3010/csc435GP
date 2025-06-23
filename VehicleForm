import javax.swing.*;
import java.awt.event.*;

public class AddVehicleForm {
    private MainSystem system;

    public AddVehicleForm(MainSystem system) {
        this.system = system;

        JFrame frame = new JFrame("Add Vehicle");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(null);
        frame.add(panel);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(10, 20, 100, 25);
        panel.add(modelLabel);

        JTextField modelField = new JTextField();
        modelField.setBounds(120, 20, 200, 25);
        panel.add(modelField);

        JLabel plateLabel = new JLabel("Plate No:");
        plateLabel.setBounds(10, 60, 100, 25);
        panel.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(120, 60, 200, 25);
        panel.add(plateField);

        JLabel priceLabel = new JLabel("Price/Day:");
        priceLabel.setBounds(10, 100, 100, 25);
        panel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(120, 100, 200, 25);
        panel.add(priceField);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(10, 140, 100, 25);
        panel.add(typeLabel);

        String[] types = {"Car", "Van"};
        JComboBox<String> typeBox = new JComboBox<>(types);
        typeBox.setBounds(120, 140, 200, 25);
        panel.add(typeBox);

        JLabel capacityLabel = new JLabel("Capacity (Van):");
        capacityLabel.setBounds(10, 180, 120, 25);
        panel.add(capacityLabel);

        JTextField capacityField = new JTextField();
        capacityField.setBounds(140, 180, 180, 25);
        capacityField.setEnabled(false); // only for van
        panel.add(capacityField);

        typeBox.addActionListener(e -> {
            String selected = (String) typeBox.getSelectedItem();
            capacityField.setEnabled(selected.equals("Van"));
        });

        JButton addButton = new JButton("Add Vehicle");
        addButton.setBounds(120, 230, 150, 30);
        panel.add(addButton);

        addButton.addActionListener(e -> {
            String model = modelField.getText();
            String plate = plateField.getText();
            double price = Double.parseDouble(priceField.getText());
            String type = (String) typeBox.getSelectedItem();

            Vehicle v;
            if (type.equals("Car")) {
                v = new Car(model, plate, price, true);
            } else {
                int capacity = Integer.parseInt(capacityField.getText());
                v = new Van(model, plate, price, true, capacity);
            }

            system.addVehicle(v);
            JOptionPane.showMessageDialog(frame, "Vehicle added.");
            frame.dispose();
        });

        frame.setVisible(true);
    }
}
