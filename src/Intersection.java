import java.util.NoSuchElementException;
import java.util.Optional;

public class Intersection {
    public static void main(String[] args) {
        Node<Integer> listOneHead = createList(10);
        Node<Integer> currentListOneNode = get(listOneHead, 8)
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Selected listOneNode = " + currentListOneNode.getValue());
        Node<Integer> listTwoHead = createList(7);
        Node<Integer> currentLastNodeOfListTwo = get(listTwoHead, 6)
                .orElseThrow(NoSuchElementException::new);
        currentLastNodeOfListTwo.setNext(currentListOneNode);
        Optional<Node<Integer>> optionalResult = intersect(listOneHead, listTwoHead);
        int result = optionalResult
                .orElseThrow(NoSuchElementException::new)
                .getValue();
        System.out.println("Intersection: " + result);

    }
    public static Node<Integer> createList(int size) {
        Node<Integer> head = new Node<>(0);
        Node<Integer> current = head;
        for(int i = 1; i < size; i++) {
            current.setNext(new Node<>(i));
            current = current.getNext();
        }
        return head;
    }
    public static <T> Optional<Node<T>> get(Node<T> node, int element) {
        for(int i = 0; i < element; i++) {
            if(node != null) {
                node = node.getNext();
            } else {
                return Optional.empty();
            }
        }
        return Optional.of(node);
    }
    public static <T> Optional<Node<T>> intersect(Node<T> listOneHead, Node<T> listTwoHead) {
        if(listOneHead == null || listTwoHead == null) {
            return Optional.empty();
        }
        boolean hasIntersection = hasIntersection(listOneHead, listTwoHead);
        int listOneSize = size(listOneHead);
        int listTwoSize = size(listTwoHead);
        if(hasIntersection && listOneSize > listTwoSize) {
            int difference = listOneSize - listTwoSize;
            Optional<Node<T>> optionalNode = findIntersection(listOneHead, listTwoHead, difference);
            return optionalNode;
        } else if (hasIntersection && listOneSize <= listTwoSize) {
            int difference = listTwoSize - listOneSize;
            Optional<Node<T>> optionalNode = findIntersection(listTwoHead, listOneHead, difference);
            return optionalNode;
        } else {
            return Optional.empty();
        }
    }
    private static <T> boolean hasIntersection(Node<T> listOneNode, Node<T> listTwoNode) {
        while(listOneNode.getNext() != null) {
            listOneNode = listOneNode.getNext();
        }
        while(listTwoNode.getNext() != null) {
            listTwoNode = listTwoNode.getNext();
        }
        return listOneNode == listTwoNode ? true : false;
    }
    public static <T> int size(Node<T> node) {
        int size = 0;
        while(node != null) {
            size++;
            node = node.getNext();
        }
        return size;
    }
    private static <T> Optional<Node<T>> findIntersection(Node<T> longerListNode, Node<T> shorterListNode, int difference) {
        for(int i = 0; i  < difference; i++) {
            longerListNode = longerListNode.getNext();
        }
        while(longerListNode != null) {
            if(longerListNode == shorterListNode) {
                return Optional.of(longerListNode);
            } else {
                longerListNode = longerListNode.getNext();
                shorterListNode = shorterListNode.getNext();
            }
        }
        return Optional.empty();
    }
}
