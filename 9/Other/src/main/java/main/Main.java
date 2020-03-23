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
        Object obj = new JSONParser().parse(new FileReader(args[1]));
        Toy parsed = Parser.parse(obj);
        CPSUtils.initializeNames(parsed);
        System.out.println(parsed.CPS().toJSONString());
    }
}

