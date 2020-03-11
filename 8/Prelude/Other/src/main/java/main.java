import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class main {
    public static void main(String[] args) {
        //x = int int int -> int
        //Function<Integer, Function<Function<Integer, Integer>, Integer>> x = (Integer a) -> (Function<Integer, Integer> b) -> a*b.apply(2);
        //System.out.println(x.apply(2).apply(4));
        //(Integer a) -> (Function<Integer, Integer> b) -> a*b.apply(2);

//        int obj = 9;
//        obj == 0 ? ifTrue : ifFalse;
//        if(obj == 0) {
//            ifTrue
//        } else {
//            ifFalse
//        }

        //args func
//        Object dsf = ((Function<Integer,?>)(Integer a) -> 1).apply(3);
//////        System.out.println(dsf.getClass());

        Optional<Integer> l = Optional.empty();
        Supplier<Integer> q = null;
        l.
        l = ()-> q;
        q = () -> 1;
        l.get();
        q.get();
        l;
        q = new Integer(89);
        System.out.println(l);
    }
}
