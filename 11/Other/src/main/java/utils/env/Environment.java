package utils.env;

import ast.Var;
import utils.table.LookupTable;
import utils.table.LookupTableEnd;
import value.Location;

import java.util.Objects;

public class Environment {
    LookupTable<Var, Location> table;

    public Environment() {
        this.table = new LookupTableEnd<>();
    }
    public Environment(LookupTable<Var, Location> table) {
        this.table = table;
    }

    public Environment put(Var key, Integer value) {
        return new Environment(table.put(key, new Location(value)));
    }

    public Location get(Var key) {
        return table.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Environment that = (Environment) o;
        return table.equals(that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table);
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
