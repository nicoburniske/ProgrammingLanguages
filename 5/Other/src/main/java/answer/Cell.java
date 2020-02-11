package answer;

import fvexpr.SFVExpr;
import fvexpr.Var;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import store.Location;
import store.Store;

/**
 * The cell class. This class implements SFVExpr, this is solely because we need to be able to call
 * interpret on it when it is a expression.
 */
public class Cell  implements JSONAware, SFVExpr {
    private Location l;

    /**
     * A constructor for a {@link Cell}
     * @param l the location the cell holds
     */
    public Cell(Location l) {
        this.l = l;
    }

    /**
     *
     * @return the location
     */
    public Location getLocation() {
        return l;
    }

    /**
     * This functions converts {@link Cell} into JSON for
     * printing using the {@link JSONAware} library
     * @return A JSON formatted String
     */
    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("cell");
        ret.add(l.toJSONString());
        return ret.toJSONString();
    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        return new AnswerCell(this);
    }
}
