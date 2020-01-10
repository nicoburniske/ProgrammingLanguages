import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SArray implements S {
    List<S> array;

    public SArray(List<S> array) {
        this.array = array;
    }

//    public SArray(List<JSONArray> array) {
//        this.array = new ArrayList<S>();
//        for(int ii = 0; ii < array.size(); ii ++ ) {
//            this.array.add(main.convertToS(array.get(ii)));
//        }
//    }

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


}
