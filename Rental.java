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
}

