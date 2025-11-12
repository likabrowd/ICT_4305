package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testEqualityByLicensePlate() {
        Customer owner = new Customer("Eve", new Address("3 Cold St", "Denver", "CO", "80204"), "720-555-0000");
        Car car1 = new Car("EV-123", CarType.COMPACT, owner);
        Car car2 = new Car("EV-123", CarType.COMPACT, owner);
        assertEquals(car1, car2);
        assertEquals(car1.hashCode(), car2.hashCode());
    }

    @Test
    public void testSetPermit() {
        Customer owner = new Customer("Tom", new Address("4 Den St", "Denver", "CO", "80205"), "720-555-1111");
        Car car = new Car("TUM-100", CarType.SUV, owner);
        Permit p = new Permit(); 
        car.setPermit(p);
        assertEquals(p, car.getPermit());
    }
}
