import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedbackForm {
    private MainSystem system;
    private String customerId;
    private String vehiclePlate;

    public FeedbackForm(MainSystem system, String customerId, String vehiclePlate) {
        this.system = system;
        this.customerId = customerId;
        this.vehiclePlate = vehiclePlate;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Submit Feedback");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Rating
        JLabel ratingLabel = new JLabel("Rating (1-5 stars):");
        JComboBox<Integer> ratingCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        
        // Comments
        JLabel commentsLabel = new JLabel("Comments:");
        JTextArea commentsArea = new JTextArea(4, 20);
        commentsArea.setLineWrap(true);
        JScrollPane commentsScroll = new JScrollPane(commentsArea);

        // Submit button
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.addActionListener(e -> {
            int rating = (int) ratingCombo.getSelectedItem();
            String comments = commentsArea.getText().trim();
            
            if (comments.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your comments");
                return;
            }

            String feedbackId = "FB" + System.currentTimeMillis();
            Feedback feedback = new Feedback(
                feedbackId, 
                customerId, 
                comments, 
                rating, 
                vehiclePlate
            );
            
            system.addFeedback(feedback); // This will trigger saving to file
            JOptionPane.showMessageDialog(frame, "Thank you for your feedback!");
            frame.dispose();
        });

        panel.add(ratingLabel);
        panel.add(ratingCombo);
        panel.add(commentsLabel);
        panel.add(commentsScroll);
        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}