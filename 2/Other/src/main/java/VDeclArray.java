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
    public VExpr sd(Map<String,Integer> accDepth, Map<String,Integer> accDecl) {
        return null;
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
