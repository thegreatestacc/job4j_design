package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Возвращает первое значение и удаляет его из коллекции
     * @return
     */
    public T poll() {
        return out.pop();
    }

    /**
     * Помещает значение в конец
     * @param value
     */
    public void push(T value) {
        in.push(value);
    }
}
