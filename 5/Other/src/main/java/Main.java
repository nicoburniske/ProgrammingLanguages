import answer.Answer;
import answer.AnswerFunction;
import fvexpr.SFVExpr;
import fvexpr.Func;
import fvexpr.Var;
import org.json.simple.JSONArray;
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
                if(obj instanceof JSONArray && ((JSONArray)obj).size() > 1 && ((JSONArray)obj).get(0) instanceof String ) {
                    JSONArray input = (JSONArray)obj;
                    Boolean wantValue = ((String)(input.get(0))).equals("value");
                    SFVExpr result = ParseUtils.parse(input.get(1));
                    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0);
                    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1);
                    String ans;
                    try {
                        ans = result.interpret(stdEnv, stdStore).toJSONString();
                    } catch (IllegalStateException e) {
                        ans = e.getMessage();
                    }
                    if(wantValue) {
                        System.out.println(String.format("[\"value\", %s]", ans));
                    } else {
                        System.out.println(String.format("[\"store\", %s]", stdStore.toJSONString()));
                    }
                }


            } else {
                throw new IllegalArgumentException("Error: an illegal function was requested");
            }
        } catch (Exception e) {
            System.out.println("ooops" + e.getMessage());
        }
    }


}

