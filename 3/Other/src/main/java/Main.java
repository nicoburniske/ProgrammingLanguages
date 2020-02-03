import interpreter.*;
import jfkbits.Atom;
import jfkbits.ExprList;
import jfkbits.LispParser;
import jfkbits.LispTokenizer;
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
    public static void main(String[] args) throws ParseException, IOException, LispParser.ParseException {
//        Object obj = new JSONParser().parse(new FileReader(args[1]));
//        VExpr result = parse(obj);
//        if ("sd".equals(args[0])) {
//            System.out.println(result.sd(new HashMap<String, Stack<AccumulatorType>>(), 0).toJson());
//        } else if ("interpreter".equals(args[0])) {
//            try {
//                //TODO: figure out the size
//                int val = result.evaluate(new StackList<StackList<Integer>>(result.getMaxNumberOfScopedVariables()));
//                System.out.println(val);
//            } catch (IllegalStateException e) {
//                System.out.println(e.getMessage());
//            }
//        } else {
//            throw new IllegalArgumentException("Error: an illegal function was requested");
//        }
        LispTokenizer tzr = new LispTokenizer(
                "((let x = (1 + 1)) (x + 3))");
        LispParser parser = new LispParser(tzr);
        LispParser.Expr result = parser.parseExpr();
        System.out.println(parseSVexp(result).toJson());
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
                return new VarPair(((Long) arr.get(0)).intValue(), ((Long) arr.get(1)).intValue());
            } else {
                List<Decl> declList = new ArrayList();
                if (arr.size() > 1) {
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


    private static VExpr parseSVexp(LispParser.Expr expr) {
        if (expr instanceof Atom) {
            return new Var(((Atom) expr).toString());
        } else if (expr instanceof ExprList) {
            ExprList exprList = (ExprList) expr;
            if (exprList.size() == 3 && exprList.get(1).toString().equals("*") || exprList.get(1).toString().equals("+")) {
                return new VOperator(parseSVexp(exprList.get(0)), parseSVexp(exprList.get(2)), exprList.get(1).toString());
            } else {
                List<Decl> declList = new ArrayList();
                if (exprList.size() > 1) {
                    for (LispParser.Expr o : exprList.subList(0, exprList.size() - 1)) {
                        declList.add(parseDecls(o));
                    }
                }
                return new VDeclArray(declList, parseSVexp(exprList.get(exprList.size() - 1)));
            }
        } else {
            throw new IllegalArgumentException("Nopw");
        }
    }

    private static Decl parseDecls(LispParser.Expr expr) {
        ExprList exprList = (ExprList) expr;
        return new Decl(new Var((exprList).get(1).toString()), parseSVexp(exprList.get(3)));
    }

    private static Decl parseDecl(JSONArray arr) {
        return new Decl(new Var((String) arr.get(1)), parse(arr.get(3)));
    }
}
