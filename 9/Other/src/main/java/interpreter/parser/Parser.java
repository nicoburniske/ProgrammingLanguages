package interpreter.parser;

import interpreter.pal.*;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    /**
     * Parses a Object into a {@link Toy}
     * @param obj the object to be parsed
     * @return the {@link Toy} that represents that object
     */
    public static Toy parse(Object obj) {
        /**
         *     An Toy is one of:
         *      - a Var
         *      - an Int
         *      - a JSON array of the shape [TDecl,...,TDecl,Toy]
         *        all variables declared in one TDecl sequence are pairwise distinct
         *      - a JSON array of the shape ["fun*",VarList,Toy]
         *        all variables in one VarList sequence are pairwise distinct
         *      - a JSON array of the shape ["call",Toy,Toy,...,Toy]
         *      - a JSON array of the shape ["if-0",Toy,Toy,Toy]
         *
         *     An TDecl is
         *      - a JSON array of the shape ["let", Var, "=", Int]
         *      - a JSON array of the shape ["let", Var, "=", ["fun*",  VarList, Toy]]
         */
        if (obj instanceof Long) {
            return new ToyInt((long) obj);
        }
        if (obj instanceof String) {
            return new ToyVar((String) obj);
        }
        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if (arr.size() == 3 && isStringAndisEqual(arr.get(0), "fun*")) {
                return new ToyFunc(parseParams(arr.get(1)), parse(arr.get(2)));
            }
            if (arr.size() == 4 && isStringAndisEqual(arr.get(0), "if-0")) {
                return new ToyConditional(parse(arr.get(1)), parse(arr.get(2)), parse(arr.get(3)));
            }
            if (arr.size() >= 2 && isStringAndisEqual(arr.get(0), "call")) {
                List<Toy> args = (List<Toy>) arr.subList(2, arr.size()).stream().map(e -> parse(e)).collect(Collectors.toList());
                return new ToyCall(parse(arr.get(1)), args);
            }

            if (arr.size() >= 2 && arr.get(1) instanceof String) {
                List<Toy> args = new ArrayList<>();
                try {
                    args.add(parse(arr.get(0)));
                    arr.subList(2, arr.size()).forEach(e -> args.add(parse(e)));
                    return new ToyCall(parse(arr.get(1)), args);
                } catch (Exception ignored) {

                }
            }
            if (arr.size() >= 1) {
                if (arr.size() == 1) {
                    return new ToyDeclArray(Collections.emptyList(), parse(arr.get(0)));
                } else {
                    List<Decl> decls = (List<Decl>) arr.subList(0, arr.size() - 1).stream().map(e -> parseDecl(e)).collect(Collectors.toList());
                    return new ToyDeclArray(decls, parse(arr.get(arr.size() - 1)));
                }
            }
        }
        throw new IllegalArgumentException("Unable to Parse JSON into Toy");
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
     * Converts a object into a List of Parmas
     *
     * @param o the object to be converted
     * @return the List of Params
     */
    private static List<ToyVar> parseParams(Object o) {
        try {
            if (o instanceof JSONArray) {
                JSONArray arr = (JSONArray) o;
                List<ToyVar> l = new ArrayList<>();
                arr.forEach(e -> l.add((ToyVar) parse(e)));
                return l;
            }
        } catch (ClassCastException e) {
            throw new IllegalStateException("Unable to Parse JSON into List<ToyVar>");
        }
        throw new IllegalStateException("Unable to Parse JSON into List<ToyVar>");
    }

    /**
     * Converts a object into a decl
     *
     * @param e the object to be converted
     * @return a TPALDecl
     */
    private static Decl parseDecl(Object e) {
        try {
            if (e instanceof JSONArray) {
                JSONArray arr = (JSONArray) e;
                if (arr.size() == 4 && isStringAndisEqual(arr.get(0), "let")) {
                    return new Decl((ToyVar) parse(arr.get(1)), parse(arr.get(3)));
                }
            }
        } catch (ClassCastException c) {
            throw new IllegalStateException("Unable to Parse JSON into Decl");
        }
        throw new IllegalStateException("Unable to Parse JSON into Decl");
    }
}
