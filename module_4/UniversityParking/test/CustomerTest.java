import org.junit.Test;
import static org.junit.Assert.*;  // JUnit 4 assertions

public class CustomerTest {

    @Test
    public void testRegisterCar() {
        Address address = new Address("321 E. 94th Street", "", "Denver", "CO", "80208");
        Customer customer = new Customer("C212", "Kalika Browder", address, "303-123-4567");
        Car car = customer.register("ABC123", CarType.COMPACT);

        assertEquals(1, customer.getCars().size());
        assertEquals("C212", car.getOwner());
        assertEquals(CarType.COMPACT, car.getType());
    }
}
