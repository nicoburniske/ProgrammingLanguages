package value;

import utils.store.Store;

public class IValueArray implements IValue{


    //let y = [x1,x2,x3]
    //[y] Var
    //[0] Loc
    //[0,    1 ,2 ,3 ] Loc
    //[[1,3],x1,x2,x3] IValue
    private Location location;
    private int lenght;

    public IValueArray(Location location, int lenght) {
        this.location = location;
        this.lenght = lenght;
    }

    public IValue get(IValueInt i, Store store) {
        return store.get(new Location(i.getValue().add(location.getLocation())));
    }

    public Location getLocation(IValueInt i) {
        return new Location(location.getLocation().add(i.getValue()));
    }

    @Override
    public String toString() {
        return "IValueArray{" +
                "location=" + location +
                ", lenght=" + lenght +
                '}';
    }
}
