package tpal;

import env.IEnv;
import env.Tuple;
import tpal.decl.TPALDecl;
import type.Type;

import java.util.List;
import java.util.Objects;

public class TPALDeclArray implements TPAL {
    List<TPALDecl> declList;
    TPAL scope;

    public TPALDeclArray(List<TPALDecl> declList, TPAL scope) {
        this.declList = declList;
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALDeclArray that = (TPALDeclArray) o;
        return declList.equals(that.declList) &&
                scope.equals(that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declList, scope);
    }

    @Override
    public String toString() {
        return "TPALDeclArray{" +
                "declList=" + declList +
                ", scope=" + scope +
                '}';
    }

    @Override
    public Tuple typeCheck(IEnv<TPALVar, Type> env) {
        for(TPALDecl decl : declList){
            env = decl.typeCheck(env).getRight();
        }
        return scope.typeCheck(env);
    }
}
