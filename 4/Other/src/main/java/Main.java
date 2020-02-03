import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerInt;
import fvexpr.FVExpr;
import fvexpr.Func;
import fvexpr.Operator;
import fvexpr.Var;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.ParseUtils;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws ParseException, IOException {

         if ("interpreter".equals(args[0])) {
            Object obj = new JSONParser().parse(new FileReader(args[1]));
            FVExpr result = ParseUtils.parse(obj);
             HashMap<Var, Answer> stdLib = new HashMap<Var, Answer>();
             stdLib.put(new Var("+"), new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new FVExpr() {
                         @Override
                         public Answer interpret(HashMap<Var, Answer> acc) {
                             return new AnswerInt(((BigInteger)acc.get(new Var("left")).result).add((BigInteger)acc.get(new Var("right")).result));
                         }

                         @Override
                         public String toJson() {
                             return null;
                         }
                     })));
            System.out.println(result.interpret(new HashMap<Var, Answer>()).result);
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }
}
