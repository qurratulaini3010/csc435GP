public class Vehicle {
    protected String model;
    protected String plateNumber;
    protected double pricePerDay;
    protected boolean available;
    protected String vehicleType;

    // Constructor
   public Vehicle(String model, String plateNumber, double pricePerDay, boolean available) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.pricePerDay = pricePerDay;
        this.available = available;
        this.vehicleType = "Generic Vehicle";
    }

    // Getters
    public String getModel() {
        return model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setModel(String model) {
        this.model = model;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
     @Override
    public String toString() {
        return String.format("%s [%s] - %s - RM%.2f/day - %s",
                model, plateNumber, vehicleType, pricePerDay,
                available ? "Available" : "Rented");
    }

    public void checkAvailability() {
        if(available) {
          System.out.println(model + " (" + plateNumber + ") is available at RM" + pricePerDay + " per day.");
        } else {
          System.out.println(model + " (" + plateNumber + ") is not available.");
        }
    }

    public String toFileString() {
            return String.join(",",
                this.getClass().getSimpleName(),
                model,
                plateNumber,
                String.valueOf(pricePerDay),
                String.valueOf(available)
            );
        }

    public static Vehicle fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        String type = parts[0];
        String model = parts[1];
        String plateNumber = parts[2];
        double pricePerDay = Double.parseDouble(parts[3]);
        boolean available = Boolean.parseBoolean(parts[4]);
        
        switch (type) {
            case "Car":
                return new Car(model, plateNumber, pricePerDay, available);
            case "Van":
                if (parts.length > 5) {
                    int capacity = Integer.parseInt(parts[5]);
                    return new Van(model, plateNumber, pricePerDay, available, capacity);
                }
                return new Van(model, plateNumber, pricePerDay, available, 0);
            // Add cases for other vehicle types
            default:
                return new Vehicle(model, plateNumber, pricePerDay, available);
        }
    }
}






