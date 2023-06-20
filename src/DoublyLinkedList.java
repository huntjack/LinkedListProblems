import java.util.NoSuchElementException;
import java.util.Optional;

public class DoublyLinkedList<T> {
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size = 0;

    public void addFirst(T value) {
        if(head==null) {
            createNode(value);
            size++;
            return;
        }
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(value);
        newNode.setNext(head);
        head.setPrevious(newNode);
        head = newNode;
        size++;
    }
    public void addLast(T value) {
        if(head == null) {
            createNode(value);
            size++;
            return;
        }
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(value);
        tail.setNext(newNode);
        newNode.setPrevious(tail);
        tail = newNode;
        size++;
    }
    private void createNode(T value) {
        head = new DoublyLinkedListNode<>(value);
        tail = head;
    }
    public T remove(int element) {
        if(head == null) {
            throw new EmptyListException();
        } else if(element == 0) {
            return removeFirstElement();
        }
        DoublyLinkedListNode<T> current = head;
        for(int i = 0; i < element && current != null; i++) {
            int nextElement = i + 1;
            if(nextElement == element) {
                return removeNextElement(current);
            }
            current = current.getNext();
        }
        throw new NoSuchElementException();
    }
    public T removeFirstElement() {
        if(isEmpty()) {
            throw new EmptyListException();
        }
        if(!isEmpty() && tail == head) {
            T headValue = head.getValue();
            tail = null;
            head = null;
            size--;
            return headValue;
        }
        T headValue = head.getValue();
        DoublyLinkedListNode<T> next = head.getNext();
        next.setPrevious(null);
        head.setNext(null);
        head = next;
        size--;
        return headValue;
    }
    public T removeNextElement(DoublyLinkedListNode<T> current) {
        DoublyLinkedListNode<T> next = current.getNext();
        if(next != null) {
            DoublyLinkedListNode<T> nodeAfterNext = next.getNext();
            current.setNext(nodeAfterNext);
            if(nodeAfterNext == null) {
                tail = current;
            } else {
                nodeAfterNext.setPrevious(current);
            }
            next.setNext(null);
            next.setPrevious(null);
            size--;
            return next.getValue();
        }
        throw new NoSuchElementException();
    }
    public T pop() {
        if(isEmpty()) {
            throw new EmptyListException();
        } else if(tail == head) {
            T value = tail.getValue();
            removeFirstElement();
            return value;
        } else  {
            T value = tail.getValue();
            removeNextElement(tail.getPrevious());
            return value;
        }
    }
    public Optional<T> get(int element) {
        DoublyLinkedListNode<T> current = head;
        int i = 0;
        while(current != null) {
            if(i++ == element ) {
                T currentData = current.getValue();
                return Optional.of(currentData);
            }
            current = current.getNext();
        }
        return Optional.empty();
    }
    public Optional<DoublyLinkedListNode<T>> getNode(int element) {
        DoublyLinkedListNode<T> current = head;
        int i = 0;
        while(current != null) {
            if(i++ == element) {
                return Optional.of(current);
            }
            current = current.getNext();
        }
        return Optional.empty();
    }
    public Optional<DoublyLinkedListNode<T>> getHead() {
        return Optional.ofNullable(head);
    }
    public Optional<DoublyLinkedListNode<T>> getTail() {
        return Optional.ofNullable(tail);
    }
    public Boolean isEmpty() {
        return size < 1;
    }
    public void print() {
        DoublyLinkedListNode<T> current = head;
        while(current != null) {
            System.out.println("value: " + current.getValue());
            current = current.getNext();
        }
    }
    public DoublyLinkedList<T> copy() {
        DoublyLinkedList<T> linkedListCopy = new DoublyLinkedList<>();
        DoublyLinkedListNode<T> current = getHead().orElseThrow();
        while(current != null) {
            linkedListCopy.addLast(current.getValue());
            current = current.getNext();
        }
        return linkedListCopy;
    }
    public int size() {
        return size;
    }
}
