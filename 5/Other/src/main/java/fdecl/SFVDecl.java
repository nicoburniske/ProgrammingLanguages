package fdecl;

import answer.Answer;
import fvexpr.SFVExpr;
import fvexpr.Var;

import java.util.HashMap;

public  class SFVDecl {
    public Var name;
    public SFVExpr rhs;

    public SFVDecl(Var name, SFVExpr rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    public  Answer interpret(HashMap<Var, Answer> acc) {
        return null;
    }

    public Object toJson() {
   return null;
    }
}

