import answer.Answer;
import fvexpr.FVExpr;
import fvexpr.Var;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.ParseUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws ParseException, IOException {

         if ("interpreter".equals(args[0])) {
            Object obj = new JSONParser().parse(new FileReader(args[1]));
            FVExpr result = ParseUtils.parse(obj);
            result.interpret(new HashMap<Var, Answer>());
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }
}
