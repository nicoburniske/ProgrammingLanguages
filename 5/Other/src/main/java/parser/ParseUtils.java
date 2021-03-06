package parser;

import fdecl.SFVDecl;
import fvexpr.*;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ParseUtils {

    /**
     * This function parses what the JSON reader returns and turns it into a FVExpr
     * @param obj object to be Parsed
     * @return
     */
    public static SFVExpr parse(Object obj) {
        if (obj instanceof String) {
            return new Var((String) obj);
        } else if (obj instanceof Long) {
            return new Int((Long) obj);
        } else if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if (arr.size() >= 2 && arr.get(0) instanceof String && ((String)arr.get(0)).equals("call")){
                List l = arr.subList(2,arr.size());
                List<SFVExpr> n = new ArrayList<SFVExpr>();
                for (Object o : l) {
                    n.add(parse(o));
                }
                return new FuncCall(parse(arr.get(1)),n);
            } else if(arr.size() == 3) {
                if(arr.get(1) instanceof String) {
                        return new Operator(parse(arr.get(0)), Arrays.asList(parse(arr.get(2))), new Var((String)arr.get(1)));
                } else if (arr.get(0) instanceof String) {
                    if (((String)arr.get(0)).equals("fun*") && arr.get(1) instanceof List){
                        return new Func(parseVarList((List<Object>)arr.get(1)), parse(arr.get(2)));
                    }
                }
            }
            if(arr.size() == 2) {
                if(arr.get(1) instanceof String) {
                    return new Operator(parse(arr.get(0)), Arrays.asList(), new Var((String)arr.get(1)));
                } else if(arr.get(0) instanceof String) {
                    return new Operator(parse(arr.get(1)), Arrays.asList(), new Var((String)arr.get(0)));
                }
            }
            if (arr.size() >= 1){
                if (arr.size() == 4 && arr.get(0) instanceof String && ((String)arr.get(0)).equals("if-0")) {
                    return new Conditional(parse(arr.get(1)), parse(arr.get(2)), parse(arr.get(3)));
                }
                else if( arr.size() ==1) {
                    return new DeclArray(new ArrayList<SFVDecl>(), parse(arr.get(0)));
                } else {
                    return new DeclArray( parseDeclList(arr.subList(0,arr.size() - 1 )), parse(arr.get(arr.size()-1)));
                }
            }
        }
        throw new IllegalStateException("JSON could not be parsed");
    }

    /**Converts a list of Objects into a list of Decls
     *
     * @param decls the objects to be convertecd to decls
     * @return
     */
    private static List<SFVDecl> parseDeclList(List<Object> decls) {
        List<SFVDecl> n = new ArrayList<SFVDecl>(decls.size());
        for(Object dec : decls) {
            n.add(parseDecl(dec));
        }
        return n;

    }

    /**
     * Parses a singular decl
     * @param dec
     * @return
     */
    private static SFVDecl parseDecl(Object dec) {
        if(dec instanceof JSONArray  && ((JSONArray) dec).size() == 4) {
            JSONArray arr = (JSONArray) dec;
            if(((String)arr.get(0)).equals("let") && parse(arr.get(1)) instanceof Var && ((String)arr.get(2)).equals("=")) {
                    return new SFVDecl((Var)parse(arr.get(1)), parse(arr.get(3)));
            }
        }
        throw new IllegalStateException("JSON could not be parsed");
    }

    /**
     * Parses the Var list in a {@link Func}
     * @param l the list of Vars
     * @return
     */
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
