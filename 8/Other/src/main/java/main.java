import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import typechecker.parse.Parser;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPAL;
import typechecker.utils.StandardLib;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(args[0]));
        TPAL tpal = Parser.parseJSON(obj);
        Map<String, String> reservedKeywords = new HashMap(){{
            put("!", "bangReplacement");
            put("*", "multReplacement");
            put("+", "addReplacement");
            put("@", "atReplacement");
            put("^", "exptReplacement");
            put("=", "equalsReplacement");
        }};
        try {
            StarAST ast = tpal.typeCheck(StandardLib.stdLib()).getLeft();
            ast.replaceReservedKeywords(reservedKeywords);
            System.out.println(postProcessing(ast.toJava()));
        } catch (Exception e) {
            System.out.println(String.format("\"type error: %s\"", e.getMessage()));
            return;
        }
    }

    /**
     * This function creates The file to go around the Java Program.
     * It add the imports, as well as removing some function names that
     * Java does not allow
     * @param val
     * @return
     */
    private static String postProcessing(String val) {
        val = val
                .replace("+", "plusRESERVED")
                .replace("*", "timesRESERVED")
                .replace("^", "exponentRESERVED")
                .replace("switch", "switch_")
                .replace("int", "int_")
                .replace("double", "double_");
//                .replace("@", "atRESERVED")
//                .replace("!", "exclaimRESERVED");
//                .replace("=", "equalsRESERVED");
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
