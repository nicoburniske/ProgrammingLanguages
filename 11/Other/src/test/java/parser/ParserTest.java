package parser;

import ast.stmt.StmtBlock;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import utils.EnvStoreTuple;
import utils.ValueEnvStoreTuple;
import utils.env.StaticCheckEnv;

import java.util.HashSet;

public class ParserTest {
    String example1 = "[\n" +
            "    [\"vec\", \"a\", \"=\", [1, 2, 3]],\n" +
            "    [\"vec\", \"b\", \"=\", [\"a\", 4, 5]],\n" +
            "    [\"vec\", \"c\", \"=\", [\"a\", \"b\"]],\n" +
            "    \"in\",\n" +
            "    [[\"a\", 1], \"=\", \"b\"],\n" +
            "    \"c\"\n" +
            "]\n";
    @Test
    public void parse() throws ParseException {
        Object arr = new JSONParser().parse(example1);
        StmtBlock block = Parser.parse(arr);
        block.staticCheck(new StaticCheckEnv());
        EnvStoreTuple tuple = new EnvStoreTuple();
        ValueEnvStoreTuple results = block.CESK(tuple);
        System.out.println(results.getLeft().toOutputString(results.getStore(), new HashSet<>()));
        System.out.println(results.getStore().toString());
    }
}