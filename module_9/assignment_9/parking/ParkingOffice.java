package parking;

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
        this.name = name;
        this.address = address;
    }

    public Collection<String> getCustomerIds() {
        return Collections.unmodifiableList(
            customers.stream()
                     .map(Customer::getCustomerId)
                     .collect(Collectors.toList())
        );
    }

    public Collection<String> getPermitIds() {
        return Collections.unmodifiableList(
            cars.stream()
                .filter(c -> c.getPermit() != null)
                .map(c -> c.getPermit().getPermitId())
                .collect(Collectors.toList())
        );
    }

    public Collection<String> getPermitIds(Customer customer) {
        if (customer == null) return Collections.emptyList();
        return Collections.unmodifiableList(
            customer.getCars().stream()
                    .filter(c -> c.getPermit() != null)
                    .map(c -> c.getPermit().getPermitId())
                    .collect(Collectors.toList())
        );
    }

    public void addLot(ParkingLot lot) { lots.add(lot); }
    public void addCustomer(Customer c) { customers.add(c); }
    public void addCar(Car c) { cars.add(c); }
    public void addCharge(ParkingCharge pc) { charges.add(pc); }

    public Optional<Car> findCarByPermit(String permitId) {
        return cars.stream()
                   .filter(c -> c.getPermit() != null && c.getPermit().getPermitId().equals(permitId))
                   .findFirst();
    }

    public List<Customer> getCustomers() { return Collections.unmodifiableList(customers); }
    public List<ParkingCharge> getAllCharges() { return Collections.unmodifiableList(charges); }

    public Customer register(String name, Address address, String phone) {
        Customer c = new Customer(name, address, phone);
        addCustomer(c);
        return c;
    }

    public Car register(Customer customer, String license, CarType type) {
        if (customer == null) throw new IllegalArgumentException("customer is null");
        Car car = new Car(license, type, customer);
        Permit p = new Permit();
        car.setPermit(p);
        customer.addCar(car);
        addCar(car);
        return car;
    }

    public List<ParkingCharge> getChargesForCustomer(Customer customer) {
        if (customer == null) return Collections.emptyList();
        Set<String> permitIds = customer.getCars().stream()
                .filter(c -> c.getPermit() != null)
                .map(c -> c.getPermit().getPermitId())
                .collect(Collectors.toSet());

        return charges.stream()
                .filter(pc -> permitIds.contains(pc.getPermitId()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() { return String.format("ParkingOffice[%s]", name); }
}
