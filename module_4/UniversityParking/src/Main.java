public class Main {
    public static void main(String[] args) {
        // Example of creating a customer and a car :)
        Address addr = new Address("321 E. 94th Street", "", "Denver", "CO", "80208");
        Customer customer = new Customer("C212", "Kalika Browder", addr, "303-123-4567");
        Car car = customer.register("ABC123", CarType.COMPACT);

        System.out.println(customer);
        System.out.println(car);
    }
}
