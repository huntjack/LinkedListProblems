import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ThreeInOne<T> {
    private T[] array;
    private int leftStack;
    private int rightStack;
    private int midLeft;
    private int midRight;

    public ThreeInOne(T[] array) {
        this.array = array;
        leftStack = -1;
        rightStack = array.length;
        midLeft = array.length / 2;
        midRight = midLeft;
    }
    public static void main(String[] args) {
        Integer[] array = new Integer[13];
        ThreeInOne<Integer> threeInOne = new ThreeInOne(array);
        threeInOne.pushLeft(0);
        threeInOne.pushLeft(1);
        threeInOne.pushLeft(2);
        threeInOne.pushLeft(3);
        threeInOne.pushLeft(4);
        threeInOne.pushRight(12);
        threeInOne.pushRight(11);
        threeInOne.pushRight(10);
        threeInOne.pushRight(9);
        threeInOne.pushRight(8);
        threeInOne.pushRight(7);
        threeInOne.pushLeft(5);
        threeInOne.pushMid(6);
        System.out.println("peek left: " + threeInOne.peekLeft());
        System.out.println("peek mid: " + threeInOne.peekMid());
        System.out.println("peek right: " + threeInOne.peekRight());
        System.out.println("pop left: " + threeInOne.popLeft());
        System.out.println("pop mid: " + threeInOne.popMid());
        System.out.println("pop right: " + threeInOne.popRight());
        threeInOne.print();
    }
    private T pushLeft(T value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        if(isLeftFull() && isRightFull()) {
            throw new FullStackException();
        } else if(isLeftFull() && !isRightFull()) {
            shiftMidRight(1);
            return pushLeft(value);
        } else {
            array[++leftStack] = value;
            balanceCapacity();
            return value;
        }
    }
    public boolean isLeftFull() {
        return leftStack >= midLeft - 1;
    }
    public boolean isRightFull() {
        return rightStack <= midRight + 1;
    }
    private void shiftMidLeft(int numberOfElements) {
        if(isLeftFull() || numberOfElements == 0 || numberOfElements > leftCapacity()) {
            return;
        }
        int j = midLeft - numberOfElements;
        for(int i = midLeft; i <= midRight; i++) {
            array[j++] = array[i];
            array[i] = null;
        }
        midRight = j - 1;
        midLeft = midLeft - numberOfElements;
    }
    private void balanceCapacity() {
        if(leftCapacity() >= rightCapacity() * 2 ) {
            int numberOfElements = leftCapacity() / 4;
            shiftMidLeft(numberOfElements);
        } else if(rightCapacity() >= leftCapacity() * 2) {
            int numberOfElements = rightCapacity() / 4;
            shiftMidRight(numberOfElements);
        }
    }
    private int leftCapacity() {
        if(leftStack < 0) {
            return midLeft;
        }
        return midLeft - leftStack - 1;
    }
    private int rightCapacity() {
        return rightStack - midRight - 1;
    }
    private void shiftMidRight(int numberOfElements) {
        if(isRightFull() || numberOfElements == 0 || numberOfElements > rightCapacity()) {
            return;
        }
        int j = midRight + numberOfElements;
        for(int i = midRight; i >= midLeft; i--) {
            array[j--] = array[i];
            array[i] = null;
        }
        midRight = midRight + numberOfElements;
        midLeft = j + 1;
    }
    public T pushMid(T value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        if(isMidFull()) {
            throw new FullStackException();
        } else if(isRightFull() && !isLeftFull() && !isMidEmpty()) {
            shiftMidLeft(1);
            return pushMid(value);
        } else {
            if(isMidEmpty()) {
                array[midRight] = value;
                return value;
            }
            array[++midRight] = value;
            balanceCapacity();
            return value;
        }
    }
    public boolean isMidFull() {
        return isLeftFull() && isRightFull() && array[midLeft] != null;
    }
    public boolean isMidEmpty() {
        return midLeft == midRight && array[midRight] == null;
    }
    public boolean isLeftEmpty() {
        return leftStack <= -1;
    }
    public boolean isRightEmpty() {
        return rightStack >= array.length;
    }
    public T pushRight(T value) {
         if(value == null) {
             throw new IllegalArgumentException();
         }
         if(isRightFull() && isLeftFull()) {
             throw new FullStackException();
         } else if(isRightFull() && !isLeftFull()) {
             shiftMidLeft(1);
             return pushRight(value);
         } else {
             array[--rightStack] = value;
             balanceCapacity();
             return value;
        }
    }
    public T peekLeft() {
        if(isLeftEmpty()) {
            throw new EmptyStackException();
        }
        return array[leftStack];
    }
    public T peekMid() {
        if(isMidEmpty()) {
            throw new EmptyStackException();
        }
        return array[midRight];
    }
    public T peekRight() {
        if(isRightEmpty()) {
            throw new EmptyStackException();
        }
        return array[rightStack];
    }
    public T popLeft() {
        if(isLeftEmpty()) {
            throw new EmptyStackException();
        }
        T value = peekLeft();
        array[leftStack--] = null;
        return value;
    }
    public T popMid() {
        if(isMidEmpty()) {
            throw new EmptyStackException();
        }
        T value = peekMid();
        if(midRight == midLeft) {
            array[midRight] = null;
        } else {
            array[midRight--] = null;
        }
        return value;
    }
    public T popRight() {
        if(isRightEmpty()) {
            throw new EmptyStackException();
        }
        T value = peekRight();
        array[rightStack++] = null;
        return value;
    }
    public void print() {
        String first = new String("Element ");
        String second = new String(" = ");
        for(int i = 0; i < array.length; i++) {
            StringBuilder stringBuilder = new StringBuilder(first);
            stringBuilder.append(i);
            stringBuilder.append(second);
            stringBuilder.append(array[i]);
            System.out.println(stringBuilder);
        }
    }

}
