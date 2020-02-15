package tast;

public class TASTVar implements TAST{
    String variable;

    public TASTVar(String variable) {
        this.variable = variable;
    }

    @Override
    public String toJSONString() {
        return String.format("\"%s\"", variable);
    }
}
