package value;

import org.json.simple.JSONArray;
import utils.exceptions.ArrayIndexException;
import utils.store.Store;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class represents an array in the Store. It is stored as the lenght of the Array and the Location of the Array.
 * The location is purely syntactic as the array is always the location of this element + 1.
 * The elements of the array are always sequentially the next elements in the Store.
 */
public class IValueArray implements IValue {

    private Location location;
    private BigInteger length;

    public IValueArray(Location location, BigInteger length) {
        this.location = location;
        this.length = length;
    }

    public IValueArray(Integer location, Integer length) {
        this(new Location(location), new BigInteger(length.toString()));
    }

    public IValueArray(Location location, Integer length) {
        this.location = location;
        this.length = new BigInteger(length.toString());
    }

    public IValue get(IValueInt i, Store store) {
        isIndexInbouds(i);
        return store.get(new Location(i.getValue().add(location.getLocation())));
    }

    public Location getLocation(IValueInt i) {
        isIndexInbouds(i);
        return new Location(location.getLocation().add(i.getValue()));
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    public BigInteger getLength() {
        return length;
    }

    @Override
    public String toJSONString() {
        //todo fix this
        JSONArray arr = new JSONArray();
        arr.add(this.length);
        arr.add(this.location);
        return arr.toJSONString();
    }

    @Override
    public String toOutputString(Store store, Set<IValue> values) {
        StringBuilder result = new StringBuilder();
        if (values.contains(this)) {
           return "\"cycle\"";
        } else {
            values.add(this);
            result.append("[");
            int start = this.location.getLocation().intValue();
            for (int ii = start; ii < start + this.length.intValue(); ii++) {
                if(ii != start) result.append(",");
                result.append(store.get(new Location(ii)).toOutputString(store, new HashSet<>(values)));
            }
            result.append("]");
        }
        return result.toString();
    }

    /**
     * This function checks to make sure that array access does not occur out of bounds of the array
     * @param i the index to be access
     * @return true if the index is valid. This will throw an Exception if the index is invalid
     */
    private boolean isIndexInbouds(IValueInt i) {
        BigInteger neg = new BigInteger("-1");
        if(i.getValue().min(length).equals(length) || i.getValue().max(neg).equals(neg)) {
            throw new ArrayIndexException();
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IValueArray that = (IValueArray) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(length, that.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, length);
    }
}
