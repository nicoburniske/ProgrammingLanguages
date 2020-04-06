package parser;


import ast.decl.ArrDecl;
import ast.decl.Decl;
import ast.expression.ArrayAccess;
import ast.expression.Int;
import ast.expression.Operator;
import ast.expression.VarExpr;
import ast.lhs.VarLoc;
import ast.stmt.Assignment;
import ast.stmt.Conditional;
import ast.stmt.LoopConditional;
import ast.stmt.StmtBlock;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    String testDecls, arrayAccess, operations, do0, if0;
    StmtBlock resultDecls, resultArrayAccess, resultOperations, resultDo0, resultIf0;

    @BeforeEach
    void setUp() {
        testDecls = "[\n" +
                "    [\"let\", \"x\", \"=\", 1],\n" +
                "    [\"vec\", \"z\", \"=\", [3, 4, 5]],\n" +
                "    \"in\",\n" +
                "    [\"x\", \"=\", \"z\"],\n" +
                "    \"x\"\n" +
                "]";
        resultDecls = new StmtBlock(Arrays.asList(new Decl("x", new Int(1)),
                new ArrDecl("z", Arrays.asList(new Int(3),
                        new Int(4),
                        new Int(5)))),
                Arrays.asList(new Assignment(new VarLoc("x"), new VarExpr("z"))),
                new VarExpr("x"));

        arrayAccess = "[\n" +
                "    [\"let\", \"x\", \"=\", 1],\n" +
                "    [\"vec\", \"z\", \"=\", [3, 4, 5]],\n" +
                "    \"in\",\n" +
                "    [\"x\", \"=\", [\"z\", 0]],\n" +
                "    \"x\"\n" +
                "]";
        resultArrayAccess = new StmtBlock(Arrays.asList(new Decl("x", new Int(1)),
                new ArrDecl("z", Arrays.asList(new Int(3),
                        new Int(4),
                        new Int(5)))),
                Arrays.asList(new Assignment(new VarLoc("x"), new ArrayAccess(new VarExpr("z"), new Int(0)))),
                new VarExpr("x"));

        operations = "\n" +
                "[\n" +
                "    [\"let\", \"x\", \"=\", 1],\n" +
                "    [\"vec\", \"z\", \"=\", [3, 4, 5]],\n" +
                "    \"in\",\n" +
                "    [\"x\", \"=\", [\"x\", \"+\", 1]],\n" +
                "    \"x\"\n" +
                "]";
        resultOperations = new StmtBlock(Arrays.asList(new Decl("x", new Int(1)),
                new ArrDecl("z", Arrays.asList(new Int(3),
                        new Int(4),
                        new Int(5)))),
                Arrays.asList(new Assignment(new VarLoc("x"), new Operator(new VarExpr("x"), new Int(1), "+"))),
                new VarExpr("x"));

        do0 = "[\n" +
                "    [\"let\", \"x\", \"=\", 10],\n" +
                "    [\"let\", \"sum\", \"=\", 0],\n" +
                "    \"in\",\n" +
                "    [\"do0\", \"x\",\n" +
                "            [\"in\",\n" +
                "                [\"sum\", \"=\", [\"x\", \"+\", \"sum\"]],\n" +
                "                [\"x\", \"=\", [\"x\", \"+\", -1]]\n" +
                "                , 5]\n" +
                "    ],\n" +
                "    \"sum\"\n" +
                "]";
        resultDo0 = new StmtBlock(Arrays.asList(new Decl("x", new Int(10)),
                new Decl("sum", new Int(0))),
                Arrays.asList(new LoopConditional( new VarExpr("x"), new StmtBlock(Arrays.asList(), Arrays.asList(
                        new Assignment(new VarLoc("sum"), new Operator(new VarExpr("x"), new VarExpr("sum"), "+")),
                        new Assignment(new VarLoc("x"), new Operator(new VarExpr("x"), new Int(-1), "+"))), new Int(5)))),
                new VarExpr("sum"));

        if0 = "[\n" +
                "    [\"let\", \"x\", \"=\", 10],\n" +
                "    [\"let\", \"y\", \"=\", 0],\n" +
                "    \"in\",\n" +
                "    [\"if0\", \"x\", [\"x\", \"=\", 12], [\"x\", \"=\", 42]]\n" +
                "    ,\n" +
                "    \"x\"\n" +
                "]";
        resultIf0 = new StmtBlock(
                Arrays.asList(new Decl("x", new Int(10)), new Decl("y", new Int(0))),
                Arrays.asList(new Conditional(
                        new VarExpr("x"),
                        new Assignment(new VarLoc("x"), new Int(12)),
                                new Assignment(new VarLoc("x"), new Int(42)))), new VarExpr("x"));

    }

    @Test
    void testParse() throws ParseException {
        assertEquals(resultDecls, Parser.parse(new JSONParser().parse(testDecls)));
        assertEquals(resultArrayAccess, Parser.parse(new JSONParser().parse(arrayAccess)));
        assertEquals(resultOperations, Parser.parse(new JSONParser().parse(operations)));
        assertEquals(resultDo0, Parser.parse(new JSONParser().parse(do0)));
        assertEquals(resultIf0, Parser.parse(new JSONParser().parse(if0)));
    }
}