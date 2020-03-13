package utils;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Objects;

public class MyInteger  extends Number implements Comparable<MyInteger>, Comparator<MyInteger> {
    BigInteger num;
    public MyInteger(Integer i)
    {
        super();
        num = new BigInteger(i + "");
    }
    public MyInteger(BigInteger i)
    {
        super();
        num = i;
    }


    @Override
    public int intValue() {
        return num.intValue();
    }

    @Override
    public long longValue() {
        return num.longValue();
    }

    @Override
    public float floatValue() {
        return num.floatValue();
    }

    @Override
    public double doubleValue() {
        return num.doubleValue();
    }

    @Override
    public int compareTo(MyInteger o) {
        return this.num.compareTo(o.num);
    }

    @Override
    public int compare(MyInteger o1, MyInteger o2) {
        return o1.num.compareTo(o2.num);
    }

    public MyInteger pow(MyInteger other) {
        return new MyInteger(this.num.pow(other.num.intValue()));
    }

    public MyInteger add(MyInteger right) {
        return new MyInteger(this.num.add(right.num));
    }

    public MyInteger multiply(MyInteger right) {
        return new MyInteger(this.num.multiply(right.num));
    }

    @Override
    public String toString() {
        return num.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyInteger myInteger = (MyInteger) o;
        return num.equals(myInteger.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
