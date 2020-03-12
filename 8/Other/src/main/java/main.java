import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import typechecker.parse.Parser;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPAL;
import typechecker.utils.StandardLib;

import java.io.FileReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(args[0]));
        TPAL tpal = Parser.parseJSON(obj);
        try {
            StarAST ast = tpal.typeCheck(StandardLib.stdLib()).getLeft();
            System.out.println(postProcessing(ast.toJava()));
        } catch (Exception e) {
            System.out.println(String.format("\"type error: %s\"", e.getMessage()));
            return;
        }
    }
    private static String postProcessing(String val) {
        val = val
                .replace("+", "plusRESERVED")
                .replace("*", "timesRESERVED")
                .replace("^", "exponentReserved");
        return String.format("package utils;\n" + "\n" +
                "import java.util.function.Function;\n" +
                "import java.util.function.Supplier;\n" +
                "\n" +
                "import static utils.StandardLib.*;\n" +
                "import utils.Cell;\n" +
                "\n" +
                "public class OutputtedCode {\n" +
                "    public static MyInteger run() {\n" +
                "        return %s;\n" +
                "\n" +
                "    }\n" +
                "}\n", val);
    }
}
