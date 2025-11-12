package parking;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingOffice {
    private final String officeName;
    private final Address address;

    private final List<Customer> customers = new ArrayList<>();
    private final List<Car> cars = new ArrayList<>();
    private final List<ParkingLot> lots = new ArrayList<>();
    private final List<ParkingCharge> charges = new ArrayList<>();

    public ParkingOffice(String officeName, Address address) {
        this.officeName = officeName;
        this.address = address;
    }

  
    public void addCustomer(Customer c) {
        if (c != null && !customers.contains(c)) customers.add(c);
    }

    public void addCar(Car c) {
        if (c != null && !cars.contains(c)) cars.add(c);
    }

    public void addLot(ParkingLot lot) {
        if (lot != null && !lots.contains(lot)) lots.add(lot);
    }

    public List<Customer> getCustomers() { return Collections.unmodifiableList(customers); }
    public List<Car> getCars() { return Collections.unmodifiableList(cars); }
    public List<ParkingLot> getLots() { return Collections.unmodifiableList(lots); }

    public String getOfficeName() { return officeName; }
    public Address getAddress() { return address; }

   
    /** Returns a collection of all customer ids. */
    public Collection<String> getCustomerIds() {
        return customers.stream()
                .map(Customer::getCustomerId)
                .collect(Collectors.toSet());
    }

    // Returns a collection of all permit ids.
    public Collection<String> getPermitIds() {
        return cars.stream()
                .map(Car::getPermit)
                .filter(Objects::nonNull)
                .map(Permit::getPermitId)
                .collect(Collectors.toSet());
    }

    // Returns the collection of permit ids for a specific customer.
    public Collection<String> getPermitIds(Customer customer) {
        if (customer == null) return Collections.emptyList();
        return customer.getCars().stream()
                .map(Car::getPermit)
                .filter(Objects::nonNull)
                .map(Permit::getPermitId)
                .collect(Collectors.toSet());
    }

   
    public Customer register(String name, Address address, String phone) {
        Customer c = new Customer(name, address, phone);
        addCustomer(c);
        return c;
    }

   
    public Car register(Customer customer, String license, CarType type) {
        if (customer == null) throw new IllegalArgumentException("customer null");
        Car car = new Car(license, type, customer);
        Permit p = new Permit();        // Assignment 9: no-arg Permit
        car.setPermit(p);
        customer.addCar(car);
        addCar(car);
        return car;
    }

    // Find a car by its permit id, which is used by ParkingLot to apply charges.
    public Optional<Car> findCarByPermit(String permitId) {
        if (permitId == null) return Optional.empty();
        return cars.stream()
                .filter(c -> c.getPermit() != null && permitId.equals(c.getPermit().getPermitId()))
                .findFirst();
    }

   
    public void addCharge(ParkingCharge pc) {
        if (pc != null) charges.add(pc);
    }

   
    public List<ParkingCharge> getAllCharges() {
        return Collections.unmodifiableList(charges);
    }


    public List<ParkingCharge> getChargesForCustomer(Customer customer) {
        if (customer == null) return Collections.emptyList();
        Set<String> permitIds = customer.getCars().stream()
                .map(Car::getPermit)
                .filter(Objects::nonNull)
                .map(Permit::getPermitId)
                .collect(Collectors.toSet());

        return charges.stream()
                .filter(pc -> permitIds.contains(pc.getPermitId()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("ParkingOffice[%s]", officeName);
    }
}
