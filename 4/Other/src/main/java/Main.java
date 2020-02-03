import answer.Answer;
import answer.AnswerFunction;
import fvexpr.BinaryFunction;
import fvexpr.FVExpr;
import fvexpr.Func;
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
             HashMap<String, BinaryFunction> stdLib = new HashMap<String, BinaryFunction>();
             stdLib.put("+", new BinaryFunction(){

                 @Override
                 public Answer apply(Answer left, Answer right) {
                     return null;
                 }

                 @Override
                 public String toJson() {
                     return null;
                 }
             });
            System.out.println(result.interpret(new HashMap<Var, Answer>()).result);
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }
}
