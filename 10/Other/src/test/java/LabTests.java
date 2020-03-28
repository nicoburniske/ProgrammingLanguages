import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class LabTests {
    List<Integer> list1;
    IList<Integer> list2;

    @Before
    public void init() throws Exception {
        // Java ArrayList
        list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        // Fundies 2 IList
        list2 = new MtList<>();
        list2 = new ConsList<>(1, list2);
        list2 = new ConsList<>(2, list2);
        list2 = new ConsList<>(3, list2);
    }
    @Test
    public void testEmptyArrayList() {
        System.out.println("Java ArrayList -> " + list1.toString());
        System.out.println("Fundies 2 IList -> " + list2.toString());

//        assertEquals(3, list1.size());
//        this.emptyArrayList(list1);
//        assertEquals(3, list1.size());
//        this.emptyArrayList2(list1);
//        assertEquals(0, list1.size());

//        List<Integer> newList = new ArrayList<>(list1);
//        newList.add(4);
//        System.out.println(list1);
//        System.out.println(newList);
    }


    @Test
    public void testSum() {
        int sum = 0;

        for (int ii = 0; ii < list1.size(); ii++) {
            int currElement = list1.get(ii);
            sum += list1.get(ii);
        }
        assertEquals(6, sum);

        // reset sum and perform sum with for each loop
        sum = 0;

        for(Integer currElement : list1) {
           sum += currElement;
        }
        assertEquals(6, sum);
    }



    /**
     * Removes all entries from the list.
     *
     * listParam --> list1 --> [1,2,3]
     * listParam --> list1 --> []
     * by changing the reference of listParam we effectively break the link to list1
     * therefore the new "linking" looks as follows:
     * listParam --> []
     */
    public void emptyArrayList(List<Integer> listParam) {
        // changing the reference of the alias does not mutate the outer list1.
        listParam = new ArrayList<>();
    }

    /**
     * Removes all entries from the list.
     */
    public void emptyArrayList2(List<Integer> list) {
        list.removeAll(list);
    }
}

interface IList<T> {
    int length();
    IList<T> reverse();
    IList<T> reverseAcc(IList<T> reverseSoFar);
    IList<T> filter(Predicate<T> pred);
    <U> IList<U> map(Function<T,U> converter);
    <U> U fold(BiFunction<T,U,U> converter,U initial);
    String toString(); // this is just included for convenience
    String toStringAcc(String soFar);
}

class MtList<T> implements IList<T> {

    MtList() {}

    public String toString() {
        return "[]";
    }
    public String toStringAcc(String soFar) {
        return String.format("[%s]", soFar.substring(0, soFar.length() - 2));
    }
    public IList<T> filter(Predicate<T> pred) {
        return new MtList<T>();
    }

    public <U> IList<U> map(Function<T, U> converter) {
        return new MtList<U>();
    }

    public <U> U fold(BiFunction<T, U, U> converter, U initial) {
        return initial;
    }

    public int length() {
        return 0;
    }

    public IList<T> reverse() {
        return new MtList<T>();
    }

    public IList<T> reverseAcc(IList<T> reverseSoFar) {
        return reverseSoFar;
    }
}

class ConsList<T> implements IList<T> {
    T first;
    IList<T> rest;

    ConsList(T first,IList<T> rest) {
        this.first = first;
        this.rest = rest;
    }
    public String toString() {
        return this.toStringAcc("");
    }

    public String toStringAcc(String soFar) {
        return this.rest.toStringAcc(soFar + this.first + ", ");
    }

    public IList<T> filter(Predicate<T> pred) {
        if (pred.test(this.first)) {
            return new ConsList<T>(this.first,this.rest.filter(pred));
        }
        else {
            return this.rest.filter(pred);
        }
    }

    public <U> IList<U> map(Function<T, U> converter) {
        return new ConsList<U>(converter.apply(this.first),this.rest.map(converter));
    }

    public <U> U fold(BiFunction<T, U, U> converter, U initial) {
        return converter.apply(this.first, this.rest.fold(converter,initial));
    }



    public int length() {
        return 1+this.rest.length();
    }

    public IList<T> reverse() {
        return this.reverseAcc(new MtList<T>());
    }

    public IList<T> reverseAcc(IList<T> reverseSoFar) {
        return this.rest.reverseAcc(new ConsList<T>(this.first,reverseSoFar));
    }
}
