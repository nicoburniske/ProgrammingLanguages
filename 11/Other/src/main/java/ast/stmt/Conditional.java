package ast.stmt;

import ast.expression.Expression;
import org.json.simple.JSONArray;
import utils.env.StaticCheckEnv;

import java.util.Objects;

public class Conditional implements Stmt {

    private Expression condition;
    private Stmt ifTrue;
    private Stmt ifFalse;

    public Conditional(Expression condition, Stmt ifTrue, Stmt ifFalse) {
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("if0");
        arr.add(condition);
        arr.add(ifTrue);
        arr.add(ifFalse);
        return arr.toJSONString();
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conditional that = (Conditional) o;
        return condition.equals(that.condition) &&
                ifTrue.equals(that.ifTrue) &&
                ifFalse.equals(that.ifFalse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, ifTrue, ifFalse);
    }

    @Override
    public Stmt staticCheck(StaticCheckEnv env) {
        return new Conditional(this.condition.staticCheck(env), this.ifTrue.staticCheck(env), this.ifFalse.staticCheck(env));
    }
}
