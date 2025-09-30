import java.time.LocalDate;
import java.util.UUID;

public class Car {
    private String permit;
    private LocalDate permitExpiration;
    private String license;
    private CarType type;
    private String owner; // Customer ID!

    public Car(String license, CarType type, String owner) {
        this.license = license;
        this.type = type;
        this.owner = owner;
        this.permit = UUID.randomUUID().toString();
        this.permitExpiration = LocalDate.now().plusYears(1);
    }

    public String getPermit() {
        return permit;
    }

    public LocalDate getPermitExpiration() {
        return permitExpiration;
    }

    public String getLicense() {
        return license;
    }

    public CarType getType() {
        return type;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "permit='" + permit + '\'' +
                ", permitExpiration=" + permitExpiration +
                ", license='" + license + '\'' +
                ", type=" + type +
                ", owner='" + owner + '\'' +
                '}';
    }
}
