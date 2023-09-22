package stacks_and_queues.stack_min;

public class StackMinNode {
    private int value;
    private StackMinNode next;;
    private StackMinNode previousMin;

    public StackMinNode getNext() {
        return next;
    }
    public void setNext(StackMinNode next) {
        this.next = next;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public StackMinNode(int value) {
        this.value = value;
    }
    public StackMinNode getPreviousMin() {return previousMin;}

    public void setPreviousMin(StackMinNode previousMin) {
        this.previousMin = previousMin;
    }
}
