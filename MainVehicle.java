public class MainVehicle {
    public static void main(String[] args) {
        Van van = new Van("Hyundai Starex", "PBL6266", 250.0, true);
        Lorry lorry = new Lorry("Mitsubishi Fuso", "KCG1592", 450.0, false);
        Bus bus = new Bus("Toyota Hiace", "WBT2593", 500.0, true);
        Car car = new Car("Perodua Bezza", "MKN7362", 200.0, true);

        van.checkAvailability();
        lorry.checkAvailability();
        bus.checkAvailability();
        car.checkAvailability();
    }
}
