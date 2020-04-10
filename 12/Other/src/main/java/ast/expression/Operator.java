package ast.expression;

import org.json.simple.JSONArray;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.exceptions.IntExpectedException;
import utils.exceptions.TypeCheckException;
import value.IValue;
import value.IValueInt;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

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

    @Override
    public IValue expressionInterpret(EnvStoreTuple tuple) {
        IValue leftVal = this.lhs.expressionInterpret(tuple);
        IValue rightVal = this.rhs.expressionInterpret(tuple);
        if(leftVal instanceof IValueInt && rightVal instanceof IValueInt) {
            IValueInt leftValInt = (IValueInt) leftVal;
            IValueInt rightValInt = (IValueInt) rightVal;
            return operators.get(this.op).apply(Arrays.asList(leftValInt, rightValInt));
        } else {
            throw new IntExpectedException();
        }
    }


    public static final HashMap<String, Function<List<IValueInt>,IValue>> operators =
            new HashMap<String, Function<List<IValueInt>,IValue>>() {{
                put("+", (List<IValueInt> args) ->
                        args.stream().reduce(new IValueInt(0),(acc, v) ->
                                new IValueInt(v.getValue().add(acc.getValue()))));
                put("*", (List<IValueInt> args) ->
                        args.stream().reduce(new IValueInt(1),(acc, v) ->
                                new IValueInt(v.getValue().multiply(acc.getValue()))));

    }};
}
