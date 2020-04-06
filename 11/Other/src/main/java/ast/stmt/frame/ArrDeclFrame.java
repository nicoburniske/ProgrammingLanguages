package ast.stmt.frame;

import ast.Var;
import ast.expression.Expression;
import utils.env.Environment;

public class ArrDeclFrame extends DeclFrame {
    private int index, length;
    private Expression after;

    public ArrDeclFrame(Var var, Environment env, int index, int length, Expression after) {
        super(var, env);
        this.index = index;
        this.length = length;
        this.after = after;
    }

    public int getIndex() {
        return index;
    }

    public Expression getAfter() {
        return after;
    }
    public int getLength() {
        return length;
    }
}

