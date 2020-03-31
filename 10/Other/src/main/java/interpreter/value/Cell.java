package interpreter.value;

/**
 * Represents a cell that contains a location referencing a spot in the Store
 */
public class Cell {
    int location;

    public Cell(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }
}
