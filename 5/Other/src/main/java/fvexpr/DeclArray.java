package fvexpr;

import answer.Answer;
import fdecl.SFVDecl;
import org.json.simple.JSONArray;
import store.Store;

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
    public Answer interpret(Store<Var, Answer> env) {
        HashMap<Var, Answer> envNew = new HashMap<Var, Answer>(env);
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
