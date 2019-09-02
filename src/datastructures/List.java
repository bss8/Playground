package datastructures;

interface List<T> {
    void add(T item);
    void insert(T item);
    T remove();
    T removeAt(int position);
    public T get(int position);
    int find(T item);
    int size();
}
