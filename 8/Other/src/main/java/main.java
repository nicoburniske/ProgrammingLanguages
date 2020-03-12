import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import typechecker.parse.Parser;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPAL;
import typechecker.utils.StandardLib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(args[0]));
        TPAL tpal = Parser.parseJSON(obj);
        try {
            StarAST ast = tpal.typeCheck(StandardLib.stdLib()).getLeft();
            System.out.println(ast.toJSONString());
            System.out.println(ast.toJava().replace("+", "plusRESERVED").replace("*", "timesRESERVED"));
        } catch (Exception e) {
            System.out.println(String.format("\"type error: %s\"", e.getMessage()));
            return;
        }
    }
}
