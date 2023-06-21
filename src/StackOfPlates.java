import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class StackOfPlates<T> {
    int stackSize;
    private SinglyLinkedList<MyStack<T>> listOfStacks = new SinglyLinkedList<>();
    public void push(T value) {
        if(!isCurrentStackFull()) {
            MyStack<T> myStack = peekCurrentStack().orElseThrow();
            myStack.push(value);
        } else {
            MyStack<T> myStack = new MyStack<>();
            myStack.push(value);
            listOfStacks.addFirst(myStack);
        }
    }
    private boolean isCurrentStackFull() {
        MyStack<T> current = peekCurrentStack().orElseThrow();
        return current.size() >= stackSize;
    }
    private Optional<MyStack<T>> peekCurrentStack() {
        if(!listOfStacks.isEmpty()) {
            MyStack<T> value = listOfStacks
                    .getHead()
                    .orElseThrow()
                    .getValue();
            return Optional.of(value);
        } else {
            return Optional.empty();
        }
    }
    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        MyStack<T> current = peekCurrentStack().orElseThrow();
        T value = current.pop();
        if(current.isEmpty()) {
            listOfStacks.removeFirstElement();
        }
        return value;
    }
    public boolean isEmpty() {
        return listOfStacks.isEmpty();
    }
    public Optional<T> peek() {
        MyStack<T> current = peekCurrentStack().orElseThrow();
        return current.peek();
    }
    public T popAt(int index) {
        int lastElement = listOfStacks.size() - 1;
        if(listOfStacks.isEmpty()) {
            throw new EmptyStackException();
        } else if(index > lastElement) {
            throw new NoSuchElementException();
        } else {
            int actualIndex = lastElement - index;
            MyStack<T> current =
                    listOfStacks
                            .get(actualIndex)
                            .orElseThrow();
            return current.pop();
        }
    }
    public StackOfPlates(int stackSize) {
        this.stackSize = stackSize;
        MyStack<T> myStack = new MyStack<>();
        listOfStacks.addFirst(myStack);
    }
    public static void main(String[] args) {
        StackOfPlates<Integer> stackOfPlates = new StackOfPlates<>(3);
        for(int i = 0; i < 9; i++) {
            stackOfPlates.push(i);
        }
        String empty = new String("StackOfPlates Empty: ");
        StringBuilder emptyStringBuilder = new StringBuilder(empty);
        System.out.println(
                emptyStringBuilder.append(
                        stackOfPlates.isEmpty()));
        Integer peekInt =
                stackOfPlates
                        .peek()
                        .orElseThrow();
        StringBuilder peekStringBuilder = new StringBuilder("Peek Integer: ");
        System.out.println(
                peekStringBuilder.append(peekInt));
        String poppedInteger = new String("Popped Integer: ");
        stackOfPlates.popAt(1);
        for(int i = 0; i < 8; i++) {
            StringBuilder stringBuilder = new StringBuilder(poppedInteger);
            Integer integer = stackOfPlates.pop();
            System.out.println(
                    stringBuilder.append(integer));
        }
        StringBuilder secondEmptyStringBuilder = new StringBuilder(empty);
        System.out.println(
                secondEmptyStringBuilder.append(
                        stackOfPlates.isEmpty()));
    }
}
