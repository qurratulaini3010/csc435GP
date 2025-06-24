import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private String rentalDate;
    private String returnDate;
    private double totalPrice;
    private double penalty;
    private double discount;
    private String customerId;
    private String plateNumber;
    private boolean isLate;
    private boolean isReturned;
    private boolean hasDamage;
    private boolean isPaid;
    private int rentalDays;
    
    public static final double WEEKLY_DISCOUNT = 0.10;  // 10% for rentals >7 days
    public static final double MONTHLY_DISCOUNT = 0.15; // 15% for rentals >30 days
    public static final double LOYALTY_DISCOUNT = 0.05; // 5% for loyal customers
    
    // Penalty rates
    public static final double LATE_PENALTY_PER_DAY = 20.0;
    public static final double DAMAGE_PENALTY = 100.0;
    
    public Rental(String rentalDate, String returnDate, double totalPrice, double penalty, double discount) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
        this.penalty = penalty;
        this.discount = discount;
        this.isReturned = false;
        this.isPaid = false;
        this.hasDamage = false;
    }
    
    public Rental(String rentalDate, String plannedReturnDate, double basePricePerDay, 
                 String customerId, String plateNumber) {
        this.rentalDate = rentalDate;
        this.returnDate = plannedReturnDate;
        this.totalPrice = basePricePerDay;
        this.customerId = customerId;
        this.plateNumber = plateNumber;
        this.penalty = 0;
        this.discount = 0;
        this.isLate = false;
        this.isReturned = false;
        this.hasDamage = false;
        this.isPaid = false;
        this.rentalDays = (int) ChronoUnit.DAYS.between(
            LocalDate.parse(rentalDate), 
            LocalDate.parse(plannedReturnDate)
        );
    }
    
    public static double calculateRentalDiscount(int rentalDays, boolean isLoyalCustomer) {
        double discount = 0;
        
        if (rentalDays > 30) {
            discount = MONTHLY_DISCOUNT;
        } else if (rentalDays > 7) {
            discount = WEEKLY_DISCOUNT;
        }
        
        if (isLoyalCustomer) {
            discount += LOYALTY_DISCOUNT;
        }
        
        return Math.min(discount, 0.25); // Cap total discount at 25%
    }
    
    public static double calculateLatePenalty(int lateDays) {
        return lateDays * LATE_PENALTY_PER_DAY;
    }
    
    public static double calculateDamagePenalty(boolean hasDamage) {
        return hasDamage ? DAMAGE_PENALTY : 0;
    }
    
    public void processReturn(String actualReturnDate, boolean hasDamage, boolean isLoyalCustomer) {
        this.returnDate = actualReturnDate;
        this.isReturned = true;
        this.hasDamage = hasDamage;
        
        LocalDate start = LocalDate.parse(rentalDate);
        LocalDate plannedEnd = LocalDate.parse(returnDate);
        LocalDate actualEnd = LocalDate.parse(actualReturnDate);
        
        // Calculate actual rental days
        this.rentalDays = (int) ChronoUnit.DAYS.between(start, actualEnd);
        
        // Check if late
        if (actualEnd.isAfter(plannedEnd)) {
            int lateDays = (int) ChronoUnit.DAYS.between(plannedEnd, actualEnd);
            this.isLate = true;
            this.penalty = calculateLatePenalty(lateDays);
        }
        
        // Calculate discount based on planned rental days
        int plannedDays = (int) ChronoUnit.DAYS.between(start, plannedEnd);
        this.discount = calculateRentalDiscount(plannedDays, isLoyalCustomer) * 
                       totalPrice * plannedDays;
        
        // Add damage penalty if any
        this.penalty += calculateDamagePenalty(hasDamage);
    }
    
    public double calculateFinalAmount() {
        return totalPrice + penalty - discount;
    }

    // Getters and setters
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

    public String getCustomerId() {
        return customerId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public boolean isLate() {
        return isLate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public boolean hasDamage() {
        return hasDamage;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public int getRentalDays() {
        return rentalDays;
    }
    
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    
    @Override
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
            String.valueOf(discount),
            customerId != null ? customerId : "",
            plateNumber != null ? plateNumber : "",
            String.valueOf(isLate),
            String.valueOf(isReturned),
            String.valueOf(hasDamage),
            String.valueOf(isPaid),
            String.valueOf(rentalDays)
        );
    }

    public static Rental fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length <= 5) {
            // Simple rental format
            return new Rental(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Double.parseDouble(parts[4])
            );
        } else {
            // Extended rental format
            Rental rental = new Rental(
                parts[0], 
                parts[1], 
                Double.parseDouble(parts[2]),
                parts[5],
                parts[6]
            );
            
            rental.penalty = Double.parseDouble(parts[3]);
            rental.discount = Double.parseDouble(parts[4]);
            rental.isLate = Boolean.parseBoolean(parts[7]);
            rental.isReturned = Boolean.parseBoolean(parts[8]);
            rental.hasDamage = Boolean.parseBoolean(parts[9]);
            rental.isPaid = Boolean.parseBoolean(parts[10]);
            rental.rentalDays = Integer.parseInt(parts[11]);
            
            return rental;
        }
    }
}