package fdecl;

import answer.Answer;
import fvexpr.Func;
import fvexpr.Var;

import java.util.HashMap;

public class FDeclFVExpr extends FDecl<Func> {
    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return rhs.interpret(acc);
    }
}
