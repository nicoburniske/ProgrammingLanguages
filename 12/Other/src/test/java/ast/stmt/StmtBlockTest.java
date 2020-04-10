package ast.stmt;

import ast.decl.ArrDecl;
import ast.expression.Int;
import ast.expression.VarExpr;
import ast.lhs.ArrIndexLoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EnvStoreTuple;
import utils.store.Store;
import value.*;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class StmtBlockTest {

    StmtBlock test1,test2,test3,test4;
    IValue result1, result2, result3, result4;
    Store result2Store, result3Store;

    @BeforeEach
    void setUp() {
        test1 = new StmtBlock(Arrays.asList(), Arrays.asList(), new Int(4));
        result1 = new IValueInt(4);
        test2 = new StmtBlock(Arrays.asList(new ArrDecl("a", Arrays.asList(new Int(1), new Int(2), new Int(3)))),Arrays.asList(), new VarExpr("a"));
        result2 = new IValueReference(new Location(1));
        result2Store = new Store().put(new Location(0), new IValueReference(new Location(1))).put(new Location(1), new IValueArray(2,3)).put(new Location(2), new IValueInt(1)).put(new Location(3), new IValueInt(2)).put(new Location(4), new IValueInt(3));

        test3 = new StmtBlock(Arrays.asList(new ArrDecl("a", Arrays.asList(new Int(1), new Int(2), new Int(3)))),Arrays.asList(new Assignment(new ArrIndexLoc(new VarExpr("a"), new Int(1)), new Int(5))), new VarExpr("a"));
        result3 = new IValueReference(new Location(1));
        result3Store = new Store().put(new Location(0), new IValueReference(new Location(1))).put(new Location(1), new IValueArray(2,3)).put(new Location(2), new IValueInt(1)).put(new Location(3), new IValueInt(5)).put(new Location(4), new IValueInt(3));
    }

    @Test
    void CESK() {
        assertEquals(result1, test1.CESK(new EnvStoreTuple()).getLeft());
        assertEquals(result2, test2.CESK(new EnvStoreTuple()).getLeft());
        assertEquals(result3, test3.CESK(new EnvStoreTuple()).getLeft());
    }

    @Test
    void run() {
        assertEquals(result1.toJSONString(), test1.run());
        assertEquals(result2.toOutputString(result2Store, new HashSet<>()), test2.run());
        assertEquals(result3.toOutputString(result3Store, new HashSet<>()), test3.run());
    }

    @Test
    void transition() {
        assertEquals(new Store(), test1.transition(new EnvStoreTuple()));
        assertEquals(result2Store.toString(), test2.transition(new EnvStoreTuple()).toString());
        assertEquals(result3Store.toString(), test3.transition(new EnvStoreTuple()).toString());
    }
}