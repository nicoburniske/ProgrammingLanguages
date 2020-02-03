package parser;

import fdecl.FDecl;
import fdecl.FDeclFVExpr;
import fdecl.FDeclInt;
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
            if (arr.size() >= 3 && arr.get(0) instanceof String && ((String)arr.get(0)).equals("call")){
                List l = arr.subList(2,arr.size());
                List<FVExpr> n = new ArrayList<FVExpr>();
                for (Object o : l) {
                    n.add(parse(o));
                }
                return new FuncCall(parse(arr.get(1)),n);
            } else if(arr.size() == 3) {
                if(arr.get(1) instanceof String) {
                    if(((String)arr.get(1)).equals("*")) {
                        return new Multiply(parse(arr.get(0)), parse(arr.get(2)));
                    } else if (((String)arr.get(1)).equals("+")) {
                        return new Plus(parse(arr.get(0)), parse(arr.get(2)));
                    }  else {
                        throw new IllegalStateException("JSON could not be parsed");
                    }
                } else if (arr.get(0) instanceof String) {
                    if (((String)arr.get(0)).equals("fun*") && arr.get(1) instanceof List){
                        return new Func(parseVarList((List<Object>)arr.get(1)), parse(arr.get(2)));
                    }
                }
            } else if (arr.size() == 4) {
                if (arr.get(0) instanceof String && ((String)arr.get(0)).equals("if-0")) {
                    return new Conditional(parse(arr.get(1)), parse(arr.get(2)), parse(arr.get(3)));
                } else {
                    throw new IllegalStateException("JSON could not be parsed");
                }
            } else if (arr.size() >= 1){
                if( arr.size() ==1) {
                    return new DeclArray(new ArrayList<FDecl>(), parse(arr.get(0)));
                } else {
                    return new DeclArray( parseDeclList(arr.subList(0,arr.size() - 1 )), parse(arr.get(arr.size()-1)));
                }
            }
        }
        throw new IllegalStateException("JSON could not be parsed");
    }

    private static List<FDecl> parseDeclList(List<Object> decls) {
        List<FDecl> n = new ArrayList<FDecl>(decls.size());
        for(Object dec : decls) {
            n.add(parseDecl(dec));
        }
        return n;

    }

    private static FDecl parseDecl(Object dec) {
        if(dec instanceof JSONArray  && ((JSONArray) dec).size() == 4) {
            JSONArray arr = (JSONArray) dec;
            if(((String)arr.get(0)).equals("let") && parse(arr.get(1)) instanceof Var && ((String)arr.get(2)).equals("=")) {
                if(arr.get(3) instanceof JSONArray && parse(arr.get(3)) instanceof Func) {
                    return new FDeclFVExpr((Var)parse(arr.get(1)), (Func)parse(arr.get(3)));
                } else if (arr.get(3) instanceof Long) {
                    return new FDeclInt((Var)parse(arr.get(1)), new Int((Long)arr.get(3)));
                }
            }
        }
        throw new IllegalStateException("JSON could not be parsed");
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

}
