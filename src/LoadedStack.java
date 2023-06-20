public class LoadedStack {
    private MyStack<Character> stack;
    private Node<Character> characterNode;
    public LoadedStack(MyStack<Character> stack, Node<Character> characterNode) {
        this.stack = stack;
        this.characterNode = characterNode;
    }
    public MyStack<Character> getStack() {
        return stack;
    }

    public Node<Character> getCharacterNode() {
        return characterNode;
    }
}
