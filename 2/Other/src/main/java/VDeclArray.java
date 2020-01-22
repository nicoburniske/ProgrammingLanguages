import java.util.*;

public class VDeclArray implements VExpr {
    List<Decl> declarations;
    VExpr scope;

    public VDeclArray(List<Decl> declarations, VExpr scope) {
        this.declarations = declarations;
        this.scope = scope;
    }

    @Override
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth) {
        depth++;
        List<Decl> l = new ArrayList();
        for (int ii = 0; ii < declarations.size(); ii++) {
            l.add(declarations.get(ii).sd(acc, depth, ii));
            declarations.get(ii).updateAcc(acc, new AccumulatorType(depth, ii));
        }
        VExpr returnVal = new VDeclArray(l, scope.sd(acc, depth));
        for (Decl declaration : declarations) {
            declaration.removeFromAcc(acc);
        }
        return returnVal;
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Decl declaration : declarations) {
            sb.append(declaration.toJSON());
            sb.append(",");
        }
        sb.append(scope.toJson());
        sb.append("]");
        return sb.toString();
    }

    //(value-of-subst (subst (value-of-subst a1) x a2))
    @Override
    public int evaluate() {
        List<Decl> l = new ArrayList<Decl>();
        HashMap<String, Stack<VExpr>> vars = new HashMap<String, Stack<VExpr>>();
        for (int ii = 0; ii < declarations.size(); ii ++) {
            Decl decl = declarations.get(ii);
            decl.substitute(vars).evaluate(vars);
            List<Decl> sub = declarations.subList(ii, declarations.size());
            for(Decl inside: sub) {
                inside.substitute(vars);
            }
        }
        return scope.substitute(vars).evaluate();
    }

    public VExpr substitute(Map<String, Stack<VExpr>> variables) {
        List<Decl> l = new ArrayList<Decl>();
        for (Decl decl : declarations) {
            Decl tmp = decl.substitute(variables);
            l.add(tmp);
            if(variables.get(decl.v.s) == null){
                variables.put(decl.v.s, new Stack<VExpr>());
            }
            variables.get(decl.v.s).push(tmp.expr);
        }
        VDeclArray tmp =  new VDeclArray(l,scope.substitute(variables));
        for (Decl decl : declarations) {
            variables.get(decl.v.s).pop();
        }
        return tmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VDeclArray that = (VDeclArray) o;

        if (!declarations.equals(that.declarations)) return false;
        return scope.equals(that.scope);
    }

    @Override
    public int hashCode() {
        int result = declarations.hashCode();
        result = 31 * result + scope.hashCode();
        return result;
    }
}
