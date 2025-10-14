package parking;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class ParkingOffice {
    private final String name;
    private final Address address;

    private final List<Customer> customers = new ArrayList<>();
    private final List<Car> cars = new ArrayList<>();
    private final List<ParkingLot> lots = new ArrayList<>();
    private final List<ParkingCharge> charges = new ArrayList<>();

    public ParkingOffice(String name, Address address) {
        this.name = name; this.address = address;
    }

    public Customer register(String name, Address address, String phone) {
        Customer c = new Customer(name, address, phone);
        customers.add(c);
        return c;
    }

    // register a car for an existing customer +  issue permit
    public Car register(Customer c, String license, CarType t) {
        Car car = new Car(license, t, c);
        Permit permit = new Permit();
        car.setPermit(permit);
        cars.add(car);
        c.addCar(car);
        return car;
    }

    // find first customer by name
    public Customer getCustomer(String name) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public Money addCharge(ParkingCharge pc) {
        charges.add(pc);
        return pc.getAmount();
    }

    public List<ParkingCharge> getChargesForCustomer(Customer c) {
        Set<String> permitIds = c.getCars().stream()
                .map(car -> car.getPermit() != null ? car.getPermit().getPermitId() : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return charges.stream().filter(pc -> permitIds.contains(pc.getPermitId())).collect(Collectors.toList());
    }

    public void addLot(ParkingLot lot) { lots.add(lot); }
    public Optional<Car> findCarByPermit(String permitId) {
        return cars.stream().filter(car -> car.getPermit() != null && permitId.equals(car.getPermit().getPermitId())).findFirst();
    }

    public List<Customer> getCustomers(){ return Collections.unmodifiableList(customers); }
    public List<ParkingCharge> getAllCharges(){ return Collections.unmodifiableList(charges); }

    @Override public String toString() {
        return String.format("ParkingOffice[%s]", name);
    }
}
