package store;

import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerString;
import fdecl.SFVDecl;
import fvexpr.Func;
import fvexpr.SFVExpr;
import fvexpr.Var;

import java.util.Arrays;
import java.util.List;

import static fvexpr.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;

public class StoreUtils {
    public  static  Answer lookup(Store<Var, Location> env, Store<Location, Answer> store, Var v){
        if(isLookupValid(env, store, v)) {
            return store.get(env.get(v));
        }
        else {
            return new AnswerString(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, v.myString));
        }
    }

    public  static  Boolean isLookupValid(Store<Var, Location> env, Store<Location, Answer> store, Var v){
        return ( (env.get(v) != null) && (store.get(env.get(v)) != null));
    }

    public static void insertIntoStore(Store<Var, Location> env, Store<Location, Answer> store, SFVDecl d) {
        Location l = new Location(store.getSize());
        env.put(d.name, l);
        store.put(l, d.interpret(env, store));
    }


    public static List<Store> initializeStd() {
        Store<Var, Location> stdEnv = new Store<>();
        Store<Location, Answer> stdStore = new Store<>();
        Location zero = new Location(0);
        stdEnv.put(new Var("+"), zero);
        stdStore.put(zero, new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "+";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return store.get(env.get(new Var("right"))).add(store.get(env.get(new Var("left"))));
            }
        })));

        Location one = new Location(1);
        stdEnv.put(new Var("*"), one);
        stdStore.put(one, new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "*";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return store.get(env.get(new Var("right"))).multiply(store.get(env.get(new Var("left"))));
            }

        })));


        Location two = new Location(2);
        stdEnv.put(new Var("^"), two);
        stdStore.put(two, new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return "^";
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return store.get((env.get(new Var("right")))).pow(store.get(env.get(new Var("left"))));
            }
        })));
        return Arrays.asList(stdEnv, stdStore);
    }

}
