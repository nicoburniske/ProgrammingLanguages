import utils.OutputtedCode;

public class main {
    public static void main(String[] args) {
        try {
            System.out.println(OutputtedCode.run());
        } catch (Exception e) {
            System.out.println(String.format("\"run-time error: %s\"", e.getMessage()));
        }
    }





}
