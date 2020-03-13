package typechecker.tast;

import org.junit.Test;
import typechecker.tast.star_ast.StarAST;
import typechecker.tast.star_decl.StarDecl;
import typechecker.type.TypeFunction;
import typechecker.type.TypedVar;
import typechecker.type.TypeInt;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TASTDeclArrayTest {

    @Test
    public void toJSONString() {
        List<StarDecl> decls = Arrays.asList();
        TASTDeclArray declArray1 = new TASTDeclArray(decls, new StarAST(new TASTVar("a"), new TypeInt()));
        assertEquals("[{\"expr\":\"a\",\"type\":\"int\"}]", declArray1.toJSONString());
        List<StarDecl> decls2 = Arrays.asList(new StarDecl(new TypedVar("b", new TypeInt()), new StarAST(new TASTVar("c"), new TypeInt())));
        TASTDeclArray declArray2 = new TASTDeclArray(decls2, new StarAST(new TASTVar("a"), new TypeInt()));
        assertEquals("[[\"let\",[\"b\",\":\",\"int\"],\"=\",{\"expr\":\"c\",\"type\":\"int\"}],{\"expr\":\"a\",\"type\":\"int\"}]", declArray2.toJSONString());

        List<StarDecl> decls3 = Arrays.asList(new StarDecl(new TypedVar("c", new TypeInt()), new StarAST(new TASTVar("c"), new TypeInt())), decls2.get(0));
        TASTDeclArray declArray3 = new TASTDeclArray(decls3, new StarAST(new TASTVar("a"), new TypeInt()));
        assertEquals("[[\"let\",[\"c\",\":\",\"int\"],\"=\",{\"expr\":\"c\",\"type\":\"int\"}],[\"let\",[\"b\",\":\",\"int\"],\"=\",{\"expr\":\"c\",\"type\":\"int\"}],{\"expr\":\"a\",\"type\":\"int\"}]", declArray3.toJSONString());



    }

    @Test
    public void toJava() {
        List<StarDecl> decls2 = Arrays.asList(new StarDecl(new TypedVar("b", new TypeInt()), new StarAST(new TASTVar("c"), new TypeInt())),
                new StarDecl(new TypedVar("d", new TypeFunction(new TypeInt(), new TypeInt())), new StarAST(new TASTVar("e"), new TypeFunction(new TypeInt(), new TypeInt()))));
        TASTDeclArray declArray2 = new TASTDeclArray(decls2, new StarAST(new TASTVar("a"), new TypeInt()));
        assertEquals("(new Function<MyInteger,MyInteger>() {\n" +
                "Function<MyInteger,MyInteger> d;\n" +
                "@Override\n" +
                "            public MyInteger apply(MyInteger b) {d = e;\n" +
                "return a;\n" +
                "}\n" +
                "}).apply(c)", declArray2.toJava(new TypeInt()));

    }
}