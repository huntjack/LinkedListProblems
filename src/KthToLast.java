import java.util.NoSuchElementException;

public class KthToLast {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList();
        linkedList.addLast(98);
        linkedList.addLast(99);
        linkedList.addLast(31);
        linkedList.addLast(32);
        linkedList.addLast(56);
        linkedList.addLast(99);
        linkedList.addLast(55);
        System.out.println("Third from the last: " + returnKthToLastWithSize(linkedList, 3).getValue());
        System.out.println();
        System.out.println("Third from the last: " + returnKthToLastIteratively(linkedList, 3).getValue());
    }
    public static <T> Node<T> returnKthToLastWithSize(SinglyLinkedList<T> linkedList, int k) {
        if(linkedList == null) {
            throw new IllegalArgumentException();
        } else if(linkedList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if(k < 0) {
            throw new IllegalArgumentException();
        }
        int element = linkedList.size() - k;
        return linkedList
                .getNode(element)
                .orElseThrow(NoSuchElementException::new);
    }
    public static <T> Node<T> returnKthToLastIteratively(SinglyLinkedList<T> linkedList, int k) {
        if(linkedList == null) {
            throw new IllegalArgumentException();
        } else if(linkedList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if(k < 0) {
            throw new IllegalArgumentException();
        }
        Node<T> runner =
                linkedList
                        .getHead()
                        .orElseThrow();
        for(int i = 1; i < k; i++) {
            runner = runner.getNext();
            if(runner == null) {
                throw new NoSuchElementException();
            }
        }
        Node<T> current =
                linkedList
                        .getHead()
                        .orElseThrow();
        while(runner.getNext() != null) {
            runner = runner.getNext();
            current = current.getNext();
        }
        return current;
    }

}
