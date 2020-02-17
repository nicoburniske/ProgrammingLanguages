package parse;

import org.json.simple.JSONArray;
import tpal.*;
import tpal.decl.TPALDecl;
import type.TypedVar;
import type.Type;
import type.TypeFunction;
import type.TypeInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    /**
     * TODO:DOCUMENT
     *
     * @param obj
     * @return
     */
    public static TPAL parseJSON(Object obj) {
        if (obj instanceof Long) {
            return new TPALInt((long) obj);
        }
        if (obj instanceof String) {
            return new TPALVar((String) obj);
        }
        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if (arr.size() == 3 && isStringAndisEqual(arr.get(1), ":")) {
                return parseTVar(obj);
            }
            if (arr.size() == 3 && isStringAndisEqual(arr.get(0), "fun*")) {
                return new TPALFunc(parseParams(arr.get(1)), parseJSON(arr.get(2)));
            }
            if (arr.size() >= 2 && isStringAndisEqual(arr.get(0), "call")) {
                List<TPAL> args = (List<TPAL>) arr.subList(2, arr.size()).stream().map(e -> parseJSON(e)).collect(Collectors.toList());
                return new TPALCall(parseJSON(arr.get(1)), args);
            }
            if (arr.size() == 4 && isStringAndisEqual(arr.get(0), "if-0")) {
                return new TPALConditional(parseJSON(arr.get(1)), parseJSON(arr.get(2)), parseJSON(arr.get(3)));
            }
            if (arr.size() >= 2 && arr.get(1) instanceof String ) {
                List<TPAL> args = new ArrayList<>();
                args.add(parseJSON(arr.get(0)));
                arr.subList(2, arr.size()).forEach( e -> args.add(parseJSON(e)));
                return new TPALCall(parseJSON(arr.get(1)),args);
            }
            if (arr.size() >= 1) {
                if (arr.size() == 1) {
                    return new TPALDeclArray(Collections.emptyList(), parseJSON(arr.get(0)));
                } else {
                    List<TPALDecl> decls = (List<TPALDecl>) arr.subList(0, arr.size() - 1).stream().map(e -> parseDecl(e)).collect(Collectors.toList());
                    return new TPALDeclArray(decls,
                            parseJSON(arr.get(arr.size() - 1)));
                }
            }



        }
        throw new IllegalArgumentException("Unable to Parse JSON into TPAL");
    }

    private static TPALDecl parseDecl(Object e) {
        if (e instanceof JSONArray) {
            JSONArray arr = (JSONArray) e;
            if (arr.size() == 4 && isStringAndisEqual(arr.get(0), "let")) {
                return new TPALDecl(parseTVar(arr.get(1)), parseJSON(arr.get(3)));
            }
        }
        throw new IllegalStateException("Unable to Parse JSON into TPALDecl");
    }

    private static List<TypedVar> parseParams(Object o) {
        if (o instanceof JSONArray) {
            JSONArray arr = (JSONArray) o;
            List<TypedVar> l = new ArrayList<>();
            arr.forEach(e -> l.add(parseTVar(e)));
            return l;
        }
        throw new IllegalStateException("Unable to Parse JSON into List<TVar>");

    }

    private static boolean isStringAndisEqual(Object obj, String str) {
        return (obj instanceof String) && ((String) obj).equals(str);
    }

    private static TypedVar parseTVar(Object obj) {
        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if (arr.size() == 3 && isStringAndisEqual(arr.get(1), ":")) {
                return new TypedVar((String) arr.get(0), parseType(arr.get(2)));
            }
        }
        throw new IllegalStateException("Unable to Parse JSON into TVar");
    }


    /**
     * TODO:DOCUMENT
     *
     * @param o
     * @return
     */
    private static Type parseType(Object o) {
        if (o instanceof String) {
            return new TypeInt();
        } else if (o instanceof JSONArray) {
            JSONArray arr = (JSONArray) o;
            boolean isRHS = false;
            List<Type> lhs = new ArrayList<>();
            for (Object obj : arr) {
                if (obj instanceof String && ((String) obj).equals("->")) {
                    isRHS = true;
                    continue;
                }
                if (!isRHS) {
                    lhs.add(parseType(obj));
                } else {
                    return new TypeFunction(lhs, parseType(obj));
                }
            }

        }
        throw new IllegalArgumentException("Unable to Parse JSON into Type");
    }
}
