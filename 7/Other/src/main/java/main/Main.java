package main;

import org.json.simple.parser.JSONParser;
import typecheck.parse.Parser;
import typecheck.tpal.TPAL;
import typecheck.utils.StandardLib;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            if ("type_check".equals(args[0])) {
                Object obj = new JSONParser().parse(new FileReader(args[1]));
                TPAL tpal = Parser.parseJSON(obj);
                System.out.println(tpal.typeCheck(StandardLib.stdLib()).getLeft().toJSONString());
            } else {
                throw new IllegalArgumentException("Error: an illegal function was requested");
            }
        } catch (Exception e) {
            System.out.println(String.format("\"type error: %s\"", e.getMessage()));
        }
    }
}

