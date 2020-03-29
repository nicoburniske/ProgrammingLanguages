package interpreter.utils;

import interpreter.pal.*;

import java.util.*;

/**
 * A Utils class For Continuation Passing Form
 */
public class CPSUtils {
    /**
     * helpful variables
     */
    public static ToyVar K = new ToyVar("k");
    public static List<ToyVar> KList = Arrays.asList(K);
    public static ToyVar left = new ToyVar("left");
    public static ToyVar right = new ToyVar("right");
    public static ToyVar plus = new ToyVar("+");
    public static ToyVar times = new ToyVar("*");
    public static ToyVar exp = new ToyVar("^");
    public static ToyVar at = new ToyVar("@");
    public static ToyVar equ = new ToyVar("=");
    public static ToyVar bang = new ToyVar("!");
    public static ToyVar ofTST = new ToyVar("of-tst");
    public static ToyVar identity = new ToyVar("identity");
    private static List<ToyVar> vars = Arrays.asList(K, left, right, ofTST, identity);
    public static Set<String> names = new HashSet<>();


    //A map of primops to their respective CPS forms
    public static Map<ToyVar, Toy> stdLib = new HashMap<ToyVar, Toy>() {{
        put(plus, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(plus, Arrays.asList(left, right)))));
        put(times, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(times, Arrays.asList(left, right)))));
        put(exp, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(exp, Arrays.asList(left, right)))));
        put(at, new ToyFunc(Arrays.asList(K, left), new ToyCall(K, new ToyCall(at, Arrays.asList(left)))));
        put(bang, new ToyFunc(Arrays.asList(K, left), new ToyCall(K, new ToyCall(bang, Arrays.asList(left)))));
        put(equ, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(equ, Arrays.asList(left, right)))));
    }};

    /**
     * Passes a Continuation passing form {@link Toy} a continuation that is the identity function
     * Used for testing if the interpreter gets the same result after CPS() is called on a {@link Toy}
     *
     * @param expression the {@link Toy} that needs a continuation
     * @return the new {@link Toy} that can be wrapped with a identity continuation
     */
    public static Toy toTestFormat(Toy expression) {
        // return new ToyCall(expression, new ToyFunc(Arrays.asList(CPSUtils.identity), new ToyStop(CPSUtils.identity)));
        return new ToyCall(expression, new ToyFunc(Arrays.asList(identity), identity));
    }

    /**
     * This function changes any of the {@link ToyVar}s defined above and replaces their Strings with unique names
     * if their names are ussed in {@param t}
     *
     * @param t the {@link Toy} that is begin used
     */
    public static void initializeNames(Toy t) {
        names = new HashSet<>();
        t.getAllNames(names);
        for (ToyVar var : vars) {
            if (names.contains(var.toString())) {
                var.setVar(nameGenerator());
            }
        }
    }

    /**
     * Generates a string that is not in the provided set of strings
     *
     * @return a new string
     */
    public static String nameGenerator() {
        // !, @, $, %, ^, &, *, _, -, +, :, /, =
        String ALLPOSSIBLECHARS = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder ret = new StringBuilder();
        Random rnd = new Random();
        while (ret.length() < 10) {
            int index = rnd.nextInt(ALLPOSSIBLECHARS.length());
            ret.append(ALLPOSSIBLECHARS.charAt(index));
        }
        String result = ret.toString();
        if (names.contains(result) || vars.stream().anyMatch(var -> var.toString().equals(result))) {
            return nameGenerator();
        } else {
            names.add(result);
            return result;
        }
    }
}
