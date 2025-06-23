public class Rental {
    private String rentalDate;
    private String returnDate;
    private double totalPrice;
    private double penalty;
    private double discount;

    public Rental(String rentalDate, String returnDate, double totalPrice, double penalty, double discount) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
        this.penalty = penalty;
        this.discount = discount;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPenalty() {
        return penalty;
    }

    public double getDiscount() {
        return discount;
    }

    public double calculateFinalAmount() {
        return totalPrice + penalty - discount;
    }

    public String toString() {
        return "Rental Date: " + rentalDate + ", Return Date: " + returnDate +
               ", Total Price: RM" + totalPrice + ", Penalty: RM" + penalty +
               ", Discount: RM" + discount + ", Final Amount: RM" + calculateFinalAmount();
    }
    public String toFileString() {
        return String.join(",",
            rentalDate,
            returnDate,
            String.valueOf(totalPrice),
            String.valueOf(penalty),
            String.valueOf(discount)
        );
    }

    public static Rental fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        return new Rental(
            parts[0],
            parts[1],
            Double.parseDouble(parts[2]),
            Double.parseDouble(parts[3]),
            Double.parseDouble(parts[4])
        );
    }
}

