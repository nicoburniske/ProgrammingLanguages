package fvexpr;

import answer.Answer;
import answer.AnswerString;

import java.util.HashMap;

public class Var implements FVExpr {
    String myString;

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        if (acc.get(this) != null) {
            return acc.get(this);
        } else {
            return new AnswerString(String.format("\"variable %s undeclared\"", this.myString));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Var var = (Var) o;

        return myString != null ? myString.equals(var.myString) : var.myString == null;
    }

    @Override
    public int hashCode() {
        return myString != null ? myString.hashCode() : 0;
    }
}
