package tast;

import org.json.simple.JSONArray;
import tast.star_ast.StarAST;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TASTConditional that = (TASTConditional) o;
        return condClause.equals(that.condClause) &&
                ifTrue.equals(that.ifTrue) &&
                ifFalse.equals(that.ifFalse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condClause, ifTrue, ifFalse);
    }
}
