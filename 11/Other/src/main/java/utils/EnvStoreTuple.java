package utils;

import ast.Var;
import utils.env.Environment;
import utils.store.Store;
import value.IValue;
import value.Location;

public class EnvStoreTuple extends Tuple<Environment, Store> {
    public EnvStoreTuple(Environment left, Store right) {
        super(left, right);
    }

    /**
     * finds a value in the {@link EnvStoreTuple}
     *
     * @param var the variable to lookup
     * @return the matching value
     */
    public IValue lookup(Var var) {
        return this.getRight().get(this.getLeft().get(var));
    }

    /**
     * inserts a new value into the {@link EnvStoreTuple}
     *
     * @param variable the variable to be inserted into the Env
     * @param value    the value to be inserted into the Store
     * @return the new {@link EnvStoreTuple}
     */
    public EnvStoreTuple insert(Var variable, IValue value) {
        Integer pos = this.getRight().getSize();
        Store newStore = this.getRight().put(new Location(pos), value);
        Environment newEnv = this.getLeft().put(variable, pos);
        return new EnvStoreTuple(newEnv, newStore);
    }


    @Override
    public String toString() {
        return String.format("[%s,\n %s]", this.getLeft().toString(), this.getRight().toString());
    }
}
