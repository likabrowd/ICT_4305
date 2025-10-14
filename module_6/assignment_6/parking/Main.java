package parking;

import java.time.Instant;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Address addr = new Address("2199 S. University Blvd.", "Denver", "CO", "80208");
        ParkingOffice office = new ParkingOffice("DU Parking", addr);

        // create lot: Entry + Exit, $2/hr, $12/day
        ParkingLot lotA = new ParkingLot("L1", "Lot A", ScanType.ENTRY_EXIT, Money.ofDollars(2.0), Money.ofDollars(12.0));
        // create lot: Entry Only, $1.50 entry
        ParkingLot lotB = new ParkingLot("L2", "Lot B", ScanType.ENTRY_ONLY, Money.ofDollars(1.5), Money.ofDollars(15.0));
        office.addLot(lotA); office.addLot(lotB);

        // Register a customer :)
        Customer kalika = office.register("Kalika", addr, "303-123-4567");
        Car kalikaCar = office.register(kalika, "OCD-123", CarType.COMPACT);
        System.out.println("Registered: " + kalika + " with car " + kalikaCar + " permit " + kalikaCar.getPermit());

        // Car enters Lot B (entry-only), causes immediate charge
        boolean enteredB = lotB.entry(kalikaCar.getPermit().getPermitId(), office);
        System.out.println("Entered Lot B (entry-only): " + enteredB);
        office.getAllCharges().forEach(System.out::println);

        // Car enters Lot A (entry_exit), then exits after short wait (simulation)
        boolean enteredA = lotA.entry(kalikaCar.getPermit().getPermitId(), office);
        System.out.println("Entered Lot A (entry-exit): " + enteredA);

        /* simulate parked 70 minutes; emulate by manual call for demo purposes, 
        just calling the exit */  
        Thread.sleep(1000); // short pause so times differ!
        boolean exitedA = lotA.exit(kalikaCar.getPermit().getPermitId(), office);
        System.out.println("Exited Lot A: " + exitedA);

        System.out.println("All charges:");
        office.getAllCharges().forEach(System.out::println);

        System.out.println("Charges for Kalika:");
        office.getChargesForCustomer(kalika).forEach(System.out::println);

    }
}

