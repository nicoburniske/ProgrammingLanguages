package answer;

import fvexpr.SFVExpr;
import fvexpr.Var;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import store.Location;
import store.Store;

public class Cell  implements JSONAware, SFVExpr {
    private Location l;

    public Cell(Location l) {
        this.l = l;
    }

    public Location getLocation() {
        return l;
    }

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
