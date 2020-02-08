package fvexpr;

import answer.Answer;
import fdecl.SFVDecl;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.util.List;

public class DeclArray implements SFVExpr {
    List<SFVDecl> decls;
    SFVExpr scope;

    public DeclArray(List<SFVDecl> fDecls, SFVExpr scope) {
        this.decls = fDecls;
        this.scope = scope;
    }


    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        for (SFVDecl d : decls) {
            StoreUtils.insertIntoStore(env, store, d);
        }
        Answer ans = scope.interpret(env, store);
        for (SFVDecl d : decls) {
            env.pop();
        }
        return ans;
    }

    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.addAll(decls);
        ret.add(scope);
        return ret.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeclArray declArray = (DeclArray) o;

        if (!decls.equals(declArray.decls)) return false;
        return scope.equals(declArray.scope);
    }

    @Override
    public int hashCode() {
        int result = decls.hashCode();
        result = 31 * result + scope.hashCode();
        return result;
    }
}
