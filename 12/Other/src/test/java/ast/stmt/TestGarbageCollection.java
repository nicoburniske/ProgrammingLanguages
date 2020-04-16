package ast.stmt;

import ast.decl.ArrDecl;
import ast.decl.Decl;
import ast.expression.Int;
import ast.expression.Operator;
import ast.expression.VarExpr;
import ast.lhs.VarLoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestGarbageCollection {

    StmtBlock block1, block2,block3, block4, block5, block6;
    String result1, result2, result3, result4, result5, result6;
    int size1, size2, size3, size4, size5, size6;
    int sizeFail1, sizeFail2, sizeFail3;

    @BeforeEach
    void setUp() {
        block1 = new StmtBlock(new Int(4));
        result1 = "4";
        size1 = 0;
        sizeFail1 = -1;


        block2 = new StmtBlock(Arrays.asList(new Decl("a", new Int(5))),Arrays.asList(), new VarExpr("a"));
        result2 = "5";
        size2 = 1;
        sizeFail2 = 0;

        block3 = new StmtBlock(
                Arrays.asList(new Decl("a", new Int(5))),
                Arrays.asList(
                        new StmtBlock(
                            Arrays.asList(
                                    new Decl("b", new Int(8)),
                                    new ArrDecl("c", Arrays.asList()),
                                    new ArrDecl("d", Arrays.asList(new Int(5)))),
                            Arrays.asList(),
                            new VarExpr("a")
                )),
                new VarExpr("a"));
        result3 = "5";
        size3 = 7;


        block4 = new StmtBlock(
                Arrays.asList(new Decl("a", new Int(100))),
                Arrays.asList(
                        new LoopConditional(new VarExpr("a"),
                        new StmtBlock(
                                Arrays.asList(
                                        new Decl("b", new Int(8)),
                                        new ArrDecl("c", Arrays.asList()),
                                        new ArrDecl("d", Arrays.asList(new Int(5)))),
                                Arrays.asList(new Assignment(
                                        new VarLoc("a"),
                                        new Operator(new VarExpr("a"), new Int(-1), "+"))),
                                new VarExpr("a")
                        ))),
                new VarExpr("a"));
        size4 = 7;
        result4 = "0";

        block5 = new StmtBlock(
                Arrays.asList(new Decl("a", new Int(0))),
                Arrays.asList(
                        new Conditional(new VarExpr("a"),
                                new StmtBlock(new Int(6)),
                                new StmtBlock(
                                        Arrays.asList(
                                                new Decl("b", new Int(8)),
                                                new ArrDecl("c", Arrays.asList()),
                                                new ArrDecl("d", Arrays.asList(new Int(5)))),
                                        Arrays.asList(new Assignment(
                                                new VarLoc("a"),
                                                new Operator(new VarExpr("a"), new Int(-1), "+"))),
                                        new VarExpr("a")
                                ))),
                new VarExpr("a"));
        size5 = 1;
        result5 = "0";

        block6 = new StmtBlock(
                Arrays.asList(new Decl("a", new Int(100))),
                Arrays.asList(
                        new Conditional(new VarExpr("a"),
                                new StmtBlock(new Int(6)),
                                new StmtBlock(
                                        Arrays.asList(
                                                new Decl("b", new Int(8)),
                                                new ArrDecl("c", Arrays.asList()),
                                                new ArrDecl("d", Arrays.asList(new Int(5)))),
                                        Arrays.asList(new Assignment(
                                                new VarLoc("a"),
                                                new Operator(new VarExpr("a"), new Int(-10000), "+"))),
                                        new VarExpr("a")
                                ))),
                new VarExpr("a"));
        size6 = 7;
        result6 = "-9900";

    }

    void assertForTheNextFew(int start, int count, String result, StmtBlock block) {
        for(int ii = start; ii < start + count; ii ++) {
            assertEquals(result, block.run(ii));
        }
    }

    void assertThrowsToZero(int start, StmtBlock block) {
        for(int ii = 0; ii <= start; ii ++) {
            int finalIi = ii;
            assertEquals("\"out of space\"", block.run(finalIi));
        }
    }


    @Test
    void testNoStoreUsage() {
        //Does not throw because never tries to allocate
        assertEquals(result1,block1.run(sizeFail1));
        assertEquals(result1, block1.run(size1));
    }

    @Test
    void testOnePlace() {
        assertThrowsToZero(size2 -1 , block2);
        assertForTheNextFew(size2, 10,result2, block2);
    }

    @Test
    void nestedDelcs() {
        assertThrowsToZero(size3 -1 , block3);
        assertForTheNextFew(size3, 10,result3, block3);
    }

    @Test
    void allocateDeallocate() {
        assertThrowsToZero(size4 -1 , block4);
        assertForTheNextFew(size4, 10,result4, block4);
    }

    @Test
    void ifConditional() {
        assertThrowsToZero(size5 -1 , block5);
        assertForTheNextFew(size5, 10,result5, block5);
        assertThrowsToZero(size6 -1 , block6);
        assertForTheNextFew(size6, 10,result6, block6);
    }
}
