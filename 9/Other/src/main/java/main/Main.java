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
            if (true) {
                Object obj = new JSONParser().parse("[\"call\",\n" +
                        "    [\"fun*\",[\"k\"],\n" +
                        "        [\"call\",\n" +
                        "            [\"fun*\",[\"k\",\"left\",\"right\"],\n" +
                        "                [\"call\",\"k\",[\"call\",\"+\",\"left\",\"right\"]]],\n" +
                        "        [\"fun*\",[\"of-f\"],[\"call\",[\"call\",\"of-f\",\"k\"], \"k\"]],5,100]],\n" +
                        "    [\"fun*\",[\"identity\"],\"identity\"]]\n");
                if(true) {
                    JSONArray input = (JSONArray)obj;
                    boolean wantValue = true;
                    Toy result = Parser.parse(input);
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

