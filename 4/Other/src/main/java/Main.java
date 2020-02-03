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

            System.out.println(result.interpret(initializeStd()).result);
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }
    public static HashMap<Var, Answer> initializeStd() {
        HashMap<Var, Answer> stdLib = new HashMap<Var, Answer>();
        stdLib.put(new Var("+"), new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new FVExpr() {
            @Override
            public Answer interpret(HashMap<Var, Answer> acc) {
                return acc.get(new Var("left")).add(acc.get(new Var("right")));
            }

            @Override
            public String toJson() {
                return "+";
            }
        })));
        stdLib.put(new Var("*"), new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new FVExpr() {
            @Override
            public Answer interpret(HashMap<Var, Answer> acc) {
                return acc.get(new Var("left")).multiply(acc.get(new Var("right")));
            }

            @Override
            public String toJson() {
                return "*";
            }
        })));
        stdLib.put(new Var("^"), new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new FVExpr() {
            @Override
            public Answer interpret(HashMap<Var, Answer> acc) {
                return (acc.get(new Var("right")).pow(acc.get(new Var("left"))));
            }

            @Override
            public String toJson() {
                return "^";
            }
        })));
        return stdLib;
    }
}

