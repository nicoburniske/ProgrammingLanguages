package utils.list;

public class IConsList<T> implements IList<T> {
    T first;
    IList<T> rest;

    public IConsList(T first, IList<T> rest) {
        this.first = first;
        this.rest = rest;
    }

    @Override
    public IList<T> add(T t) {
        return new IConsList<T>(t, this);
    }

    @Override
    public int length() {
        return 1 + this.rest.length();
    }

    @Override
    public boolean contains(T t) {
        return this.first.equals(t) || this.rest.contains(t);
    }

    @Override
    public T getFromHead(int index) {
        return (index == 0) ? this.first : this.rest.getFromHead(index - 1);
    }

    @Override
    public String toString() {
        return this.first.toString() + "," + this.rest.toString();
    }
}
