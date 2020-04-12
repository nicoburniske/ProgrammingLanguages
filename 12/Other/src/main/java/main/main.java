package main;

import ast.stmt.StmtBlock;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import parser.Parser;
import utils.exceptions.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class main {
    /**
     * This is the main entry point to run the code. Arg[0] should be the input file
     */
    public static void main(String[] args) throws IOException, ParseException {
        //FileReader reader = new FileReader(args[0]);
        try {
            Object obj = new JSONParser().parse("{\"space\":  14,\n" +
                    "  \"program\": [\n" +
                    "  [\"vec\", \"z\", \"=\", [3, 4, 5]],\n" +
                    "  \"in\",\n" +
                    "  [[\"vec\", \"w\", \"=\", [1, 2, 3]],\"in\", \"w\"],\n" +
                    "  [[\"vec\", \"a\", \"=\", [88, 21, 39]],\"in\", \"a\"],\n" +
                    "  [\"z\", 2]]\n" +
                    "}");
            Object program = Parser.parseProgram(obj);
            int size = Parser.parseSize(obj);
            StmtBlock block = Parser.parse(program);
            System.out.println(block.run(size));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (org.json.simple.parser.ParseException exception) {
            System.out.println(ParseException.basicMessage);
            //Uncomment this line for debugging
            //System.out.println(exception.getMessage());
        }

    }
}
