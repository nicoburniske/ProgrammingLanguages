package interpreter.value;

import org.json.simple.JSONArray;

import java.util.Objects;

/**
 * Represents a Value containing a Cell
 */
public class ValueCell implements IValue {
    public Cell getCell() {
        return cell;
    }

    private Cell cell;

    public ValueCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("Cell");
        arr.add(this.cell.location);
        return arr.toJSONString();
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
