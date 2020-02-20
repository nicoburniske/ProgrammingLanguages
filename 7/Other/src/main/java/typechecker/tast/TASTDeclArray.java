package typechecker.tast;

import org.json.simple.JSONArray;
import typechecker.tast.star_ast.StarAST;
import typechecker.tast.star_decl.StarDecl;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TASTDeclArray declArray = (TASTDeclArray) o;
        return declList.equals(declArray.declList) &&
                rhs.equals(declArray.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declList, rhs);
    }
}
