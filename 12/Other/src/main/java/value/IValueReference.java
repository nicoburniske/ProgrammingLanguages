package value;

import utils.store.Store;

import java.util.Objects;
import java.util.Set;

public class IValueReference implements IValue {
    private Location loc;

    public IValueReference(Location loc) {
        this.loc = loc;
    }

    public Location getLoc() {
        return loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IValueReference that = (IValueReference) o;
        return Objects.equals(loc, that.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loc);
    }

    @Override
    public String toString() {
        return "IValueReference{" +
                "loc=" + loc +
                '}';
    }

    @Override
    public String toOutputString(Store store, Set<IValue> acc) {
        return store.get(loc).toOutputString(store,acc);
    }

    @Override
    public String toJSONString() {
        return "ref" + loc.toString();
    }
}
