package utils.env;

import ast.Var;
import utils.list.IConsList;
import utils.list.IList;
import utils.list.IMtList;

import java.util.Objects;

/**
 * Represents a non mutable environment. Composes a LookupTable<Var, Integer>
 */
public class StaticCheckEnv {
    private IList<Var> declaredVars;

    public StaticCheckEnv(IList<Var> declaredVars) {
        this.declaredVars = declaredVars;
    }

    public StaticCheckEnv() {
        this.declaredVars = new IMtList<>();
    }

    public StaticCheckEnv(Var var) {
        this.declaredVars = new IConsList<>(var, new IMtList<>());
    }

    public StaticCheckEnv put(Var key) {
        return new StaticCheckEnv(declaredVars.add(key));
    }

    public boolean contains(Var key) {
        return declaredVars.contains(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaticCheckEnv that = (StaticCheckEnv) o;
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
