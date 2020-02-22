package interpreter.value;

import org.json.simple.JSONAware;

import java.util.Objects;

public class ValueCell implements IValue{
    private Cell cell;

    public ValueCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toJSONString() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueCell valueCell = (ValueCell) o;
        return cell.equals(valueCell.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell);
    }
}
