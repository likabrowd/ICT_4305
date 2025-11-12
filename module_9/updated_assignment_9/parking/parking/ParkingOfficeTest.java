package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

public class ParkingOfficeTest {

    @Test
    public void testCustomerAndPermitIdAccessors() {
        Address addr = new Address("2199 S. University Blvd.", "Denver", "CO", "80208");
        ParkingOffice office = new ParkingOffice("DU Parking", addr);

        Customer alice = new Customer("Alice", new Address("1 A St", "Denver", "CO", "80202"), "303-000-0000");
        Customer bob   = new Customer("Bob",   new Address("2 B St", "Denver", "CO", "80203"), "303-111-1111");

      
        office.addCustomer(alice);
        office.addCustomer(bob);

        Car car1 = new Car("ABC-111", CarType.COMPACT, alice);
        Car car2 = new Car("XYZ-222", CarType.SUV, bob);

        Permit p1 = new Permit(); 
        Permit p2 = new Permit();

        car1.setPermit(p1);
        car2.setPermit(p2);

        alice.addCar(car1);
        bob.addCar(car2);

        office.addCar(car1);
        office.addCar(car2);

        // getCustomerIds()
        Collection<String> customerIds = office.getCustomerIds();
        assertTrue(customerIds.contains(alice.getCustomerId()));
        assertTrue(customerIds.contains(bob.getCustomerId()));

        // getPermitIds() (all)
        Collection<String> allPermitIds = office.getPermitIds();
        assertTrue(allPermitIds.contains(p1.getPermitId()));
        assertTrue(allPermitIds.contains(p2.getPermitId()));

        // getPermitIds(Customer)
        Collection<String> alicePermits = office.getPermitIds(alice);
        assertEquals(1, alicePermits.size());
        assertTrue(alicePermits.contains(p1.getPermitId()));
    }
}
