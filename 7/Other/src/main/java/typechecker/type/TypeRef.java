package typechecker.type;

public class TypeRef implements Type {
    Type refernece;

    public TypeRef(Type refernece) {
        this.refernece = refernece;
    }

    @Override
    public String toJSONString() {
        return String.format("ref(%s)", refernece.toJSONString());
    }

    public Type getType() {
        return refernece;
    }
}
