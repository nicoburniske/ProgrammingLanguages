import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VDeclArray implements VExpr {
    List<Decl> declarations;
    VExpr scope;

    public VDeclArray(List<Decl> declarations, VExpr scope) {
        this.declarations = declarations;
        this.scope = scope;
    }

    @Override
    public VExpr sd(Map<String, AccumulatorType> acc, int depth) {
        depth ++;
        List<Decl> l = new ArrayList();
        for(int ii = 0; ii < declarations.size(); ii ++ ) {
            l.add(declarations.get(ii).sd(acc, depth, ii));
            acc = declarations.get(ii).updateAcc(acc, depth, ii);
        }
        return new VDeclArray(l, scope.sd(acc,depth));
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
}
