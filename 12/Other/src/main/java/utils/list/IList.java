package utils.list;

/**
 * A Generic List class
 *
 * @param <T>
 */
public interface IList<T> {

    /**
     * Add an element to the list
     */
    IList<T> add(T t);

    /**
     * @return the length of the list
     */
    int length();

    /**
     * Does the List contain this element
     */
    boolean contains(T t);

    /**
     * get an element from the head of the list at the specified index
     */
    T getFromHead(int index);
}
