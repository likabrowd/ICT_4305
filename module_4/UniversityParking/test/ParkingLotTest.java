import org.junit.Test;
import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void testEntry() {
        Address address = new Address("456 Main St", "", "Denver", "CO", "80208");
        ParkingLot lot = new ParkingLot("Lot1", address, 2);
        Customer customer = new Customer("C002", "John Doe", address, "303-876-5432");
        Car car = customer.register("XYZ123", CarType.SUV);

        lot.entry(car);
        assertEquals(1, lot.getParkedCars().size());
        assertEquals(car, lot.getParkedCars().get(0));
    }
}
