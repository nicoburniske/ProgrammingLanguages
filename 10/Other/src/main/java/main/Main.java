package main;

import interpreter.parser.Parser;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.store.Store;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

import interpreter.pal.Toy;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, IllegalStateException {
        if (args.length == 2) {
            Object obj = new JSONParser().parse(new FileReader(args[0]));
            Object obj2 = new JSONParser().parse(new FileReader(args[1]));
            Toy input = Parser.parse(obj);
            CPSUtils.initializeNames(input);
            Toy actual = input.CPS();
            Toy expected = Parser.parse(obj2);
            boolean result = Toy.alphaEquals(expected, actual);
            if (!result) {
                System.out.println("///////////////////////////////////////////////////////////////////////");
                System.out.println(String.format("Test %s FAILED", args[0]));
                System.out.println(String.format("Test %s FAILED", args[1]));
                System.out.println(String.format("Input: %s", input.toJSONString()));
                System.out.println(String.format("Expected: %s", expected.toJSONString()));
                System.out.println(String.format("Actual: %s", actual.toJSONString()));
                System.out.println("///////////////////////////////////////////////////////////////////////\n");
            } else {
                System.out.println("///////////////////////////////////////////////////////////////////////");
                System.out.println("Success");
                System.out.println("///////////////////////////////////////////////////////////////////////\n");
            }
        } else {
            Object obj = new JSONParser().parse("[[\"let\", \"x\", \"=\", 2],\"x\"]");
            Toy parsed = Parser.parse(obj);

            CPSUtils.initializeNames(parsed);
            System.out.println(parsed.toJSONString());
            //System.out.println(parsed.CPS().toJSONString());
        }
    }
}

