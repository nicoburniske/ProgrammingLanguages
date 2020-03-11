import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import typechecker.parse.Parser;
import typechecker.tpal.TPAL;
import typechecker.utils.StandardLib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
//        Object obj = new JSONParser().parse(new FileReader(args[0]));
//        TPAL tpal = Parser.parseJSON(obj);
//        try {
//            tpal.typeCheck(StandardLib.stdLib()).getLeft().toJSONString();
//        } catch (Exception e) {
//            System.out.println(String.format("\"type error: %s\"", e.getMessage()));
//            return;
//        }

        Function f = (a) -> a;
        //Integer i = ((Integer a) -> 10).apply();
        // create a program
        class class1 {
            int i;
            Supplier<Integer> a = () -> this.i + 5;
            class1(int i, int j) {
                this.i = i;
                this.c2 = new class2(j);
            }
            public int peform() {
                return c2.perform();
            }

            class2 c2;
            class class2 {
                int j;
                public class2(int j) {
                    this.j = j;
                }
                public int perform () {
                    return j + a.get();
                }
            }
        }
        class1 c1 = new class1(7, 5);
        System.out.println(c1.peform());
    }
}
