package tast.star_decl;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import tast.star_ast.StarAST;
import tast.TASTVar;

/**
 * TODO: DATA Definition
 */
public class StarDecl implements JSONAware {
    TASTVar name;
    StarAST rhs;

    public StarDecl(TASTVar name, StarAST rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("let");
        arr.add(name);
        arr.add("=");
        arr.add(rhs);
        return arr.toJSONString();
    }
}
