package typechecker.tast;

import org.junit.Test;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.TypedVar;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;

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
}