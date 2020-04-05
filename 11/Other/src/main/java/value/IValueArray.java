package value;

import ast.expression.Int;
import utils.exceptions.ArrayIndexException;
import utils.store.Store;

import java.math.BigInteger;

public class IValueArray implements IValue {


    //let y = [x1,x2,x3]
    //[y] Var
    //[0] Loc
    //[0,    1 ,2 ,3 ] Loc
    //[[1,3],x1,x2,x3] IValue
    private Location location;
    private BigInteger lenght;

    public IValueArray(Location location, BigInteger lenght) {
        this.location = location;
        this.lenght = lenght;
    }

    public IValueArray(Location location, Integer lenght) {
        this.location = location;
        this.lenght = new BigInteger(lenght.toString());
    }

    public IValue get(IValueInt i, Store store) {
        if (i.getValue().min(lenght).equals(lenght)) {
            throw new ArrayIndexException();
        } else {
            return store.get(new Location(i.getValue().add(location.getLocation())));
        }
    }

    public Location getLocation(IValueInt i) {
        if (i.getValue().min(lenght).equals(lenght)) {
            throw new ArrayIndexException();
        } else {
            return new Location(location.getLocation().add(i.getValue()));
        }
    }

    @Override
    public String toString() {
        return "IValueArray{" +
                "location=" + location +
                ", lenght=" + lenght +
                '}';
    }
}
