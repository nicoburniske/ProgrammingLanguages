package value;

import org.json.simple.JSONArray;
import utils.exceptions.ArrayIndexException;
import utils.store.Store;

import java.math.BigInteger;
import java.util.Set;

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

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public String toJSONString() {
        //todo fix this
        JSONArray arr = new JSONArray();
        arr.add(this.length);
        arr.add(this.location);
        return arr.toJSONString();
    }

    public String toOutputString(Store store, Set<IValue> values) {
        StringBuilder result = new StringBuilder();
        if (values.contains(this)) {
           return "cycle";
        } else {
            values.add(this);
            result.append("[");
            int start = this.location.getLocation().intValue();
            for (int ii = start; ii < start + this.length.intValue(); ii++) {
                if(ii != start) result.append(",");
                result.append(store.get(new Location(ii)).toOutputString(store, values));
            }
            result.append("]");
        }
        return result.toString();
    }

    private boolean isIndexInbouds(IValueInt i) {
        BigInteger zero = new BigInteger("0");
        if(i.getValue().min(length).equals(length) || i.getValue().max(zero).equals(zero)) {
            throw new ArrayIndexException();
        } else {
            return true;
        }
    }
}
