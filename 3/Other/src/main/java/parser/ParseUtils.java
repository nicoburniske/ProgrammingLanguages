package parser;

import interpreter.*;
import jfkbits.Atom;
import jfkbits.ExprList;
import jfkbits.LispParser;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities class holding methods used for parsing and mapping in Main.
 */
public final class ParseUtils {
    /**
     * @param obj to be converted to VExpr.
     * @return The converted VExpr.
     */
    public static VExpr parse(Object obj) {
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

    /**
     * Maps S-expression syntax to java VExpr.
     * @param expr The S-Expression to be converted.
     * @return the mapped VExpr.
     */
    public static VExpr parseSVexp(LispParser.Expr expr) {
        if(expr instanceof Atom) {
            try {
                Integer num = Integer.parseInt(expr.toString());
                return new VInt(num);
            } catch (NumberFormatException ne) {
                return  new Var(((Atom)expr).toString());
            }
        } else if (expr instanceof ExprList) {
            ExprList exprList = (ExprList) expr;
            if(exprList.size() == 3 && (exprList.get(1).toString().equals("*") || exprList.get(1).toString().equals("+"))) {
                return new VOperator(parseSVexp(exprList.get(0)), parseSVexp(exprList.get(2)), exprList.get(1).toString());
            } else {
                List<Decl> declList = new ArrayList();
                if(exprList.size() > 1) {
                    for (LispParser.Expr o : exprList.subList(0, exprList.size() - 1)) {
                        declList.add(parseLispDecl(o));
                    }
                }
                return new VDeclArray(declList, parseSVexp(exprList.get(exprList.size() - 1)));
            }
        } else {
            throw new IllegalStateException("Nopw");
        }
    }

    /**
     * Maps an SDecl onto a Decl
     * @param expr The SDecl to be mapped.
     * @return the equivalent decl
     */
    private static Decl parseLispDecl(LispParser.Expr expr) {
        ExprList exprList = (ExprList)expr;
        return new Decl(new Var((exprList).get(1).toString()), parseSVexp(exprList.get(3)));
    }

    /**
     * Maps a decl in JSON to a Decl.
     * @param arr the JSON array representing a decl.
     * @return the equivalent decl.
     */
    private static Decl parseDecl(JSONArray arr) {
        return new Decl(new Var((String) arr.get(1)), parse(arr.get(3)));
    }
}
