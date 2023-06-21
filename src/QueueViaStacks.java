import java.util.Optional;

public class QueueViaStacks<T> {
    private MyStack<T> reserve = new MyStack<>();
    private MyStack<T> active = new MyStack<>();
    public void enqueue(T value) {
        reserve.push(value);
    }
    public T dequeue() {
        if(active.isEmpty()) {
            transferToActive();
        }
        return active.pop();
    }
    private void transferToActive() {
        while(!reserve.isEmpty()) {
            active.push(reserve.pop());
        }
    }
    public Optional<T> peek() {
        if(active.isEmpty()) {
            transferToActive();
        }
        return active.peek();
    }
    public boolean isEmpty() {
        return active.isEmpty() && reserve.isEmpty();
    }
    public static void main(String[] args) {
        QueueViaStacks<Integer> queue = new QueueViaStacks<>();
        for(int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        int peekValue = queue
                .peek()
                .orElseThrow();
        System.out.println("peek: " + peekValue);
        for(int i = 5; i < 10; i++) {
            queue.enqueue(i);
        }
        StringBuilder stringBuilder = new StringBuilder("Stack: ");
        while(!queue.isEmpty()) {
            stringBuilder.append(queue.dequeue());
        }
        System.out.println(stringBuilder);
    }
}
