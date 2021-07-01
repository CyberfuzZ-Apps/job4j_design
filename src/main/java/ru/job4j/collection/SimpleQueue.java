package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;

    /**
     * Метод возвращает первое значение и удаляет его из коллекции.
     * @return
     */
    public T poll() {
        if (sizeOut == 0) {
            while (sizeIn != 0) {
                out.push(in.pop());
                sizeIn--;
                sizeOut++;
            }
        }
        sizeOut--;
        return out.pop();
    }

    /**
     * Метод помещает значение в конец коллекции.
     * @param value
     */
    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}