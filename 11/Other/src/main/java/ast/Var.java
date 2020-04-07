package ast;

import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;

import java.util.Objects;

/**
 * This class represents a String
 */
public class Var implements WhileLang {
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

    public Var typecheck(StaticCheckEnv env) {
        if(env.contains(this)) {
            return new Var(this.s);
        } else {
            throw new TypeCheckException();
        }
    }
}

