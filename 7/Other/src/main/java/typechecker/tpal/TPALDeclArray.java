package typechecker.tpal;

import common.LookupTable;
import typechecker.env.Tuple;
import common.TupleGeneric;
import typechecker.tast.TASTDeclArray;
import typechecker.tast.star_ast.StarAST;
import typechecker.tast.star_decl.StarDecl;
import typechecker.tpal.decl.TPALDecl;
import typechecker.type.Type;

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
    public Tuple typeCheck(LookupTable<TPALVar, Type> env) {
        LookupTable<TPALVar, Type> envPlus = env;
        List<StarDecl> decls = new ArrayList<>();
        //Add everyone to the enviroment
        for(TPALDecl decl : declList) {
            envPlus = decl.getVar().typeCheck(envPlus).getRight();
        }
        //check all of their types
        for (TPALDecl decl : declList) {
            TupleGeneric<StarDecl, LookupTable<TPALVar, Type>> declTuple = decl.typeCheck(envPlus);
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
