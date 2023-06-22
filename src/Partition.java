import java.util.NoSuchElementException;
import java.util.Optional;

public class Partition {
    public static Optional<Node<Integer>> partition(SinglyLinkedList<Integer> linkedList, int x) {
        if(linkedList.isEmpty()) {
            return Optional.empty();
        }
        Node<Integer> lessThanX = null;
        Node<Integer> current =
                linkedList
                        .getHead()
                        .orElseThrow();
        int currentValue = current
                .getValue()
                .intValue();
        if(currentValue < x) {
            lessThanX = current;
        }
        while(current.getNext() != null) {
            Node<Integer> next = current
                    .getNext();
            int nextValue = next
                    .getValue()
                    .intValue();
            if(nextValue < x) {
                linkedList.removeNextElement(current);
                linkedList.addFirst(next);
                if(lessThanX == null) {
                    lessThanX =
                            linkedList
                                    .getHead()
                                    .orElseThrow();
                }
            } else {
                current = current.getNext();
            }
        }
        return Optional.ofNullable(
                lessThanX.getNext());
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        linkedList.addLast(3);
        linkedList.addLast(7);
        linkedList.addLast(6);
        linkedList.addLast(9);
        linkedList.addLast(2);
        linkedList.addLast(5);
        linkedList.addLast(1);
        Optional<Node<Integer>> optionalRightNode = partition(linkedList, 5);
        Node<Integer> firstElementOfRightPartition =
                optionalRightNode.orElseThrow(NoSuchElementException::new);
        System.out.println("firstElementOfRightPartition = " + firstElementOfRightPartition.getValue());
        linkedList.print();
    }
}
