package typechecker.type;

import java.util.Objects;

/**
 * Represents a reference of a Type
 */
public class TypeRef implements Type {
    Type reference;

    public TypeRef(Type reference) {
        this.reference = reference;
    }

    @Override
    public String toJSONString() {
        return String.format("ref(%s)", reference.toJSONString());
    }

    public Type getType() {
        return reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeRef typeRef = (TypeRef) o;
        return reference.equals(typeRef.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
