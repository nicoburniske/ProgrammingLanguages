package ast.lhs;

import java.util.Objects;

public class VarLoc implements LHS{
    private String s;

    public VarLoc(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public String toJSONString() {
        return String.format("\"%s\"", s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarLoc varLoc = (VarLoc) o;
        return s.equals(varLoc.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }
}
