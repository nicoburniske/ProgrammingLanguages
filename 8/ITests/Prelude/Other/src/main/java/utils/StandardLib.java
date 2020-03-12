package utils;

import java.util.function.Function;

public interface StandardLib {
    Function<MyInteger, Function<MyInteger, MyInteger>> plusRESERVED = (left) -> (right) -> left.add(right);
    Function<MyInteger, Function<MyInteger, MyInteger>> timesRESERVED = (left) -> (right) -> left.multiply(right);
    Function<MyInteger, Function<MyInteger, MyInteger>> exponentRESERVED = (left) -> (right) -> {
        if(right.compareTo(new MyInteger(0)) >= 0 && right.compareTo(new MyInteger(128)) <= 0) {
        return left.pow(right);
        }
        else {
            throw new IllegalStateException("primop domain error");
        }

    };
}
