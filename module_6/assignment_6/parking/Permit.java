package parking;

import java.time.Instant;
import java.util.UUID;

public class Permit {
    private final String permitId = UUID.randomUUID().toString();
    private final Instant issuedAt = Instant.now();
    private boolean active = true;

    public String getPermitId() { return permitId; }
    public Instant getIssuedAt() { return issuedAt; }
    public boolean isActive() { return active; }
    public void revoke() { active = false; }

    @Override public String toString() {
        return String.format("Permit[%s]", permitId);
    }
}
