package utils;

import ast.Var;
import ast.WhileLang;
import ast.stmt.frame.IFrame;
import utils.env.Environment;
import utils.store.Store;
import value.IValue;
import value.IValueArray;
import value.Location;

import java.util.List;
import java.util.Stack;

public class EnvStoreTuple extends Tuple<Environment, Store> {
    public EnvStoreTuple(Environment left, Store right) {
        super(left, right);
    }

    /**
     * ONLY USE THIS CONSTRUTOR IF YOU KNOW WHAT YOU ARE DOING
     * IT HAS A SET MAXSIZE TO ITS STORE
     */
    public EnvStoreTuple() {
        super(new Environment(), new Store(100));
    }

    /**
     *  Creates a new {@link EnvStoreTuple}  with a specified maxsize for the store
     * @param maxSize the maximum size of a store
     */
    public EnvStoreTuple(int maxSize) {
        super(new Environment(), new Store(maxSize));
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
     * inserts a new value into the {@link EnvStoreTuple}, This will trigger garbage collection if the
     * Store is out of space.
     *
     * @param variable the variable to be inserted into the Env
     * @param value    the value to be inserted into the Store
     * @param tuple used for garbage collection
     * @param stack used for garbage collection
     * @param control used for garbage collection
     * @return the new {@link EnvStoreTuple}
     */
    public EnvStoreTuple insert(Var variable, IValue value, EnvStoreTuple tuple, Stack<IFrame> stack , WhileLang control) {
        Integer pos = this.getRight().getCounter();
        Store newStore = this.getRight().putWithGarbageCollection(new Location(pos), value, tuple, stack, control);
        Environment newEnv = this.getLeft().put(variable, pos);
        return new EnvStoreTuple(newEnv, newStore);
    }


    /**
     * inserts a new value into the {@link EnvStoreTuple}. This function will not trigger garbage collection
     * Only use this function if you know what you are doing. It will fail if the {@link Store} is out of space
     *
     * @param variable the variable to be inserted into the Env
     * @param value    the value to be inserted into the Store
     * @return the new {@link EnvStoreTuple}
     */
    public EnvStoreTuple insert(Var variable, IValue value) {
        Integer pos = this.getRight().getCounter();
        Store newStore = this.getRight().put(new Location(pos), value);
        Environment newEnv = this.getLeft().put(variable, pos);
        return new EnvStoreTuple(newEnv, newStore);
    }

    /**
     * inserts a new value into the {@link EnvStoreTuple}
     *
     * @param variable the variable to be inserted into the Env
     * @param arr    the value to be inserted into the Store
     * @return the new {@link EnvStoreTuple}
     */
    public EnvStoreTuple insert(Var variable, List<IValue> arr) {
        Integer pos = this.getRight().getCounter();
        Store newStore = this.getRight().put(new Location(pos), new IValueArray(new Location(pos+ 1), arr.size()));
        int ii = 0;
        int storesize = newStore.getCounter();
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
