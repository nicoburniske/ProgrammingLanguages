import interpreter.*;
import jfkbits.LispParser;
import jfkbits.LispTokenizer;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static parser.ParseUtils.parseSVexp;
import static parser.ParseUtils.parse;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {

         if ("interpreter".equals(args[0])) {
            Object obj = new JSONParser().parse(new FileReader(args[1]));
            VExpr result = parse(obj);
            try {

                System.out.println(val);
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }
}
