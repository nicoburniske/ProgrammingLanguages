package fvexpr;

import answer.Answer;
import fdecl.FDecl;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeclArray  implements FVExpr{
    List<FDecl> decls;
    FVExpr scope;

    public DeclArray(List<FDecl> fDecls, FVExpr scope) {
        this.decls = fDecls;
        this.scope = scope;
    }


    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        HashMap<Var, Answer> envNew = new HashMap<Var, Answer>(acc);
        for(FDecl d : decls) {
            envNew.put(d.name, d.interpret(envNew));
        }
        return scope.interpret(envNew);
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        for (FDecl decl : decls) {
            ret.add(decl.toJson());
        }
        ret.add(scope.toJson());
        return ret.toJSONString();
    }
}
