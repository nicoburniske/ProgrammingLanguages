package fdecl;

import answer.Answer;
import fvexpr.FVExpr;
import fvexpr.Var;

import java.util.HashMap;

public abstract class FDecl<T> {
    public Var name;
    public T rhs;

    public FDecl(Var name, T rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    public  abstract Answer interpret(HashMap<Var, Answer> acc);

    public abstract Object toJson();
}

