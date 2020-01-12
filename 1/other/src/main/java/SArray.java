import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SArray implements S {
    List<S> array;

    public SArray(List<S> array) {
        this.array = array;
    }

    public int count() {
        int sum = 0;
        for (S sCurrent : array) {
            sum += sCurrent.count();
        }
        return sum;
    }

    public S replace() {
        List<S> acc = new ArrayList<S>();
        for (S sCurrent : array) {
            acc.add(sCurrent.replace());
        }
        return new SArray(acc);
    }

    public T context() {
        List<T> acc = new ArrayList();
        for( S sCurrent: array){
            acc.add(sCurrent.context(1));
        }
        return new TArray(acc);
    }

    public T context(int depth) {
        depth ++;
        List<T> acc = new ArrayList();
        for( S sCurrent: array){
            acc.add(sCurrent.context(depth));
        }
        return new TArray(acc);
    }

    public String toJSON() {
        StringBuilder result = new StringBuilder();
        for (int ii = 0; ii < this.array.size(); ii ++ ) {
            S s = this.array.get(ii);
            result.append(s.toJSON());
            if(ii + 1 != this.array.size()) {
                result.append(",");
            }
        }
        return "[" + result.toString() + "]";
    }

}
