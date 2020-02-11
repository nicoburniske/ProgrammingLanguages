package store;

import answer.*;
import fdecl.SFVDecl;
import fvexpr.Func;
import fvexpr.SFVExpr;
import fvexpr.Var;

import java.util.Arrays;
import java.util.List;

import static fvexpr.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;

/**
 * Utilities Class for operations on Environment/Store Combinations.
 */
public class StoreUtils {
    /**
     * Retrieves the Answer associated with the given Var from the Environment/Store Combination.
     * If no such value exists an undeclared variable error will be returned as an AnswerString.
     *
     * @param env   The current environment.
     * @param store The current store.
     * @param v     the Var whose value we are trying to find.
     * @return The associated Answer, or an AnswerString if the Var has not been defined.
     */
    public static Answer lookup(Store<Var, Location> env, Store<Location, Answer> store, Var v) {
        if (isLookupValid(env, store, v)) {
            return store.get(env.get(v));
        } else {
            return new AnswerString(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, v.myString));
        }
    }

    /**
     * Determines if the given Var's Answer exists in the Environment/Store combination.
     *
     * @param env   The current environment.
     * @param store The current store.
     * @param v     The given Var.
     * @return true if the Var exists within the environment, and the associated location
     */
    private static Boolean isLookupValid(Store<Var, Location> env, Store<Location, Answer> store, Var v) {
        return ((env.get(v) != null) && (store.get(env.get(v)) != null));
    }

    /**
     * Inserts the Declaration into the Environment/Store combination.
     *
     * @param env   The current environment.
     * @param store The current store.
     * @param d     the declaration to be inserted.
     */
    public static void insertIntoStore(Store<Var, Location> env, Store<Location, Answer> store, SFVDecl d) {
        // obtain a unique location.
        Location l = new Location(store.getSize());
        store.put(l, d.interpret(env, store));
        env.put(d.name, l);
    }

    /**
     * Inserts the Answer into the store while maintaining the association with Var v.
     *
     * @param env   the current environment.
     * @param store The current store.
     * @param v     the Var to
     * @param a
     */
    public static void insertIntoStore(Store<Var, Location> env, Store<Location, Answer> store, Var v, Answer a) {
        Location l = new Location(store.getSize());
        store.put(l, a);
        env.put(v, l);
    }

    /**
     * Instantiates the Standard Prelude for our interpreter.
     * Currently creates the following primops:
     * <ul>
     *     <li> + (addition) </li>
     *     <li> *(multiplication) </li>
     *     <li> ^ (exponentiation) </li>
     *     <li> @ (constructor) </li>
     *     <li> ! (getter) </li>
     *     <li> = (setter) </li>
     * </ul>
     *
     * @return a List containing two Stores, where the first represents the Standard Environment, and the second represents the Standard Store.
     */
    public static List<Store> initializeStd() {
        Store<Var, Location> stdEnv = new Store<Var, Location>();
        Store<Location, Answer> stdStore = new Store<Location, Answer>();
        Location plusLocation = new Location(0);
        stdEnv.put(new Var("+"), plusLocation);
        Location multiplyLocation = new Location(1);
        stdEnv.put(new Var("*"), multiplyLocation);
        Location exponenetLocation = new Location(2);
        stdEnv.put(new Var("^"), exponenetLocation);
        Location allocateLocation = new Location(3);
        stdEnv.put(new Var("@"), allocateLocation);
        Location findLocation = new Location(4);
        stdEnv.put(new Var("!"), findLocation);
        Location setLocation = new Location(5);
        stdEnv.put(new Var("="), setLocation);

        //PLUS, TIMES, Exp should all have access to eachother so add to env first then to store
        stdStore.put(multiplyLocation, new AnswerPrimop(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "*";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return lookup(env, store, new Var("right")).multiply(lookup(env, store, new Var("left")));
            }

        }), stdEnv));
        stdStore.put(exponenetLocation, new AnswerPrimop(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "^";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return lookup(env, store, new Var("right")).pow(lookup(env, store, new Var("left")));
            }
        }), stdEnv));
        stdStore.put(plusLocation, new AnswerPrimop(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "+";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return lookup(env, store, new Var("right")).add(lookup(env, store, new Var("left")));
            }
        }), stdEnv));
        stdStore.put(allocateLocation, new AnswerPrimop(new Func(Arrays.asList(new Var("arg")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "@";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                Answer cell = lookup(env, store, new Var("arg"));
                Location newLocation = new Location(store.getSize());
                store.put(newLocation, cell);
                return new AnswerCell(new Cell(newLocation));
            }

        }), stdEnv));
        stdStore.put(findLocation, new AnswerPrimop(new Func(Arrays.asList(new Var("arg")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "!";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                AnswerCell cell = (AnswerCell) lookup(env, store, new Var("arg"));
                return store.get(cell.result.getLocation());
            }

        }), stdEnv));
        stdStore.put(setLocation, new AnswerPrimop(new Func(Arrays.asList(new Var("arg1"), new Var("arg2")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "=";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                AnswerCell cell = (AnswerCell) lookup(env, store, new Var("arg1"));
                return store.getThenSet(cell.result.getLocation(), lookup(env, store, new Var("arg2")));
            }

        }), stdEnv));
        return Arrays.asList(stdEnv, stdStore);
    }

}
