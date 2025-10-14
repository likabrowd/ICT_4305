package parking;

import java.time.Instant;
import java.util.Objects;

public class ParkingCharge {
    private final String permitId;
    private final String lotId;
    private final Instant incurred;
    private final Money amount;

    public ParkingCharge(String permitId, String lotId, Instant incurred, Money amount) {
        this.permitId = permitId;
        this.lotId = lotId;
        this.incurred = incurred;
        this.amount = amount;
    }

    public String getPermitId() { return permitId; }
    public String getLotId() { return lotId; }
    public Instant getIncurred() { return incurred; }
    public Money getAmount() { return amount; }

    @Override public String toString() {
        return String.format("Charge[permit=%s, lot=%s, time=%s, amt=%s]", permitId, lotId, incurred, amount);
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof ParkingCharge)) return false;
        ParkingCharge p = (ParkingCharge)o;
        return Objects.equals(permitId, p.permitId) && Objects.equals(lotId, p.lotId) && Objects.equals(incurred, p.incurred);
    }
}
