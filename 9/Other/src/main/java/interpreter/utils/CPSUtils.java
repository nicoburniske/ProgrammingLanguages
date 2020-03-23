package interpreter.utils;

import interpreter.pal.Toy;
import interpreter.pal.ToyCall;
import interpreter.pal.ToyFunc;
import interpreter.pal.ToyVar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CPSUtils {
    public static ToyVar K = new ToyVar("k");
    public static List<ToyVar> KList = Arrays.asList(new ToyVar("k"));
    public static ToyVar left = new ToyVar("left");
    public static ToyVar right = new ToyVar("right");
    public static ToyVar plus = new ToyVar("+");
    public static ToyVar times = new ToyVar("*");
    public static ToyVar exp = new ToyVar("^");
    public static ToyVar at = new ToyVar("@");
    public static ToyVar equ = new ToyVar("=");
    public static ToyVar bang = new ToyVar("!");
    public static ToyVar ofTST = new ToyVar("of-tst");

    public static Map<ToyVar, Toy> stdLib = new HashMap<ToyVar, Toy>(){{
        put(plus, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(plus, Arrays.asList(left, right)))));
        put(times, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(times, Arrays.asList(left, right)))));
        put(exp, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(exp, Arrays.asList(left, right)))));
        put(at,new ToyFunc(Arrays.asList(K, left), new ToyCall(K, new ToyCall(at, Arrays.asList(left)))));
        put(bang,new ToyFunc(Arrays.asList(K, left), new ToyCall(K, new ToyCall(bang, Arrays.asList(left)))));
        put(equ, new ToyFunc(Arrays.asList(K, left, right), new ToyCall(K, new ToyCall(equ, Arrays.asList(left, right)))));
    }};

    public static Toy toTestFormat(Toy expression) {
        return new ToyCall(expression, new ToyFunc(Arrays.asList(new ToyVar("identity")), new ToyVar("identity")));
    }
}
