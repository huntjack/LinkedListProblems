import java.util.Optional;

public class StackMin {
    private StackMinNode head;
    private StackMinNode minNode;
    private int size = 0;
    public void push(int value) {
        if(head == null && minNode == null) {
            head = new StackMinNode(value);
            minNode = head;
        } else {
            StackMinNode newNode = new StackMinNode(value);
            if(newNode.getValue() < minNode.getValue()) {
                newNode.setPreviousMin(minNode);
                minNode = newNode;
            }
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }
    public Optional<Integer> peek() {
        if(!isEmpty()) {
            return Optional.of(head.getValue());
        }
        return Optional.empty();
    }
    public Integer pop() {
        if(!isEmpty()) {
            if(head == minNode) {
                minNode = head.getPreviousMin();
            }
            int value = head.getValue();
            StackMinNode next = head.getNext();
            head.setNext(null);
            head = next;
            size--;
            return value;
        } else {
            throw new EmptyListException();
        }
    }
    public Optional<Integer> min() {
        Integer min = minNode.getValue();
        return Optional.ofNullable(min);
    }
    public Boolean isEmpty() {
        return size < 1;
    }
    public static void main(String[] args) {
        StackMin stackMin = new StackMin();
        String emptyString = new String("stack empty: ");
        System.out.println(emptyString + stackMin.isEmpty());
        stackMin.push(10);
        stackMin.push(15);
        stackMin.push(8);
        stackMin.push(25);
        stackMin.push(6);
        StringBuilder sb2 = new StringBuilder(emptyString);
        System.out.println(emptyString + stackMin.isEmpty());
        System.out.println("peek " +
                stackMin
                        .peek()
                        .orElseThrow());
        System.out.println("size: " + stackMin.size);
        String minString = new String("current minimum: ");
        String popString = new String("popped value: ");
        int size = stackMin.size;
        for(int i = 0; i < size; i++) {
            StringBuilder minStringBuilder = new StringBuilder(minString);
            StringBuilder popStringBuilder = new StringBuilder(popString);
            Integer min =
                    stackMin
                            .min()
                            .orElseThrow();
            System.out.println(minStringBuilder.append(min));
            Integer poppedValue = stackMin.pop();
            System.out.println(popStringBuilder.append(poppedValue));
        }
    }
}
