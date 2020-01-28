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
    public static void main(String[] args) throws ParseException, IOException, LispParser.ParseException {

        if ("ast".equals(args[0])) {
            LispTokenizer tzr = new LispTokenizer(new FileReader(args[1]));
            LispParser parser = new LispParser(tzr);
            LispParser.Expr result = parser.parseExpr();
            System.out.println(parseSVexp(result).toJson());
        } else if ("interpreter".equals(args[0])) {
            Object obj = new JSONParser().parse(new FileReader(args[1]));
            VExpr result = parse(obj);
            try {
                //TODO: figure out the size
                int val = result.evaluate(new StackList<StackList<Integer>>(result.getMaxNumberOfScopedVariables()));
                System.out.println(val);
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }
}
