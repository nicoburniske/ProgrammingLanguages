public interface S {
    public int count();
    public S replace();
    public T context();
    public T context(int depth);
    public String toJSON();
}
