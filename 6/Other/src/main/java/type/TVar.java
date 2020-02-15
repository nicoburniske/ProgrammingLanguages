package type;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import tpal.TPALVar;
import type.Type;

import java.util.Objects;

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
        arr.add(var);
        arr.add(":");
        arr.add(type);
        return arr.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TVar tVar = (TVar) o;
        return type.equals(tVar.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
