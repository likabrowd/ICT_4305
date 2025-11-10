package parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

public class ParkingOfficeTest {

    @Test
    public void testGetCustomerIdsAndPermits() {
        // Create ParkingOffice
        ParkingOffice office = new ParkingOffice("DU Parking", new Address("2199 S. University Blvd.", "Denver", "CO", "80208"));

        // Correct variable names for customers
        Customer may = new Customer("May", new Address("123 Maple St.", "Denver", "CO", "80203"), "303-111-0001");
        Customer bill = new Customer("Bill", new Address("456 Oak Ave.", "Denver", "CO", "80204"), "303-222-0002");

        office.addCustomer(may);
        office.addCustomer(bill);

        // Create Cars
        Car mayCar = new Car("ABC-123", CarType.COMPACT, may);
        Car billCar = new Car("XYZ-999", CarType.SUV, bill);

        // Issue Permits
        Permit p1 = new Permit();
        Permit p2 = new Permit();
        mayCar.setPermit(p1);
        billCar.setPermit(p2);

        // Add cars to their customers
        may.addCar(mayCar);
        bill.addCar(billCar);

        // Add cars to ParkingOffice
        office.addCar(mayCar);
        office.addCar(billCar);

        // Test customer IDs
        Collection<String> cIds = office.getCustomerIds();
        assertTrue(cIds.contains(may.getCustomerId()));
        assertTrue(cIds.contains(bill.getCustomerId()));

        // Test all permits
        Collection<String> allPermits = office.getPermitIds();
        assertTrue(allPermits.contains(p1.getPermitId()));
        assertTrue(allPermits.contains(p2.getPermitId()));

        // Test permits for a specific customer
        Collection<String> mayPermits = office.getPermitIds(may);
        assertEquals(1, mayPermits.size());
        assertTrue(mayPermits.contains(p1.getPermitId()));

        // Equals/hashCode basic checks
        Customer copyOfMay = new Customer(may.getName(), may.getAddress(), "303-111-0001");
        // copyOfMay has a different generated ID, therefore is not equal
        assertNotEquals(may, copyOfMay);

        // License-based equality for cars
        Car mayCar2 = new Car("ABC-123", CarType.COMPACT, may);
        assertEquals(mayCar, mayCar2);
    }
}
