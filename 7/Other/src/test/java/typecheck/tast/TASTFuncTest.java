package typecheck.tast;

import org.junit.Test;
import typecheck.tast.star_ast.StarAST;
import typecheck.type.TypedVar;
import typecheck.type.TypeInt;

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
}