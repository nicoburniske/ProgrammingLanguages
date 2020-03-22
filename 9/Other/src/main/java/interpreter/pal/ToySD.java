package interpreter.pal;

import org.json.simple.JSONArray;

import java.util.Objects;

public class ToySD extends ToyVar {
    private int depth;
    private int declPosition;

    /**
     * The first integer counts how far away the variable’s declaration is in terms of surrounding Decl JSON arrays,
     * the second one how far from the left the declaration is within this array.
     */
    public ToySD(int depth, int declPosition) {
        super("_");
        this.depth = depth;
        this.declPosition = declPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ToySD toySD = (ToySD) o;
        return depth == toySD.depth &&
                declPosition == toySD.declPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), depth, declPosition);
    }

    @Override
    public String toString() {
        return "ToySD{" +
                "depth=" + depth +
                ", declPosition=" + declPosition +
                '}';
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add(this.depth);
        arr.add(this.declPosition);
        return arr.toJSONString();
    }
}
