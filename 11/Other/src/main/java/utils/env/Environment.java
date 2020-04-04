package utils.env;

import ast.Var;
import utils.list.IConsList;
import utils.list.IList;
import utils.list.IMtList;

import java.util.Objects;

/**
 * Represents a non mutable environment. Composes a LookupTable<Var, Integer>
 */
public class Environment {
    private IList<Var> declaredVars;

    public Environment(IList<Var> declaredVars) {
        this.declaredVars = declaredVars;
    }

    public Environment() {
        this.declaredVars = new IMtList<>();
    }

    public Environment(Var var) {
        this.declaredVars = new IConsList<>(var, new IMtList<>());
    }

    public Environment put(Var key) {
        return new Environment(declaredVars.add(key));
    }

    public boolean contains(Var key) {
        return declaredVars.contains(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Environment that = (Environment) o;
        return declaredVars.equals(that.declaredVars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declaredVars);
    }

    @Override
    public String toString() {
        return declaredVars.toString();
    }


}
