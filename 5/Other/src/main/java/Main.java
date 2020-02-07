import answer.Answer;
import answer.AnswerFunction;
import fvexpr.SFVExpr;
import fvexpr.Func;
import fvexpr.Var;
import org.json.simple.parser.JSONParser;
import parser.ParseUtils;
import store.Location;
import store.Store;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            if ("interpreter".equals(args[0])) {
                Object obj = new JSONParser().parse(new FileReader(args[1]));
                SFVExpr result = ParseUtils.parse(obj);
                try {
                    System.out.println(result.interpret(initializeStd().get(0), initializeStd().get(1)).result);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new IllegalArgumentException("Error: an illegal function was requested");
            }
        } catch (Exception e) {
            //Do nothing
        }
    }

    public static List<Store> initializeStd() {
        Store<Var, Location> stdEnv = new Store<>();
        Store<Location, Answer> stdStore = new Store<>();
        Location zero = new Location(0);
        stdEnv.put(new Var("+"), zero);
        stdStore.put(zero, new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return null;
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return store.get(env.get(new Var("right"))).add(store.get(env.get(new Var("left"))));
            }

            @Override
            public String toJson() {
                return "+";
            }
        })));

        Location one = new Location(1);
        stdEnv.put(new Var("*"), one);
        stdStore.put(one, new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return null;
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return store.get(env.get(new Var("right"))).multiply(store.get(env.get(new Var("left"))));
            }

            @Override
            public String toJson() {
                return "*";
            }
        })));


        Location two = new Location(2);
        stdEnv.put(new Var("^"), two);
        stdStore.put(two, new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public String toJSONString() {
                return null;
            }

            @Override
            public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
                return store.get((env.get(new Var("right")))).pow(store.get(env.get(new Var("left"))));
            }

            @Override
            public String toJson() {
                return "^";
            }
        })));
        return Arrays.asList(stdEnv, stdStore);
    }
}

