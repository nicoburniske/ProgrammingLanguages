import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    private VExpr parse(Object obj) {
       if (obj instanceof String) {
           System.out.println("String : " + (String)obj);
           return new Var((String)obj);
       } else if (obj instanceof Integer) {
           System.out.println("Integer : " + (Integer)obj);
           return new VInt((Integer) obj);
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

    private Decl parseDecl(JSONArray arr) {
        return new Decl(new Var((String)arr.get(1)),parse(arr.get(3)));
    }
}
