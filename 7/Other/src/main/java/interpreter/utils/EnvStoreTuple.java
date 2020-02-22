package interpreter.utils;

import common.TupleGeneric;
import interpreter.pal.PALVar;
import interpreter.utils.env.Environment;
import interpreter.utils.store.Store;
import interpreter.value.IBin;
import interpreter.value.IValue;
import interpreter.value.ValueInt;
import interpreter.value.ValuePrimop;

import java.math.BigInteger;
import java.util.List;

import static interpreter.utils.RuntimeExceptions.ARITHMETIC_ERROR;

public class EnvStoreTuple extends TupleGeneric<Environment, Store> {
    public EnvStoreTuple(Environment left, Store right) {
        super(left, right);
    }

    /**
     * finds a value in the {@link EnvStoreTuple}
     *
     * @param var the varibale to lookup
     * @return the matching value
     */
    public IValue lookup(PALVar var) {
        return this.getRight().get(this.getLeft().get(var));
    }

    /**
     * inserts a new value into the {@link EnvStoreTuple}
     *
     * @param variable the varible to be inserted into the Env
     * @param value    the value to be inserted into the Store
     * @return the new {@link EnvStoreTuple}
     */
    public EnvStoreTuple insert(PALVar variable, IValue value) {
        Integer pos = this.getRight().getSize();
        Store newStore = (Store) this.getRight().put(pos, value);
        Environment newEnv = (Environment) this.getLeft().put(variable, pos);
        return new EnvStoreTuple(newEnv, newStore);
    }


    /**
     * Returns the standard prelude for the interpretation of PALVars
     *
     * @return
     */
    public static EnvStoreTuple stdLib() {
        EnvStoreTuple current = new EnvStoreTuple(new Environment(), new Store());
        // Puts a valueprimop into the store representing an addition primop.
        current = current.insert(new PALVar("+"), new ValuePrimop(2, (List<IValue> values) -> {
            ValueInt left = (ValueInt) values.get(0);
            ValueInt right = (ValueInt) values.get(1);
            return new ValueInt((left).getNum().add((right).getNum()));
        }));

        // Puts a ValuePrimop into the store representing a multiply primop.
        current = current.insert(new PALVar("*"), new ValuePrimop(2, (List<IValue> values) -> {
            ValueInt left = (ValueInt) values.get(0);
            ValueInt right = (ValueInt) values.get(1);
            return new ValueInt((left).getNum().multiply((right).getNum()));
        }));

        // Puts a ValuePrimop into the store representing an exponent primop.
        // Will throw an exception at runtime if the exponent (the right argument)is negative
        current = current.insert(new PALVar("^"), new ValuePrimop(2, (List<IValue> values) -> {
            ValueInt leftInt = (ValueInt) values.get(0);
            ValueInt rightInt = (ValueInt) values.get(1);
            if (rightInt.getNum().compareTo(new BigInteger("0")) >= 0) {
                return new ValueInt(leftInt.getNum().pow(rightInt.getNum().intValue()));
            } else {
                throw new IllegalStateException(ARITHMETIC_ERROR);
            }
        }));
        return current;
    }

}
