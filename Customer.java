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
} 
