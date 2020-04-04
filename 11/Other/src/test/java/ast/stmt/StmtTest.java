package ast.stmt;

import ast.Var;
import ast.decl.ArrDecl;
import ast.decl.Decl;
import ast.expression.ArrayAccess;
import ast.expression.Int;
import ast.expression.Operator;
import ast.expression.VarExpr;
import ast.lhs.ArrIndexLoc;
import ast.lhs.VarLoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StmtTest {
    StaticCheckEnv mt, containsX;
    Assignment ass1;
    Conditional cond1;
    LoopConditional loop1;
    StmtBlock block1, block2, blockHard1;

    Decl d1, dx;
    ArrDecl arrD1;

    ArrIndexLoc arrIndex1, arrIndex2;
    VarLoc varloc1, varloc2;

    Int int1, int2, int3;
    Operator onePlus2, twoPlusThree, onePlusX;
    VarExpr xVar, yVar;
    ArrayAccess arr1;

    @BeforeEach
    void setUp() {
        mt = new StaticCheckEnv();
        containsX = mt.put(new Var("x"));
        // init decls
        d1 = new Decl("x", new Int(5));
        dx = new Decl("x", new VarExpr("x"));
        arrD1 = new ArrDecl("y", Arrays.asList(new Int(5), new VarExpr("x")));

        //init expressions
        int1 = new Int(1);
        int2 = new Int(2);
        int3 = new Int(3);
        xVar = new VarExpr("x");
        yVar = new VarExpr("y");

        onePlus2 = new Operator(int1, int2, "+");
        twoPlusThree = new Operator(int2, int3, "+");
        onePlusX= new Operator(int1, xVar, "+");

        arr1 = new ArrayAccess(xVar, int1);


        //init LHS
        xVar = new VarExpr("x");
        yVar = new VarExpr("y");

        varloc1 = new VarLoc("x");
        varloc2 = new VarLoc("y");

        arrIndex1 = new ArrIndexLoc(xVar, new Int(0));

        // init stmt
        ass1 = new Assignment(varloc1, int1);
        cond1 = new Conditional(int1, ass1, ass1);
        loop1 = new LoopConditional(int1, cond1);
        block1 = new StmtBlock(Arrays.asList(d1, dx), Arrays.asList(ass1, cond1),onePlus2);
        block2 = new StmtBlock(Arrays.asList(dx), Arrays.asList(ass1, cond1),onePlus2);
    }

    @Test
    void typecheck() {
        assertThrows(TypeCheckException.class, () -> ass1.staticCheck(mt));
        assertEquals(ass1, ass1.staticCheck(containsX));

        assertThrows(TypeCheckException.class, () -> cond1.staticCheck(mt));
        assertEquals(cond1, cond1.staticCheck(containsX));

        assertThrows(TypeCheckException.class, () -> loop1.staticCheck(mt));
        assertEquals(loop1, loop1.staticCheck(containsX));

        assertEquals(block1, block1.staticCheck(mt));
        assertThrows(TypeCheckException.class, () -> block2.staticCheck(mt));
    }
}