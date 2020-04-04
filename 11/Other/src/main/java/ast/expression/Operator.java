package ast.expression;

import org.json.simple.JSONArray;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;

import java.util.Objects;

public class Operator implements Expression {
    private Expression lhs;
    private Expression rhs;
    private String op;

    public Operator(Expression lhs, Expression rhs, String op) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add(lhs);
        arr.add(op);
        arr.add(rhs);
        return arr.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return lhs.equals(operator.lhs) &&
                rhs.equals(operator.rhs) &&
                op.equals(operator.op);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs, op);
    }

    @Override
    public Expression staticCheck(StaticCheckEnv env) throws TypeCheckException {
        return new Operator(this.lhs.staticCheck(env), this.rhs.staticCheck(env), this.op);
    }
}
