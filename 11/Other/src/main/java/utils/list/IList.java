package utils.list;

public interface IList <T> {
    IList<T> add(T t);
    int length();
    boolean contains(T t);
    T getFromHead(int index);
}
