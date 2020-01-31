package fdecl;

import answer.Answer;
import fvexpr.Int;
import fvexpr.Var;

import java.util.HashMap;

public class FDeclInt extends FDecl<Int> {
    public FDeclInt(Var name, Int rhs) {
        super(name, rhs);
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return rhs.interpret(acc);
    }
}
