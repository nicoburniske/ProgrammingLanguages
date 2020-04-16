package utils.list;

public class IMtList<T> implements IList<T> {

    @Override
    public IList<T> add(T t) {
        return new IConsList<>(t, this);
    }
    @Override
    public int length() {
        return 0;
    }

    @Override
    public boolean contains(T t) {
        return false;
    }

    @Override
    public T getFromHead(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return "MT";
    }

}
