package main;

import interpreter.parser.Parser;
import interpreter.utils.CPSUtils;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

import interpreter.pal.Toy;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, IllegalStateException {
            FileReader reader = new FileReader(args[0]);
            Object obj = new JSONParser().parse(reader);
            Parser.generateValidSeqNames(obj);
            Toy parsed = Parser.parse(obj);
            CPSUtils.initializeNames(parsed);
            try {
                System.out.println(parsed.run().toJSONString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}

