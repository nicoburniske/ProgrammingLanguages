import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *  [["let","x","=",5],
 *
 *      ["let","y","=",["x","+",1]]
 *
 *      ["x","*","y"]]
 */

public class Main {
    public static void main(String[] args) throws ParseException {
        Object obj = new JSONParser().parse("[[\"let\",\"x\",\"=\",5],[\"let\",\"y\",\"=\",[\"x\",\"+\",1]],[\"x\",\"*\",\"y\"]]");
        VExpr result = parse(obj);
    }

    private static VExpr parse(Object obj) {
        System.out.println(obj.getClass());
       if (obj instanceof String) {
           System.out.println("String : " + (String)obj);
           return new Var((String)obj);
       } else if (obj instanceof Long) {
           System.out.println("Integer : " + ((Long)obj).intValue());
           return new VInt(((Long)obj).intValue());
       } else if (obj instanceof JSONArray) {
           JSONArray arr = (JSONArray) obj;
           if(arr.get(1) instanceof  String) {
               System.out.println("Oper : " + (String)arr.get(1));
               return new VOperator(parse(arr.get(0)), parse(arr.get(2)), (String) arr.get(1));
           } else {
               System.out.println("VDecl : ");
               List<Decl> declList = new ArrayList();
               for(Object o : arr.subList(0, arr.size() - 1) ){
                  declList.add(parseDecl((JSONArray) o));
               }
               return new VDeclArray(declList, parse(arr.get(arr.size() -1 )));
           }
       } else {
           throw new IllegalStateException("JSON could not be parsed");
       }
    }

    private static Decl parseDecl(JSONArray arr) {
        return new Decl(new Var((String)arr.get(1)),parse(arr.get(3)));
    }
}
