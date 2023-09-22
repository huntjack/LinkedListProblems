package common;

import java.util.Optional;

public class MyStack<T> {
    private SinglyLinkedList<T> linkedList = new SinglyLinkedList<>();
    public T pop() {
        return linkedList.removeFirstElement();
    }
    public void push(T item) {
        linkedList.addFirst(item);
    }
    public Optional<T> peek() {
        if(!linkedList.isEmpty()) {
            T value = linkedList
                    .getHead()
                    .orElseThrow()
                    .getValue();
            return Optional.of(value);
        } else {
            return Optional.empty();
        }
    }
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
    public int size() {
        return linkedList.size();
    }
}
