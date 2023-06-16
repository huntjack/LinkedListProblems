import java.util.NoSuchElementException;
import java.util.Optional;

public class LoopDetection {
    public static void main(String[] args) {
        Node<Integer> head = Intersection.createList(9);
        Node<Integer> tail = Intersection.get(head, 8)
                .orElseThrow(NoSuchElementException::new);
        Node<Integer> target = Intersection.get(head, 3)
                .orElseThrow(NoSuchElementException::new);
        tail.setNext(target);
        Optional<Node<Integer>> optionalIntersection = findIntersection(head);
        Node<Integer> intersection = optionalIntersection
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Intersection: " + intersection.getValue());
        Optional<Node<Integer>> optionalStartOfLoop = findStartOfLoop(head);
        Node<Integer> startOfLoop = optionalStartOfLoop.orElseThrow(NoSuchElementException::new);
        System.out.println("Start of Loop: " + startOfLoop.getValue());
    }
    public static <T> Optional<Node<T>> findIntersection(Node<T> node) {
        Node<T> slow = node;
        Node<T> fast = node;
        while(fast != null || fast.getNext() == null) {
            slow = slow.getNext();
            fast = fast
                    .getNext()
                    .getNext();
            if(fast == slow) {
                return Optional.of(fast);
            }
        }
        return Optional.empty();
    }
    public static <T> Optional<Node<T>> findStartOfLoop(Node<T> slow) {
        Optional<Node<T>> optionalCurrent = findIntersection(slow);
        if(optionalCurrent.isEmpty()) {
            return optionalCurrent;
        }
        Node<T> fast = optionalCurrent.get();
        while(slow != fast) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return Optional.of(fast);
    }
}
