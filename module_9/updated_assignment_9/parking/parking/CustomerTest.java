package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testEqualsAndHashCodeByCustomerId() {
        Address addr = new Address("1 A St", "Denver", "CO", "80202");
        Customer c1 = new Customer("Alice", addr, "303-000-0000");
        Customer c2 = new Customer("Alice", addr, "303-000-0000");

        // Different generated IDs, not equal. 
        assertNotEquals(c1, c2);
        assertNotEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void testAddCarMaintainsOwnership() {
        Customer c = new Customer("Bob", new Address("2 B St", "Denver", "CO", "80203"), "303-111-1111");
        Car car = new Car("XYZ-222", CarType.SUV, c);
        c.addCar(car);
        assertTrue(c.getCars().contains(car));
        assertEquals(c, car.getOwner());
    }
}
