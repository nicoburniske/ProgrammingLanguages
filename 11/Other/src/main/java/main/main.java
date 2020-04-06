package main;

import ast.stmt.StmtBlock;
import org.json.simple.parser.JSONParser;
import parser.Parser;
import utils.exceptions.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
        FileReader reader = new FileReader(args[0]);
        try {
            Object obj = new JSONParser().parse(reader);
            StmtBlock block = Parser.parse(obj);
            System.out.println(block.run());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (org.json.simple.parser.ParseException exception) {
            System.out.println(ParseException.basicMessage);
            //Uncomment this line for debugging
            //System.out.println(exception.getMessage());
        }

    }
}
