package fvexpr;

import answer.Answer;
import fdecl.SFVDecl;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;

import java.util.List;

public class DeclArray  implements SFVExpr {
    List<SFVDecl> decls;
    SFVExpr scope;

    public DeclArray(List<SFVDecl> fDecls, SFVExpr scope) {
        this.decls = fDecls;
        this.scope = scope;
    }


    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        for(SFVDecl d : decls) {
            Location l = new Location(store.getSize());
            env.put(d.name, l);
            store.put(l, d.interpret(env, store));
        }
        Answer ans = scope.interpret(env, store);
        for(SFVDecl d : decls) {
            env.pop();
        }
        return ans;
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
