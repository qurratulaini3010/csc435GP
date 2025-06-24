import java.util.List;

public class Customer {
    private String name;
    private String id;
    private String phone;
    private String address;

    public Customer(String name, String id, String phone, String address) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Address: " + address;
    }
    
     public String toFileString() {
        return String.join(",",
            name,
            id,
            phone,
            address
        );
    }

    public static Customer fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        return new Customer(parts[0], parts[1], parts[2], parts[3]);
    }
    
    public static Customer findById(List<Customer> customers, String id) {
    for (Customer customer : customers) {
        if (customer.getId().equals(id)) {
            return customer;
        }
    }
    return null;
}
    
}

