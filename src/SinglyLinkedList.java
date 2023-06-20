import java.util.NoSuchElementException;
import java.util.Optional;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int size = 0;
    public void addFirst(T data) {
        if(head==null) {
            createHeadNode(data);
            size++;
            return;
        }
        Node<T> newNode = new Node<>(data);
        addFirst(newNode);
    }
    public void addFirst(Node<T> node) {
        node.setNext(head);
        head = node;
        size++;
    }
    private void createHeadNode(T data) {
        head = new Node<>(data);
    }
    public void addLast(T data) {
        if(head == null) {
            createHeadNode(data);
            size++;
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> current = this.head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        size++;
    }
    public T remove(int element) {
        if(head == null) {
            throw new EmptyListException();
        } else if(element == 0) {
            return removeFirstElement();
        }
        Node<T> current = head;
        for(int i = 0; i < element && current != null; i++) {
            int nextElement = i + 1;
            if(nextElement == element) {
                return removeNextElement(current);
            }
            current = current.getNext();
        }
        throw new NoSuchElementException();
    }
    public T removeNextElement(Node<T> current) {
        Node<T> next = current.getNext();
        if(next != null) {
            Node<T> nodeAfterNext = next.getNext();
            current.setNext(nodeAfterNext);
            next.setNext(null);
            size--;
            return next.getValue();
        } else {
            throw new NoSuchElementException();
        }
    }
    public T removeFirstElement() {
        if(!isEmpty()) {
            T value = head.getValue();
            Node<T> next = head.getNext();
            head.setNext(null);
            head = next;
            size--;
            return value;
        } else {
            throw new EmptyListException();
        }
    }
    public Optional<T> get(int element) {
        Node<T> current = head;
        int i = 0;
        while(current != null) {
            if(i++ == element) {
                T currentData = current.getValue();
                return Optional.of(currentData);
            }
            current = current.getNext();
        }
        return Optional.empty();
    }
    public Optional<Node<T>> getNode(int element) {
        Node<T> current = head;
        int i = 0;
        while(current != null) {
            if(i++ == element) {
                return Optional.of(current);
            }
            current = current.getNext();
        }
        return Optional.empty();
    }
    public Boolean isEmpty() {
        return size < 1;
    }
    public int size() {
        return size;
    }
    public Optional<Node<T>> getHead() {
        return Optional.ofNullable(head);
    }
    public void print() {
        Node<T> current = head;
        while(current != null) {
            System.out.println("value: " + current.getValue());
            current = current.getNext();
        }
    }
    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> linkedListCopy = new SinglyLinkedList<>();
        Node<T> current = getHead().orElseThrow();
        while(current != null) {
            linkedListCopy.addLast(current.getValue());
            current = current.getNext();
        }
        return linkedListCopy;
    }
}
