package value;

import org.json.simple.JSONArray;
import utils.exceptions.ArrayIndexException;
import utils.store.Store;

import java.math.BigInteger;
import java.util.List;

public class IValueArray implements IValue {

    //let y = [x1,x2,x3]
    //[y] Var
    //[0] Loc
    //[0,    1 ,2 ,3 ] Loc
    //[[1,3],x1,x2,x3] IValue
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
        if (i.getValue().min(length).equals(length)) {
            throw new ArrayIndexException();
        } else {
            return store.get(new Location(i.getValue().add(location.getLocation())));
        }
    }

    public Location getLocation(IValueInt i) {
        if (i.getValue().min(length).equals(length)) {
            throw new ArrayIndexException();
        } else {
            return new Location(location.getLocation().add(i.getValue()));
        }
    }

    @Override
    public String toString() {
        return "IValueArray{" +
                "location=" + location +
                ", lenght=" + length +
                '}';
    }

    @Override
    public String toJSONString() {
        //todo fix this
        JSONArray arr = new JSONArray();
        arr.add(this.length);
        arr.add(this.location);
        return arr.toJSONString();
    }
}
