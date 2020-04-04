package value;

import utils.EnvStoreTuple;
import utils.store.Store;

import java.util.List;

public class IValueArray implements IValue{


    //let y = [x1,x2,x3]
    //[y]
    //[0]
    //[0,1 ,2 ,3 ]
    //[3,x1,x2,x3]
    private List<Location> locations;

    public IValueArray(List<Location> locations) {
        this.locations = locations;
    }

    public IValue get(IValueInt i, Store store) {
        return store.get(locations.get(i.getValue().intValue()));
    }

    public Location getLocation(IValueInt i) {
        return locations.get(i.getValue().intValue());
    }


}
