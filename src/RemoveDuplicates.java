import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        linkedList.addLast(99);
        linkedList.addLast(99);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(5);
        linkedList.addLast(99);
        linkedList.addLast(5);
        SinglyLinkedList<Integer> linkedListCopy = linkedList.copy();
        linkedList = removeDuplicatesWithSet(linkedList);
        linkedList.print();
        System.out.println();
        linkedListCopy = removeDuplicatesInPlace(linkedListCopy);
        linkedListCopy.print();
    }
    public static <T> SinglyLinkedList<T> removeDuplicatesWithSet(SinglyLinkedList<T> linkedList) {
        if(linkedList == null) {
            throw new IllegalArgumentException();
        } else if(linkedList.isEmpty()) {
            return linkedList;
        }
        Node<T> current = linkedList.getHead();
        T initialValue = current.getValue();
        Set<T> set = new HashSet<>();
        set.add(initialValue);
        while(current != null) {
            processElement(linkedList, set, current);
            current = current.getNext();
        }
        return linkedList;
    }
    private static <T> void processElement(SinglyLinkedList<T> linkedList, Set<T> set, Node<T> current) {
        T nextValue = current
                .getNext()
                .getValue();
        if(!set.contains(nextValue)) {
            set.add(nextValue);
        } else {
            linkedList.removeNextElement(current);
            if (current.getNext() != null) {
                processElement(linkedList, set, current);
            }
        }
    }
    public static <T> SinglyLinkedList<T> removeDuplicatesInPlace(SinglyLinkedList<T> linkedList) {
        if(linkedList == null) {
            throw new IllegalArgumentException();
        } else if(linkedList.isEmpty()) {
            return linkedList;
        }
        Node<T> current = linkedList.getHead();
        while(current != null) {
            removeElementDuplicates(linkedList, current);
            current = current.getNext();
        }
        return linkedList;
    }
    private static <T> void removeElementDuplicates(SinglyLinkedList<T> linkedList, Node<T> runner) {
        T currentValue = runner.getValue();
        while(runner.getNext() != null) {
            T nextValue = runner
                    .getNext()
                    .getValue();
            if(nextValue.equals(currentValue)) {
                linkedList.removeNextElement(runner);
            } else {
                runner = runner.getNext();
            }
        }
    }
}
