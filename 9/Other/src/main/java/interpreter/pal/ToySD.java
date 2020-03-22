package interpreter.pal;

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
}
