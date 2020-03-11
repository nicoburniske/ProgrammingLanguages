import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import typechecker.parse.Parser;
import typechecker.tpal.TPAL;
import typechecker.utils.StandardLib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(args[0]));
        TPAL tpal = Parser.parseJSON(obj);
        try {
            tpal.typeCheck(StandardLib.stdLib()).getLeft().toJSONString();
        } catch (Exception e) {
            System.out.println(String.format("\"type error: %s\"", e.getMessage()));
            return;
        }

        Function f = (a) -> a;
        // create a program
    }
}
