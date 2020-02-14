package tast;

import star_ast.StarAST;
import star_decl.StarDecl;

import java.util.List;

public class TASTDeclArray implements TAST {
    List<StarDecl> declList;
    StarAST rhs;

    public TASTDeclArray(List<StarDecl> declList, StarAST rhs) {
        this.declList = declList;
        this.rhs = rhs;
    }
}
