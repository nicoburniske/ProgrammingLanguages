package fdecl;

import answer.Answer;
import fvexpr.Var;

import java.util.HashMap;

public abstract class FDecl<T> {
    public Var name;
    public T rhs;

    public  abstract Answer interpret(HashMap<Var, Answer> acc);
}

