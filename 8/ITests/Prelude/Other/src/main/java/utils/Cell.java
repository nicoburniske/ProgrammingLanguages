package utils;

// Cell class from assignment description
class Cell<T> {

    private T c;

    Cell(T c) { this.c = c; }

    T retrieve() { return c; }

    T assign(T nu) { T tmp = c; this.c = nu; return tmp; }

}