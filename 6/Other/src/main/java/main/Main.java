package main;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import parse.Parser;
import tpal.TPAL;
import utils.StandardLib;

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
            System.out.println(e.getMessage());
        }
    }
}

