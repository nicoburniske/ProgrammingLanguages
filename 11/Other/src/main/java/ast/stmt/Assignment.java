package ast.stmt;

import ast.expression.Expression;
import ast.lhs.LHS;
import org.json.simple.JSONArray;
import utils.env.StaticCheckEnv;

import java.util.Objects;

public class Assignment implements Stmt {
    private LHS leftHandSide;
    private Expression expression;

    public Assignment(LHS leftHandSide, Expression expression) {
        this.leftHandSide = leftHandSide;
        this.expression = expression;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add(leftHandSide);
        arr.add("=");
        arr.add(expression);
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
        Assignment that = (Assignment) o;
        return leftHandSide.equals(that.leftHandSide) &&
                expression.equals(that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftHandSide, expression);
    }

    @Override
    public Stmt staticCheck(StaticCheckEnv env) {
        return new Assignment(this.leftHandSide.staticCheck(env), this.expression.staticCheck(env));
    }
}
