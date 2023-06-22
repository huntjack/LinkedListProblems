import java.util.NoSuchElementException;

public class SumLists {
    public static SinglyLinkedList<Integer> sumListsInReverseOrder(SinglyLinkedList<Integer> listOne, SinglyLinkedList<Integer> listTwo) {
        Node<Integer> currentOne =
                listOne
                        .getHead()
                        .orElseThrow();
        Node<Integer> currentTwo =
                listTwo
                        .getHead()
                        .orElseThrow();
        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();
        int size = listOne.size() > listTwo.size() ? listOne.size() : listTwo.size();
        int carryDigit = 0;
        for(int i = 0; i < size || carryDigit > 0; i++) {
            int currentOneValue = getNodeValue(currentOne);
            int currentTwoValue = getNodeValue(currentTwo);
            int sum = currentOneValue + currentTwoValue + carryDigit;
            if (sum > 9) {
                carryDigit = sum / 10;
                sum %= 10;
            } else {
                carryDigit = 0;
            }
            result.addLast(sum);
            if(currentOne != null) {
                currentOne = currentOne.getNext();
            }
            if(currentTwo != null) {
                currentTwo = currentTwo.getNext();
            }
        }
        return result;
    }
    private static int getNodeValue(Node<Integer> current) {
        return current == null ? 0 : current.getValue();
    }
    public static SinglyLinkedList<Integer> sumListsInForwardOrder(SinglyLinkedList<Integer> listOne, SinglyLinkedList<Integer> listTwo) {
        SinglyLinkedList<SinglyLinkedList<Integer>> listOfLists = addLeadingZeros(listOne, listTwo);
        listOne = listOfLists
                .get(0)
                .orElseThrow(NoSuchElementException::new);
        listTwo = listOfLists
                .get(1)
                .orElseThrow(NoSuchElementException::new);
        Node<Integer>  listOneHead =
                listOne
                        .getHead()
                        .orElseThrow();
        Node<Integer>  listTwoHead =
                listTwo
                        .getHead()
                        .orElseThrow();
        PartialResult partialResult = addDigits(listOneHead, listTwoHead);
        int carryDigit = partialResult.getCarryDigit();
        if(carryDigit > 0) {
            partialResult.addFirst(carryDigit);
        }
        SinglyLinkedList<Integer> resultList = partialResult.getResult();
        return resultList;
    }
    private static SinglyLinkedList<SinglyLinkedList<Integer>> addLeadingZeros(SinglyLinkedList<Integer> listOne, SinglyLinkedList<Integer> listTwo) {
        if(listOne == null || listTwo == null) {
            throw new IllegalArgumentException();
        }
        SinglyLinkedList<Integer> smallerList;
        int sizeOfLargerList;
        if(listOne.size() > listTwo.size()) {
            smallerList = listTwo;
            sizeOfLargerList = listOne.size();
        } else {
            smallerList = listOne;
            sizeOfLargerList = listTwo.size();
        }
        int numberOfZeros = sizeOfLargerList - smallerList.size();
        for(int i = 0; i < numberOfZeros; i++) {
            smallerList.addFirst(0);
        }
        return createListOfLists(listOne, listTwo);
    }
    private static SinglyLinkedList<SinglyLinkedList<Integer>> createListOfLists(SinglyLinkedList<Integer> listOne, SinglyLinkedList<Integer> listTwo) {
        SinglyLinkedList<SinglyLinkedList<Integer>> resultList = new SinglyLinkedList<>();
        resultList.addLast(listOne);
        resultList.addLast(listTwo);
        return resultList;
    }
    private static PartialResult addDigits(Node<Integer> digitOne, Node<Integer> digitTwo) {
        PartialResult partialResult;
        if(digitOne.getNext() != null) {
            partialResult = addDigits(digitOne.getNext(), digitTwo.getNext());
        } else {
            partialResult = new PartialResult();
        }
        int carryDigit = partialResult.getCarryDigit();
        int sum = digitOne.getValue() + digitTwo.getValue() + carryDigit;
        int newCarryDigit = 0;
        if (sum > 9) {
            newCarryDigit = sum / 10;
            sum %= 10;
        }
        partialResult.addFirst(sum);
        partialResult.setCarryDigit(newCarryDigit);
        return partialResult;
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> listOne = new SinglyLinkedList<>();
        listOne.addLast(9);
        listOne.addLast(9);
        listOne.addLast(7);
        listOne.addLast(0);
        SinglyLinkedList<Integer> listTwo = new SinglyLinkedList<>();
        listTwo.addLast(2);
        listTwo.addLast(9);
        listTwo.addLast(5);
        SinglyLinkedList<Integer> resultOne = sumListsInReverseOrder(listOne, listTwo);
        resultOne.print();
        System.out.println();
        SinglyLinkedList<Integer> copyOfListOne = listOne.copy();
        SinglyLinkedList<Integer>  copyOfListTwo = listTwo.copy();
        SinglyLinkedList<Integer>  resultTwo = sumListsInForwardOrder(copyOfListOne, copyOfListTwo);
        resultTwo.print();
    }
}
