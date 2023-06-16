public class PartialResult {
    private SinglyLinkedList<Integer> result;
    private int carryDigit = 0;
    public PartialResult() {
        this.result = new SinglyLinkedList<>();
    }
    public void addFirst(int data) {
        result.addFirst(data);
    }
    public SinglyLinkedList<Integer> getResult() {
        return result;
    }
    public int getCarryDigit() {
        return carryDigit;
    }
    public void setCarryDigit(int carryDigit) {
        this.carryDigit = carryDigit;
    }
}
