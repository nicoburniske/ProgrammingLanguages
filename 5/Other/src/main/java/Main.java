import answer.Answer;
import answer.AnswerFunction;
import fvexpr.SFVExpr;
import fvexpr.Func;
import fvexpr.Var;
import org.json.simple.parser.JSONParser;
import parser.ParseUtils;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        try {
            if ("interpreter".equals(args[0])) {
                Object obj = new JSONParser().parse(new FileReader(args[1]));
                SFVExpr result = ParseUtils.parse(obj);
                try {
                    System.out.println(result.interpret(initializeStd()).result);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new IllegalArgumentException("Error: an illegal function was requested");
            }
        } catch (Exception e) {
            //Do nothing
        }
    }

    public static HashMap<Var, Answer> initializeStd() {
        HashMap<Var, Answer> stdLib = new HashMap<Var, Answer>();
        stdLib.put(new Var("+"), new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public Answer interpret(HashMap<Var, Answer> acc) {
                return acc.get(new Var("right")).add(acc.get(new Var("left")));
            }

            @Override
            public String toJson() {
                return "+";
            }
        })));
        stdLib.put(new Var("*"), new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
            @Override
            public Answer interpret(HashMap<Var, Answer> acc) {
                return acc.get(new Var("right")).multiply(acc.get(new Var("left")));
            }

            @Override
            public String toJson() {
                return "*";
            }
        })));
        stdLib.put(new Var("^"), new AnswerFunction(new Func(Arrays.asList(new Var("left"), new Var("right")), new SFVExpr() {
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

