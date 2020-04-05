package value;

import java.math.BigInteger;
import java.util.Objects;

public class Location {
    private BigInteger location;

    public Location(int location) {
        this.location = new BigInteger(String.valueOf(location));
    }

    public Location(BigInteger location) {
        this.location = location;
    }

    public BigInteger getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return location.equals(location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "L:" + location ;
    }
}
