import java.util.Optional;

public class MyStack<T> {
    private DoublyLinkedList<T> linkedList = new DoublyLinkedList<>();
    public Optional<T> pop() {
        return linkedList.pop();
    }
    public void push(T item) {
        linkedList.addLast(item);
    }
    public Optional<T> peek() {
        if(!linkedList.isEmpty()) {
            T value = linkedList
                    .getTail()
                    .getValue();
            return Optional.of(value);
        }
        return Optional.empty();
    }
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
