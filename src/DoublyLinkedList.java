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
    public void remove(int element) {
        if(head == null) {
            return;
        } else if(element == 0) {
            removeFirstElement();
            return;
        }
        DoublyLinkedListNode<T> current = head;
        for(int i = 0; i < element && current != null; i++) {
            int nextElement = i + 1;
            if(nextElement == element) {
                removeNextElement(current);
                return;
            }
            current = current.getNext();
        }
    }
    public Optional<T> removeFirstElement() {
        if(isEmpty()) {
            return Optional.empty();
        }
        if(!isEmpty() && tail == head) {
            T headValue = head.getValue();
            tail = null;
            head = null;
            size--;
            return Optional.of(headValue);
        }
        T headValue = head.getValue();
        DoublyLinkedListNode<T> next = head.getNext();
        next.setPrevious(null);
        head.setNext(null);
        head = next;
        size--;
        return Optional.of(headValue);
    }
    public void removeNextElement(DoublyLinkedListNode<T> current) {
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
        }
    }
    public Optional<T> pop() {
        if(isEmpty()) {
            return Optional.empty();
        } else if(tail == head) {
            T value = tail.getValue();
            removeFirstElement();
            return Optional.of(value);
        } else  {
            T value = tail.getValue();
            removeNextElement(tail.getPrevious());
            return Optional.of(value);
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
    public DoublyLinkedListNode<T> getHead() {
        return head;
    }
    public DoublyLinkedListNode<T> getTail() {
        return tail;
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
        DoublyLinkedListNode<T> current = this.getHead();
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
