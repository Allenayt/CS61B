public interface Deque<T> {
    public void addFirst(T items);
    public void addLast(T items);
    default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
