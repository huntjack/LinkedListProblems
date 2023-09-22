package linked_lists;

import common.Node;
import common.SinglyLinkedList;

import java.util.NoSuchElementException;

public class DeleteMiddleNode {
    public static <T> boolean deleteMiddleNode(Node<T> node) {
        if(node == null || node.getNext() == null) {
            return false;
        }
        Node<T> next = node.getNext();
        node.setValue(next.getValue());
        node.setNext(next.getNext());
        next.setNext(null);
        return true;
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        linkedList.addLast(99);
        linkedList.addLast(99);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(5);
        linkedList.addLast(99);
        linkedList.addLast(5);
        Node<Integer> target = linkedList
                .getNode(4)
                .orElseThrow(NoSuchElementException::new);
        deleteMiddleNode(target);
        linkedList.print();
    }
}
