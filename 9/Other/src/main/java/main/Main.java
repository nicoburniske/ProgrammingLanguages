package main;
import interpreter.parser.Parser;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.store.Store;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

import interpreter.pal.Toy;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, IllegalStateException {
            if ("interpreter".equals(args[0])) {
                Object obj = new JSONParser().parse(new FileReader(args[1]));
                if(obj instanceof JSONArray && ((JSONArray)obj).size() > 1 && ((JSONArray)obj).get(0) instanceof String ) {
                    JSONArray input = (JSONArray)obj;
                    boolean wantValue = ((String)(input.get(0))).equals("value");
                    Toy result = Parser.parse(input.get(1));
                    ValueEnvStoreTuple ans;
                    Store store;
                    String finalAns;

                    if(wantValue) {
                        try {
                            ans = result.interpret(EnvStoreTuple.stdLib());
                            finalAns = ans.getLeft().toJSONString();
                        } catch (IllegalStateException e) {
                            finalAns = e.getMessage();
                        }
                        System.out.println(String.format("[\"value\", %s]", finalAns));
                    } else {
                        ans = result.interpret(EnvStoreTuple.stdLib());
                        store = ans.getRight().getRight();
                        System.out.println(String.format("[\"store\", %s]", store.toJSONString()));
                    }
                }
            } else {
                throw new IllegalArgumentException("Error: an illegal function was requested");
            }
    }
}

