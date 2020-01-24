package interpreter;

import java.util.ArrayList;
import java.util.List;

public class  StackList<T> {
    private List<T> l;
    private int size;
    private int current = 0;

    public StackList(int size) {
        this.l = new ArrayList<T>();
        this.size = size;
    }

    public T get(int i) {
        return l.get(i);
    }

    public T pop() {
        current --;
        return l.remove(current + 1);
    }

    public void push(T element) {
        if(l.size() >= this.size) {
            throw new IllegalStateException("List is at Capacity");
        }
        l.add(element);
        current ++;
    }

    public T peek() {
        return l.get(current);
    }


}
