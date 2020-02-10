package store;

import org.json.simple.JSONAware;

public class Location implements JSONAware {
    private int i;

    public Location(int i){
        this.i = i;
    }

    public int getI() {
        return i;
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

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return i + "";
    }

    @Override
    public String toJSONString() {
        return i + "";
    }
}
