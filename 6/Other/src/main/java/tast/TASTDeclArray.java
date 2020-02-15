package tast;

import org.json.simple.JSONArray;
import tast.star_ast.StarAST;
import tast.star_decl.StarDecl;

import java.util.List;

public class TASTDeclArray implements TAST {
    List<StarDecl> declList;
    StarAST rhs;

    public TASTDeclArray(List<StarDecl> declList, StarAST rhs) {
        this.declList = declList;
        this.rhs = rhs;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.addAll(declList);
        arr.add(rhs);
        return arr.toJSONString();
    }
}
