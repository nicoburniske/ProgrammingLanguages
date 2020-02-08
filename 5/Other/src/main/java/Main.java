import answer.Answer;
import answer.AnswerFunction;
import fvexpr.SFVExpr;
import fvexpr.Func;
import fvexpr.Var;
import org.json.simple.parser.JSONParser;
import parser.ParseUtils;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            if ("interpreter".equals(args[0])) {
                Object obj = new JSONParser().parse(new FileReader(args[1]));
                SFVExpr result = ParseUtils.parse(obj);
                try {
                    System.out.println(result.interpret(StoreUtils.initializeStd().get(0), StoreUtils.initializeStd().get(1)).toString());
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


}

