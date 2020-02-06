package fvexpr;

import answer.Answer;
import fdecl.SFVDecl;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;

public class DeclArray  implements SFVExpr {
    List<SFVDecl> decls;
    SFVExpr scope;

    public DeclArray(List<SFVDecl> fDecls, SFVExpr scope) {
        this.decls = fDecls;
        this.scope = scope;
    }


    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        HashMap<Var, Answer> envNew = new HashMap<Var, Answer>(acc);
        for(SFVDecl d : decls) {
            envNew.put(d.name, d.interpret(envNew));
        }
        return scope.interpret(envNew);
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        for (SFVDecl decl : decls) {
            ret.add(decl.toJson());
        }
        ret.add(scope.toJson());
        return ret.toJSONString();
    }
}
