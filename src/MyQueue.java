import java.util.Optional;

public class MyQueue<T> {
    private DoublyLinkedList<T> linkedList = new DoublyLinkedList<>();
    public void enqueue(T item) {
        linkedList.addLast(item);
    }
    public T dequeue() {
        return linkedList.removeFirstElement();
    }
    public Optional<T> peek() {
        if(!linkedList.isEmpty()) {
            T value = linkedList
                    .getHead()
                    .orElseThrow()
                    .getValue();
            return Optional.of(value);
        }
        return Optional.empty();
    }
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
