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

    @Override
    public int evaluate(Map<String, Stack<Integer>> acc) {
        for (Decl decl : declarations) {
            decl.updateAcc(acc, decl.evaluate(acc));
        }
        int returnVal = scope.evaluate(acc);
        for (Decl declaration : declarations) {
            declaration.removeFromAcc(acc);
        }
        return returnVal;
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
