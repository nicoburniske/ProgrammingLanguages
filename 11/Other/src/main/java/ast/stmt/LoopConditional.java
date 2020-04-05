package ast.stmt;

import ast.expression.Expression;
import org.json.simple.JSONArray;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.store.Store;
import value.IValue;
import value.IValueInt;

import java.util.Objects;

public class LoopConditional implements Stmt {
    private Expression condition;
    private Stmt body;

    public LoopConditional(Expression condition, Stmt body) {
        this.condition = condition;
        this.body = body;
    }


    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("do0");
        arr.add(condition);
        arr.add(body);
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
        LoopConditional that = (LoopConditional) o;
        return condition.equals(that.condition) &&
                body.equals(that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, body);
    }

    @Override
    public Stmt staticCheck(StaticCheckEnv env) {
        return new LoopConditional(this.condition.staticCheck(env), this.body.staticCheck(env));
    }

    @Override
    public Store transition(EnvStoreTuple tuple) {
        IValue result = this.condition.expressionInterpret(tuple);
        if (result.equals(new IValueInt(0))) {
            tuple = new EnvStoreTuple(tuple.getLeft(), this.body.transition(tuple));
            return this.transition(tuple);
        }
        return tuple.getRight();
    }
}
