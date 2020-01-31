package parser;

import fvexpr.*;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public final class ParseUtils {
    public static FVExpr parse(Object obj) {
        if (obj instanceof String) {
            return new Var((String) obj);
        } else if (obj instanceof Long) {
            return new Int((Long) obj);
        } else if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if(arr.size() == 3) {
                if(arr.get(1) instanceof String) {
                    if(((String)arr.get(1)).equals("*")) {
                        return new Multiply(parse(arr.get(0)), parse(arr.get(2)));
                    } else if (((String)arr.get(1)).equals("+")) {
                        return new Plus(parse(arr.get(0)), parse(arr.get(2)));
                    } else if (((String)arr.get(0)).equals("fun*") && arr.get(1) instanceof List){
                        return new Func(parseVarList((List)arr.get(1)), parse(arr.get(2)));
                    }
                }
            }
        }
    }
    private static List<Var> parseVarList(List<Object> l) {
        List<Var> n = new ArrayList<Var>(l.size());
        for(Object o : l) {
            if(o instanceof String) {
                n.add(new Var((String)o));
            } else {
                throw new IllegalStateException("JSON could not be parsed");
            }
        }
        return n;

    }
//    public static VExpr parse(Object obj) {
//        if (obj instanceof String) {
//            return new Var((String) obj);
//        } else if (obj instanceof Long) {
//            return new VInt(((Long) obj).intValue());
//        } else if (obj instanceof JSONArray) {
//            JSONArray arr = (JSONArray) obj;
//            if (arr.size() > 1 && arr.get(1) instanceof String) {
//                return new VOperator(parse(arr.get(0)), parse(arr.get(2)), (String) arr.get(1));
//            } else if (arr.size() > 1 && arr.get(0) instanceof Long && arr.get(1) instanceof Long) {
//                return new VarPair(((Long)arr.get(0)).intValue(), ((Long)arr.get(1)).intValue());
//            }
//            else {
//                List<Decl> declList = new ArrayList();
//                if(arr.size() > 1) {
//                    for (Object o : arr.subList(0, arr.size() - 1)) {
//                        declList.add(parseDecl((JSONArray) o));
//                    }
//                }
//                return new VDeclArray(declList, parse(arr.get(arr.size() - 1)));
//            }
//        } else {
//            throw new IllegalStateException("JSON could not be parsed");
//        }
//    }
//
//    public static VExpr parseSVexp(LispParser.Expr expr) {
//        if(expr instanceof Atom) {
//            try {
//                Integer num = Integer.parseInt(expr.toString());
//                return new VInt(num);
//            } catch (NumberFormatException ne) {
//                return  new Var(((Atom)expr).toString());
//            }
//        } else if (expr instanceof ExprList) {
//            ExprList exprList = (ExprList) expr;
//            if(exprList.size() == 3 && (exprList.get(1).toString().equals("*") || exprList.get(1).toString().equals("+"))) {
//                return new VOperator(parseSVexp(exprList.get(0)), parseSVexp(exprList.get(2)), exprList.get(1).toString());
//            } else {
//                List<Decl> declList = new ArrayList();
//                if(exprList.size() > 1) {
//                    for (LispParser.Expr o : exprList.subList(0, exprList.size() - 1)) {
//                        declList.add(parseDecls(o));
//                    }
//                }
//                return new VDeclArray(declList, parseSVexp(exprList.get(exprList.size() - 1)));
//            }
//        } else {
//            throw new IllegalStateException("Nopw");
//        }
//    }
//
//    public static Decl parseDecls(LispParser.Expr expr) {
//        ExprList exprList = (ExprList)expr;
//        return new Decl(new Var((exprList).get(1).toString()), parseSVexp(exprList.get(3)));
//    }
//
//    public static Decl parseDecl(JSONArray arr) {
//        return new Decl(new Var((String) arr.get(1)), parse(arr.get(3)));
//    }
}
