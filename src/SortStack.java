import java.util.Random;

public class SortStack {
    private MyStack<Integer> unsorted;
    private MyStack<Integer> sorted;
    public SortStack(MyStack<Integer> unsorted) {
        this.unsorted = unsorted;
        this.sorted = new MyStack<>();
    }
    public MyStack<Integer> sort() {
        while(!unsorted.isEmpty()) {
            pushMinOntoSortedStack();
        }
        while(!sorted.isEmpty()) {
            Integer current = sorted.pop();
            unsorted.push(current);
        }
        return unsorted;
    }
    private void pushMinOntoSortedStack() {
        int unsortedSize = unsorted.size();
        Integer min = unsorted
                .peek()
                .orElseThrow();
        while(!unsorted.isEmpty()) {
            Integer current = unsorted.pop();
            if(current.intValue() < min.intValue()) {
                min = current;
            }
            sorted.push(current);
        }
        for(int i = 0; i < unsortedSize; i++) {
            Integer current = sorted.pop();
            if(current != min) {
                unsorted.push(current);
            }
        }
        sorted.push(min);
    }
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            myStack.push(random.nextInt(100));
        }
        SortStack stack = new SortStack(myStack);
        myStack = stack.sort();
        String element = new String("Element: ");
        while(!myStack.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder(element);
            Integer currentInt = myStack.pop();
            stringBuilder.append(currentInt);
            System.out.println(stringBuilder);
        }
    }
}
