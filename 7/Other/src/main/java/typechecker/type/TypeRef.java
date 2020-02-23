package typechecker.type;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeRef typeRef = (TypeRef) o;
        return refernece.equals(typeRef.refernece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refernece);
    }
}
