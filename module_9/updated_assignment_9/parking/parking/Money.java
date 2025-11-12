package parking;

import java.util.Objects;

public final class Money {
    private final long cents;

    public Money(long cents) {
        this.cents = cents;
    }

    public static Money ofDollars(double dollars) {
        return new Money(Math.round(dollars * 100));
    }

    public long getCents() { return cents; }

    public double getDollars() {
        return cents / 100.0;
    }

    public Money plus(Money other) {
        return new Money(this.cents + other.cents);
    }

    public Money minus(Money other) {
        return new Money(this.cents - other.cents);
    }

    public Money times(long factor) {
        return new Money(this.cents * factor);
    }

    @Override
    public String toString() {
        return String.format("$%.2f", getDollars());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money)) return false;
        Money m = (Money)o;
        return this.cents == m.cents;
    }

    @Override
    public int hashCode() { return Objects.hash(cents); }
}
