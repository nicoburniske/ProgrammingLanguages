public class SName implements S {
    String name;

    public SName(String name) {
        this.name= name;
    }
    @Override
    public int count() {
        return 1;
    }

    @Override
    public S replace() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder acc = new StringBuilder();

        for (char c : name.toCharArray()) {
            acc.append(alphabet.charAt(25 - alphabet.indexOf(c)));
        }

        return new SName(acc.toString());
    }

    @Override
    public T context() {
        return new TInteger(0);
    }

    public T context(int depth) {
        return new TInteger(depth);
    }


}
