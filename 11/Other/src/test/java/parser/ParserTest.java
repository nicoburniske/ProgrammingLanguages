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
            "    [\"vec\", \"n\", \"=\", [22,23,24]],\n" +
            "    \"in\",\n" +
            "        [\n" +
            "            [\"vec\", \"a\", \"=\", [32, 12, 17]],\n" +
            "            \"in\", \n" +
            "            [[\"n\", 2], \"=\", \"a\"],\n" +
            "            5\n" +
            "        ],\n" +
            "    \"n\"\n" +
            "]";
    @Test
    public void parse() throws ParseException {
        Object arr = new JSONParser().parse(example1);
        StmtBlock block = Parser.parse(arr);
        // EnvStoreTuple tuple = new EnvStoreTuple();
        // ValueEnvStoreTuple results = block.CESK(tuple);
        // System.out.println(results.getLeft().toOutputString(results.getStore(), new HashSet<>()));
        // System.out.println(results.getStore().toString());
        System.out.println(block.run());
    }
}