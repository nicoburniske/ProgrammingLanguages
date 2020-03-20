package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PALDeclArrayTest {

    EnvStoreTuple stdLib;
    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }
    @Test
    public void testInterpret1() {
        Decl d1 = new Decl(new PALVar("x"), new PALFunc(Arrays.asList(new PALVar("param1")), new PALCall(new PALVar("+"), Arrays.asList(new PALVar("param1"), new PALVar("y")))));
        Decl d2 = new Decl(new PALVar("y"), new PALInt(5L));
        Decl d3 = new Decl(new PALVar("z"), new PALInt(100L));

        PALDeclArray darr1 = new PALDeclArray(Arrays.asList(d1, d2, d3), new PALCall(new PALVar("x"), Arrays.asList(new PALVar("z"))));

        assertEquals(new ValueInt(105L), darr1.interpret(stdLib).getLeft());
    }

    @Test
    public void testInterpret2() {
        Decl d1 = new Decl(new PALVar("x"), new PALInt(42L));
        Decl d2 = new Decl(new PALVar("x"), new PALInt(20L));
        PALFunc f1 = new PALFunc(Arrays.asList(), new PALVar("x"));
        Decl d3 = new Decl(new PALVar("fx"), f1);
        PALCall call = new PALCall(new PALVar("fx"), Arrays.asList());
        PALDeclArray declArr = new PALDeclArray(Arrays.asList(d1, d3), new PALDeclArray(Arrays.asList(d2), call));

        System.out.println(declArr.interpret(stdLib).getLeft().toJSONString());
        assertEquals(new ValueInt(42L), declArr.interpret(stdLib).getLeft());
    }
}