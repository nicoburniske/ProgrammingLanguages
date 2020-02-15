package tast;

import org.json.simple.JSONArray;
import tast.star_ast.StarAST;

public class TASTConditional implements TAST {
    StarAST condClause;
    StarAST ifTrue;
    StarAST ifFalse;

    public TASTConditional(StarAST condClause, StarAST ifTrue, StarAST ifFalse) {
        this.condClause = condClause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("if-0");
        arr.add(condClause);
        arr.add(ifTrue);
        arr.add(ifFalse);
        return arr.toJSONString();
    }
}
