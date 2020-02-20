package typecheck.type;

import typecheck.env.IEnv;
import typecheck.env.Tuple;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import typecheck.tast.TASTVar;
import typecheck.tast.star_ast.StarAST;
import typecheck.tpal.TPALVar;

import java.util.Objects;

/**
 * This represnts a Var that knows its typecheck.type
 */
public class TypedVar extends TPALVar implements JSONAware {
    //String var;
    Type type;

    public TypedVar(String var, Type type) {
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
        TypedVar typedVar = (TypedVar) o;
        return type.equals(typedVar.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public Tuple typeCheck(IEnv<TPALVar, Type> env) {
        return new Tuple(
                new StarAST(new TASTVar(this.var), type),
                env.put(new TPALVar(this.var), this.type));
    }

    public Type getType() {
        return type;
    }
}
