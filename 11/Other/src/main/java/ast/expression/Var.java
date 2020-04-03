package ast.expression;

import java.util.Objects;

public class Var implements Expression {
    private String s;

    public Var(String s) {
        this.s = s;
    }


    @Override
    public String toJSONString() {
        return String.format("\"%s\"", s);
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Var var = (Var) o;
        return s.equals(var.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }
}

