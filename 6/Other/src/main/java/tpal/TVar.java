package tpal;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import type.Type;

public class TVar  extends TPALVar implements JSONAware {
    //String var;
    Type type;

    public TVar(String var, Type type) {
        super(var);
        this.type = type;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add(super.var);
        arr.add(":");
        arr.add(type);
        return arr.toJSONString();
    }
}
