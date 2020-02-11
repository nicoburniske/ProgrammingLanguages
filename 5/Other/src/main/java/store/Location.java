package store;

import org.json.simple.JSONAware;

public class Location implements JSONAware {
    private int i;

    /**
     * A constructor to create a new location
     * @param i the integer representing that location
     */
    public Location(int i){
        this.i = i;
    }

    @Override
    public String toString() {
        return i + "";
    }

    /**
     * This functions converts {@link Location} into JSON for
     * printing using the {@link JSONAware} library
     * @return A JSON formatted String
     */
    @Override
    public String toJSONString() {
        return i + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return i == location.i;
    }

    @Override
    public int hashCode() {
        return i;
    }

}
