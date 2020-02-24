package interpreter.utils;

import common.TupleGeneric;
import interpreter.pal.PALVar;
import interpreter.utils.env.Environment;
import interpreter.utils.store.Store;
import interpreter.value.*;

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
        Store newStore = this.getRight().put(pos, value);
        Environment newEnv = this.getLeft().put(variable, pos);
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
        current = current.insert(new PALVar("+"), new ValuePrimop(2, (List<IValue> args, EnvStoreTuple tuple)  -> {
            ValueInt left = (ValueInt) args.get(0);
            ValueInt right = (ValueInt) args.get(1);
            return new ValueEnvStoreTuple(new ValueInt((left).getNum().add((right).getNum())), tuple);
        }));

        // Puts a ValuePrimop into the store representing a multiply primop.
        current = current.insert(new PALVar("*"), new ValuePrimop(2, (List<IValue> args, EnvStoreTuple tuple)  -> {
            ValueInt left = (ValueInt) args.get(0);
            ValueInt right = (ValueInt) args.get(1);
            return new ValueEnvStoreTuple(new ValueInt((left).getNum().multiply((right).getNum())), tuple);
        }));

        // Puts a ValuePrimop into the store representing an exponent primop.
        // Will throw an exception at runtime if the exponent (the right argument)is negative
        current = current.insert(new PALVar("^"), new ValuePrimop(2, (List<IValue> args, EnvStoreTuple tuple)  -> {
            ValueInt leftInt = (ValueInt) args.get(0);
            ValueInt rightInt = (ValueInt) args.get(1);
            if (rightInt.getNum().compareTo(new BigInteger("0")) >= 0) {
                return new ValueEnvStoreTuple(new ValueInt(leftInt.getNum().pow(rightInt.getNum().intValue())), tuple);
            } else {
                throw new IllegalStateException(ARITHMETIC_ERROR);
            }
        }));

        //@
        current = current.insert(new PALVar("@"), new ValuePrimop(1, (List<IValue> args, EnvStoreTuple tuple) -> {
            IValue val = args.get(0);
            Store store = tuple.getRight();
            int loc = store.getSize();
            store = store.put(loc, val);
            return new ValueEnvStoreTuple(new ValueCell(new Cell(loc)), new EnvStoreTuple(tuple.getLeft(), store));
        }));

        //!
        current = current.insert(new PALVar("!"), new ValuePrimop(1, (List<IValue> args, EnvStoreTuple tuple) -> {
            ValueCell val = (ValueCell) args.get(0);
            Store store = tuple.getRight();
            int loc = val.getCell().getLocation();
            return new ValueEnvStoreTuple(store.get(loc), tuple);
        }));

        //=
        current = current.insert(new PALVar("="), new ValuePrimop(2, (List<IValue> args, EnvStoreTuple tuple)-> {
            ValueCell cellVal = (ValueCell) args.get(0);
            IValue newVal = args.get(1);
            Store store = tuple.getRight();
            IValue oldVal = store.get(cellVal.getCell().getLocation());
            store = store.set(cellVal.getCell().getLocation(), newVal);
            return new ValueEnvStoreTuple(oldVal, new EnvStoreTuple(tuple.getLeft(), store));
        }));
        return current;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EnvStoreTuple{");
        sb.append(this.getLeft().toString());
        sb.append(this.getRight().toString());
        sb.append('}');
        return sb.toString();
    }
}
