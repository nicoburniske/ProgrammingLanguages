package utils;

import java.util.function.Function;
import java.util.function.Supplier;

import static utils.StandardLib.*;
import utils.Cell;

public class OutputtedCode {
    public static MyInteger run() {
        return (new Supplier<MyInteger>() {
            Function<MyInteger,MyInteger> factorial;
            @Override
            public MyInteger get() {factorial = (new Function<MyInteger,MyInteger>() {
                @Override
                public MyInteger apply(MyInteger x) {
                    return x.equals(new MyInteger(0)) ? new MyInteger(1) : (timesRESERVED).apply(x).apply((factorial).apply((plusRESERVED).apply(x).apply(new MyInteger(-1))));
                }
            });
                return (factorial).apply(new MyInteger(150));
            }
        }).get();

    }
}
