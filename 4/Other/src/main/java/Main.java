import fvexpr.FVExpr;
import interpreter.*;
import jfkbits.LispParser;
import jfkbits.LispTokenizer;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.ParseUtils;

import java.io.FileReader;
import java.io.IOException;

import static parser.ParseUtils.parseSVexp;
import static parser.ParseUtils.parse;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {

         if ("interpreter".equals(args[0])) {
            Object obj = new JSONParser().parse(new FileReader(args[1]));
            FVExpr result = ParseUtils.parse(obj);
            try {

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }
}
