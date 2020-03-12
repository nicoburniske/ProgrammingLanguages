import utils.OutputtedCode;

import java.util.function.Function;
import java.util.function.Supplier;

import static utils.StandardLib.*;

public class main {
    public static void main(String[] args) {
        try {
            System.out.println(OutputtedCode.run());
        } catch (Error e) {
            System.out.println(String.format("\"runtime error: %s\"", e.getMessage()));
        }
    }





}
