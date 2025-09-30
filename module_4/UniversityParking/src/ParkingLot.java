import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private String lotId;
    private Address address;
    private int capacity;
    private List<Car> parkedCars;

    public ParkingLot(String lotId, Address address, int capacity) {
        this.lotId = lotId;
        this.address = address;
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
    }

    
    // Fee calculation logic would go here! This depends on entry/exit type. 
    public void entry(Car car) {
        if (parkedCars.size() < capacity) {
            parkedCars.add(car);
        } else {
            throw new IllegalStateException("Parking lot is full");
        }
    }

    public String getLotId() {
        return lotId;
    }

    public Address getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "lotId='" + lotId + '\'' +
                ", address=" + address +
                ", capacity=" + capacity +
                ", parkedCars=" + parkedCars +
                '}';
    }
}
