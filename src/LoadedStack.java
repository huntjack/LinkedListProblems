import java.util.Calendar;

public class LoadedStack {
    private DoublyLinkedList<Character> stack;
    private Node<Character> characterNode;
    public LoadedStack(DoublyLinkedList<Character> stack, Node<Character> characterNode) {
        this.stack = stack;
        this.characterNode = characterNode;
    }
    public DoublyLinkedList<Character> getStack() {
        return stack;
    }

    public Node<Character> getCharacterNode() {
        return characterNode;
    }
}
