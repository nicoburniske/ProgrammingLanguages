package interpreter.value;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.util.Objects;

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
