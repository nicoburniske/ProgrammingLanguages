package typechecker.tast;

import org.junit.Test;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPALCall;
import typechecker.tpal.TPALFunc;
import typechecker.tpal.TPALInt;
import typechecker.type.TypedVar;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TASTFuncCallTest {

    @Test
    public void toJSONString() {
        List<TypedVar> params1 = Arrays.asList();
        TASTFunc fun1 = new TASTFunc(params1,new StarAST(new TASTVar("a"), new TypeInt()));

        assertEquals("[\"call\",{\"expr\":[\"fun*\",[],{\"expr\":\"a\",\"type\":\"int\"}],\"type\":[\"int\",\"->\",\"int\"]}]",
                new TASTFuncCall(
                        new StarAST(fun1, new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())), Arrays.asList()).toJSONString());

    }
    @Test
    public void toJava() {
        List<TypedVar> params1 = Arrays.asList(new TypedVar("a", new TypeInt()));
        TASTFunc fun1 = new TASTFunc(params1,new StarAST(new TASTVar("a"), new TypeInt()));
        TASTFuncCall call1 = new TASTFuncCall(
                new StarAST(fun1, new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())), Arrays.asList(new StarAST(new TASTInteger(new BigInteger("4")), new TypeInt())));
        // (Function<Integer, Integer> (int a) -> a).apply(4);
        assertEquals("(((Function<Integer,Integer>)(Integer a) -> a)).apply(4)", call1.toJava(new TypeInt()));

        List<TypedVar> params2 = Arrays.asList();
        TASTFunc fun2 = new TASTFunc(params2,new StarAST(new TASTInteger(new BigInteger("5")), new TypeInt()));
        TASTFuncCall call2 = new TASTFuncCall(
                new StarAST(fun2, new TypeFunction(Arrays.asList(), new TypeInt())), Arrays.asList());
        // (Function<Integer, Integer> (int a) -> a).apply(4);
        assertEquals("(((Function<Integer,Integer>)(Integer a) -> a)).apply(4))", call2.toJava(new TypeInt()));
    }
}