package typechecker.tast.star_ast;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import typechecker.tast.TAST;
import typechecker.type.Type;

import java.util.Objects;

/**
 * A StarAST is a JSON object { "expr" : TAST, "type" : Type }.
 */
public class StarAST implements JSONAware {
    TAST expr;
    Type type;

    public StarAST(TAST expr, Type type) {
        this.expr = expr;
        this.type = type;
    }

    @Override
    public String toJSONString() {
        JSONObject jo = new JSONObject();
        jo.put("expr", this.expr);
        jo.put("type", this.type);
        return jo.toJSONString();
    }

    public Type getType() {
        return this.type;
    }

    public TAST getExpr() {
        return this.expr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarAST starAST = (StarAST) o;
        return expr.equals(starAST.expr) &&
                type.equals(starAST.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr, type);
    }

    public String toJava() {
        return this.expr.toJava(this.type);
    }
}
