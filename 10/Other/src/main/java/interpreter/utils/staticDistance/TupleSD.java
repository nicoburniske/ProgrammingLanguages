package interpreter.utils.staticDistance;

import common.TupleGeneric;

public class TupleSD extends TupleGeneric<Integer, Integer> {
    public TupleSD(Integer left, Integer right) {
        super(left, right);
    }

    /**
     * gets the relative depth of a variable to its declaration
     *
     * @param currDepth the current depth of the transformation
     * @return the Depth
     */
    public Integer getDepth(int currDepth) {
        return currDepth - this.getLeft();
    }

    /**
     * gets the positions in the decl of the var
     *
     * @return the posistion
     */
    public Integer getPosition() {
        return this.getRight();
    }
}
