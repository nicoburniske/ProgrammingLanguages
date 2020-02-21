package interpreter.value;

import org.json.simple.JSONAware;

public class ValueCell implements IValue{
    private Cell cell;

    public ValueCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toJSONString() {
        return null;
    }
}
