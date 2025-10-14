package parking;

public class Car {
    private final String license;
    private final CarType type;
    private final Customer owner;
    private Permit permit;

    public Car(String license, CarType type, Customer owner) {
        this.license = license; this.type = type; this.owner = owner;
    }

    public String getLicense() { return license; }
    public CarType getType() { return type; }
    public Customer getOwner() { return owner; }
    public Permit getPermit() { return permit; }
    public void setPermit(Permit p) { this.permit = p; }

    @Override public String toString() {
        return String.format("Car[%s,%s,%s]", license, type, owner.getName());
    }
}
