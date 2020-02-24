package main;

import interpreter.pal.PAL;
import interpreter.utils.EnvStoreTuple;
import org.json.simple.parser.JSONParser;
import typechecker.parse.Parser;
import typechecker.tpal.TPAL;
import typechecker.utils.StandardLib;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
                Object obj = new JSONParser().parse(new FileReader(args[0]));
                TPAL tpal = Parser.parseJSON(obj);
                try {
                    tpal.typeCheck(StandardLib.stdLib()).getLeft().toJSONString();
                } catch (Exception e) {
                    System.out.println(String.format("\"type error: %s\"", e.getMessage()));
                    return;
                }
                System.out.println("Successfully Typechecked");
                PAL pal = tpal.fillet();
                try {
                    // TODO: toJsonString();
                    System.out.println(pal.interpret(EnvStoreTuple.stdLib()).getLeft().toJSONString());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(String.format("\"run-time error: %s\"", e.getMessage()));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

