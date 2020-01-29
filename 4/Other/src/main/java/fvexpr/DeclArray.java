package fvexpr;

import answer.Answer;
import fdecl.FDecl;

import java.util.HashMap;
import java.util.List;

public class DeclArray  implements FVExpr{
    List<FDecl> decls;
    FVExpr scope;


    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        HashMap<Var, Answer> envNew = new HashMap<Var, Answer>(acc);
        for(FDecl d : decls) {
            envNew.put(d.name, d.interpret(envNew));
        }
        return scope.interpret(envNew);
    }
}
