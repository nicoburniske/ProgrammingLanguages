import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * [["let","x","=",5],
 * <p>
 * ["let","y","=",["x","+",1]]
 * <p>
 * ["x","*","y"]]
 */

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        Object obj = new JSONParser().parse(new FileReader("../SDTests/1-in.json"));
        VExpr result = parse(obj).sd(new HashMap<String, Stack<AccumulatorType>>(), 0);
        int result2 = parse(obj).evaluate(new HashMap<String, Stack<Integer>>());
        System.out.println(parse(obj).toJson());
        System.out.println(result.toJson());
        System.out.println(result2);
    }

    private static VExpr parse(Object obj) {
        if (obj instanceof String) {
            return new Var((String) obj);
        } else if (obj instanceof Long) {
            return new VInt(((Long) obj).intValue());
        } else if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if (arr.size() > 1 && arr.get(1) instanceof String) {
                return new VOperator(parse(arr.get(0)), parse(arr.get(2)), (String) arr.get(1));
            } else {
                List<Decl> declList = new ArrayList();
                if(arr.size() > 1) {
                    for (Object o : arr.subList(0, arr.size() - 1)) {
                        declList.add(parseDecl((JSONArray) o));
                    }
                }
                return new VDeclArray(declList, parse(arr.get(arr.size() - 1)));
            }
        } else {
            throw new IllegalStateException("JSON could not be parsed");
        }
    }

    private static Decl parseDecl(JSONArray arr) {
        return new Decl(new Var((String) arr.get(1)), parse(arr.get(3)));
    }
}
