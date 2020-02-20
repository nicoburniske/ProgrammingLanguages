package typecheck.parse;

import org.json.simple.JSONArray;
import typecheck.tpal.*;
import typecheck.tpal.decl.TPALDecl;
import typecheck.type.TypedVar;
import typecheck.type.Type;
import typecheck.type.TypeFunction;
import typecheck.type.TypeInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class containing the parser for JSON->TPAL
 */
public class Parser {
    /**
     * Takes an Object returned by the JSON reder and turns it into a TPAL
     *
     * @param obj the object to be converted
     * @return a TPAL
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
            if (arr.size() >= 2 && arr.get(1) instanceof String) {
                List<TPAL> args = new ArrayList<>();
                try {
                        args.add(parseJSON(arr.get(0)));
                        arr.subList(2, arr.size()).forEach(e -> args.add(parseJSON(e)));
                        return new TPALCall(parseJSON(arr.get(1)), args);
                } catch (Exception ignored) {

                }
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

    /**
     * Converts a object into a decl
     *
     * @param e the object to be converted
     * @return a TPALDecl
     */
    private static TPALDecl parseDecl(Object e) {
        if (e instanceof JSONArray) {
            JSONArray arr = (JSONArray) e;
            if (arr.size() == 4 && isStringAndisEqual(arr.get(0), "let")) {
                return new TPALDecl(parseTVar(arr.get(1)), parseJSON(arr.get(3)));
            }
        }
        throw new IllegalStateException("Unable to Parse JSON into TPALDecl");
    }

    /**
     * Converts a object into a List of Parmans
     *
     * @param o the object to be converted
     * @return the List of Params
     */
    private static List<TypedVar> parseParams(Object o) {
        if (o instanceof JSONArray) {
            JSONArray arr = (JSONArray) o;
            List<TypedVar> l = new ArrayList<>();
            arr.forEach(e -> l.add(parseTVar(e)));
            return l;
        }
        throw new IllegalStateException("Unable to Parse JSON into List<TVar>");

    }

    /**
     * A helper method to check if an obj is a specified string
     *
     * @param obj the obj to be checked
     * @param str the specified string
     * @return
     */
    private static boolean isStringAndisEqual(Object obj, String str) {
        return (obj instanceof String) && ((String) obj).equals(str);
    }

    /**
     * Converts an object into a TypedVar
     *
     * @param obj the object to be converted
     * @return the TypedVar
     */
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
     * Converts an object into a Type
     *
     * @param o the object to be converted
     * @return the resultant typecheck.type
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
