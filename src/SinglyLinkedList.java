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
    public void remove(int element) {
        if(head == null) {
            return;
        } else if(element == 0) {
            removeFirstElement();
            return;
        }
        Node<T> current = head;
        for(int i = 0; i < element && current != null; i++) {
            int nextElement = i + 1;
            if(nextElement == element) {
                removeNextElement(current);
                return;
            }
            current = current.getNext();
        }
    }
    public void removeNextElement(Node<T> current) {
        Node<T> next = current.getNext();
        if(next != null) {
            Node<T> nodeAfterNext = next.getNext();
            current.setNext(nodeAfterNext);
            next.setNext(null);
            size--;
        }
    }
    public void removeFirstElement() {
        if(!isEmpty()) {
            Node<T> next = head.getNext();
            head.setNext(null);
            head = next;
            size--;
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
        return size < 1 ? true : false;
    }
    public int size() {
        return size;
    }
    public Node<T> getHead() {
        return head;
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
        Node<T> current = this.getHead();
        while(current != null) {
            linkedListCopy.addLast(current.getValue());
            current = current.getNext();
        }
        return linkedListCopy;
    }
}
