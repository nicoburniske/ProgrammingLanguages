package utils;
// Will be overwritten with code produced by transpiler.
public class OutputtedCode {
    public static MyInteger run() {
        return new Cell<>(new Cell<>(new MyInteger(1))).retrieve().retrieve();

    }
}

