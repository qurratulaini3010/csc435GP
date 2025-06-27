import java.io.Serializable;
import java.time.LocalDateTime;

public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    private String feedbackId;
    private String customerId;
    private String comments;
    private int rating; // e.g., 1 to 5 stars
    private LocalDateTime feedbackDate;
    private String vehiclePlateNumber; // Optional: link feedback to a specific rental/vehicle

    public Feedback(String feedbackId, String customerId, String comments, int rating, String vehiclePlateNumber) {
        this.feedbackId = feedbackId;
        this.customerId = customerId;
        this.comments = comments;
        this.rating = rating;
        this.feedbackDate = LocalDateTime.now(); // Timestamp when feedback is given
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    // Getters
    public String getFeedbackId() {
        return feedbackId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getComments() {
        return comments;
    }

    public int getRating() {
        return rating;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    // Setters (if feedback can be modified)
    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
     public String toFileString() {
        return String.join(",",
            feedbackId,
            customerId,
            comments.replace(",", "\\,"), // Escape commas in comments
            String.valueOf(rating),
            feedbackDate.toString(),
            vehiclePlateNumber != null ? vehiclePlateNumber : ""
        );
    }

    public static Feedback fromFileString(String fileString) {
        String[] parts = fileString.split("(?<!\\\\),"); // Split on unescaped commas
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid feedback data string");
        }
        
        // Unescape commas in comments
        String comments = parts[2].replace("\\,", ",");
        
        Feedback feedback = new Feedback(
            parts[0], 
            parts[1], 
            comments, 
            Integer.parseInt(parts[3]),
            parts[4].isEmpty() ? null : parts[4]
        );
        
        // Parse the date if it exists
        if (parts.length > 5 && !parts[5].isEmpty()) {
            feedback.feedbackDate = LocalDateTime.parse(parts[5]);
        }
        
        return feedback;
    }

    @Override
    public String toString() {
        return "Feedback [ID=" + feedbackId + ", Customer ID=" + customerId +
               ", Vehicle=" + (vehiclePlateNumber != null ? vehiclePlateNumber : "N/A") +
               ", Rating=" + rating + ", Date=" + feedbackDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
               ", Comments='" + comments + "']";
    }
}
