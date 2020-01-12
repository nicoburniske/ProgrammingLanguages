public class TInteger implements T{
    int number;

    public TInteger(int number) {
        this.number = number;
    }

    public String toJSON() {
        return this.number + "";
    }
}