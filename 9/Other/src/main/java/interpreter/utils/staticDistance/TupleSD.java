package interpreter.utils.staticDistance;

import common.TupleGeneric;

public class TupleSD extends TupleGeneric<Integer, Integer> {
    public TupleSD(Integer left, Integer right) {
        super(left, right);
    }
    public Integer getDepth(int currDepth) {
       return currDepth - this.getLeft();
    }

    public Integer getPosition() {
        return this.getRight();
    }
}
