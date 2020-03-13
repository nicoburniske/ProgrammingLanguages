package typechecker.tast;

import typechecker.type.Type;

import java.util.Map;
import java.util.Objects;

public class TASTVar implements TAST{
    String variable;

    public TASTVar(String variable) {
        this.variable = variable;
    }

    @Override
    public String toJSONString() {
        return String.format("\"%s\"", variable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TASTVar tastVar = (TASTVar) o;
        return variable.equals(tastVar.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }

    @Override
    public String toJava(Type type) {
        return this.variable;
    }

    @Override
    public void replaceReservedKeywords(Map<String, String> reserved) {
        // doesn't need to do anything
    }

    @Override
    public void replaceReservedKeyword(String varName, String replacement) {
        if (this.variable.equals(varName)) {
            this.variable = replacement;
        }
    }
}
