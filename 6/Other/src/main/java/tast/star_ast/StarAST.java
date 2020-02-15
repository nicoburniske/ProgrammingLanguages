package tast.star_ast;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import tast.TAST;
import type.Type;

/**
 * TODO: add data definition
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
        jo.put("type", type);
        return jo.toJSONString();
    }
}
