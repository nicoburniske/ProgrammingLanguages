package tpal;

import env.IEnv;
import env.Tuple;
import env.TupleGeneric;
import tast.TASTDeclArray;
import tast.star_ast.StarAST;
import tast.star_decl.StarDecl;
import tpal.decl.TPALDecl;
import type.Type;

import java.util.ArrayList;
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
        List<StarDecl> decls = new ArrayList<>();
        for (TPALDecl decl : declList) {
            TupleGeneric<StarDecl, IEnv<TPALVar, Type>> declTuple = decl.typeCheck(env);
            env = declTuple.getRight();
            decls.add(declTuple.getLeft());
        }
        Tuple rhsTuple = scope.typeCheck(env);
        return new Tuple(
                new StarAST(
                        new TASTDeclArray(decls, rhsTuple.getLeft()),
                        rhsTuple.getLeft().getType()),
                env);
    }
}
