import interpreter.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        Object obj = new JSONParser().parse(new FileReader(args[1]));
        VExpr result = parse(obj);
        if ("sd".equals(args[0])) {
            System.out.println(result.sd(new HashMap<String, Stack<AccumulatorType>>(), 0).toJson());
        } else if ("interpreter".equals(args[0])) {
            try {
                //TODO: figure out the size
                int val = result.evaluate(new StackList<StackList<Integer>>(123012));
                System.out.println(val);
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
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
            } else if (arr.size() > 1 && arr.get(0) instanceof Long && arr.get(1) instanceof Long) {
                return new VarPair(((Long)arr.get(0)).intValue(), ((Long)arr.get(1)).intValue());
            }
            else {
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
