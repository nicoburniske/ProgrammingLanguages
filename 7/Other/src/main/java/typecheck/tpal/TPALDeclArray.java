package typecheck.tpal;

import typecheck.env.IEnv;
import typecheck.env.Tuple;
import typecheck.env.TupleGeneric;
import typecheck.tast.TASTDeclArray;
import typecheck.tast.star_ast.StarAST;
import typecheck.tast.star_decl.StarDecl;
import typecheck.tpal.decl.TPALDecl;
import typecheck.type.Type;

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
        IEnv<TPALVar, Type> envPlus = env;
        List<StarDecl> decls = new ArrayList<>();
        //Add everyone to the enviroment
        for(TPALDecl decl : declList) {
            envPlus = decl.getVar().typeCheck(envPlus).getRight();
        }
        //check all of their types
        for (TPALDecl decl : declList) {
            TupleGeneric<StarDecl, IEnv<TPALVar, Type>> declTuple = decl.typeCheck(envPlus);
            //envPlus = declTuple.getRight();
            decls.add(declTuple.getLeft());
        }
        Tuple rhsTuple = scope.typeCheck(envPlus);
        return new Tuple(
                new StarAST(
                        new TASTDeclArray(decls, rhsTuple.getLeft()),
                        rhsTuple.getLeft().getType()),
                env);
    }
}
