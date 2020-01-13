import java.util.List;

public class TArray implements T {
    List<T> array;

    public TArray(List<T> array) {
        this.array = array;
    }

    public String toJSON() {
        StringBuilder result = new StringBuilder();
        for (int ii = 0; ii < this.array.size(); ii++) {
            T t = this.array.get(ii);
            result.append(t.toJSON());
            if (ii + 1 != this.array.size()) {
                result.append(",");
            }
        }
        return "[" + result.toString() + "]";
    }
}
