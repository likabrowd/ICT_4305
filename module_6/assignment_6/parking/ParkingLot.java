package parking;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final String lotId;
    private final String name;
    private final ScanType scanType;
    private final Money hourlyRate;
    private final Money dailyRate;
    // track entries for ENTRY_EXIT mode: permitId is entryTime
    private final Map<String, Instant> activeEntries = new HashMap<>();

    public ParkingLot(String lotId, String name, ScanType scanType, Money hourlyRate, Money dailyRate) {
        this.lotId = lotId; this.name = name; this.scanType = scanType; this.hourlyRate = hourlyRate; this.dailyRate = dailyRate;
    }

    public String getLotId() { return lotId; }
    public ScanType getScanType() { return scanType; }

    public boolean entry(String permitId, ParkingOffice office) {
        Instant now = Instant.now();
        if (scanType == ScanType.ENTRY_ONLY) {
            Money charge = hourlyRate;
            Car car = office.findCarByPermit(permitId).orElse(null);
            if (car != null && car.getType() == CarType.COMPACT) {
                charge = Money.ofDollars(charge.getDollars() * 0.8); // 20% discount for compact cars
            }
            ParkingCharge pc = new ParkingCharge(permitId, lotId, now, charge);
            office.addCharge(pc);
            return true;
        } else {
            // ENTRY_EXIT: record entry; charge on exit
            activeEntries.put(permitId, now);
            return true;
        }
    }

    public boolean exit(String permitId, ParkingOffice office) {
        Instant exit = Instant.now();
        Instant entry = activeEntries.remove(permitId);
        if (entry == null) {
            // unknown entry; could be ENTRY_ONLY lot or missing record
            return false;
        }

        long minutes = Duration.between(entry, exit).toMinutes();
        // calculate hours by rounding up
        long hours = Math.max(1, (minutes + 59) / 60);
        Money amount = hourlyRate.times(hours);
        long days = Duration.between(entry, exit).toDays();
        if (days > 0) {
            amount = amount.plus(dailyRate.times(days));
        }
        Car car = office.findCarByPermit(permitId).orElse(null);
        if (car != null && car.getType() == CarType.COMPACT) {
            amount = Money.ofDollars(amount.getDollars() * 0.8);
        }
        ParkingCharge pc = new ParkingCharge(permitId, lotId, exit, amount);
        office.addCharge(pc);
        return true;

    }

    @Override public String toString() {
        return String.format("ParkingLot[%s,%s,%s]", lotId, name, scanType);
    }
}
