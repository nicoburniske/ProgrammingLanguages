package typechecker.tast;

import org.junit.Test;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPALFunc;
import typechecker.tpal.TPALInt;
import typechecker.type.TypeFunction;
import typechecker.type.TypedVar;
import typechecker.type.TypeInt;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TASTFuncTest {

    @Test
    public void toJSONString() {
        List<TypedVar> params1 = Arrays.asList();
        TASTFunc fun1 = new TASTFunc(params1,new StarAST(new TASTVar("a"), new TypeInt()));
        assertEquals("[\"fun*\",[],{\"expr\":\"a\",\"type\":\"int\"}]", fun1.toJSONString());
    }
    @Test
    public void toJava() {
        TASTFunc func = new TASTFunc(Arrays.asList(new TypedVar("a", new TypeFunction(Arrays.asList(new TypeInt()),new TypeInt()))), new StarAST(new TASTInteger(1), new TypeInt()));
        assertEquals("(Function<Integer,Integer> a) -> 1",
                func.toJava(
                        new TypeFunction(
                                Arrays.asList(new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())),
                                new TypeInt())
                ));
    }
}