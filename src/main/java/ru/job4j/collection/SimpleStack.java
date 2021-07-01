package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод должен возвращать значение и удалять его из коллекции.
     * @return
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод помещает значение в коллекцию.
     * @param value
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}
