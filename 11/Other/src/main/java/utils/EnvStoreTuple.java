package utils;

import ast.Var;
import utils.env.Environment;
import utils.store.Store;
import value.IValue;
import value.IValueArray;
import value.Location;

import java.util.List;

public class EnvStoreTuple extends Tuple<Environment, Store> {
    public EnvStoreTuple(Environment left, Store right) {
        super(left, right);
    }
    public EnvStoreTuple() {
        super(new Environment(), new Store());
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

    public void insertMutate(Var variable, IValue value) {
        EnvStoreTuple newTuple = this.insert(variable, value);
        this.left = newTuple.getLeft();
        this.right = newTuple.getRight();
    }


    public void setMutate(Var variable, IValue value) {
        Location l = this.left.get(variable);
        this.right = this.right.set(l, value);
    }



    /**
     * inserts a new value into the {@link EnvStoreTuple}
     *
     * @param variable the variable to be inserted into the Env
     * @param arr    the value to be inserted into the Store
     * @return the new {@link EnvStoreTuple}
     */
    public EnvStoreTuple insert(Var variable, List<IValue> arr) {
        Integer pos = this.getRight().getSize();
        Store newStore = this.getRight().put(new Location(pos), new IValueArray(new Location(pos+ 1), arr.size()));
        int ii = 0;
        int storesize = newStore.getSize();
        for (IValue iValue : arr) {
            newStore = newStore.put(new Location(storesize + ii), iValue);
            ii ++;
        }
        Environment newEnv = this.getLeft().put(variable, pos);
        return new EnvStoreTuple(newEnv, newStore);
    }


    @Override
    public String toString() {
        return String.format("[%s,\n %s]", this.getLeft().toString(), this.getRight().toString());
    }
}
