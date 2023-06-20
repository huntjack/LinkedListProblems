
public class Palindrome {
    public static void main(String[] args) {
        String palindrome = new String("racecar");
        SinglyLinkedList<Character> palindromeList = toLinkedList(palindrome);
        String notPalindrome = new String("racecat");
        SinglyLinkedList<Character> notPalindromeList = toLinkedList(notPalindrome);
        System.out.println("This should be true: " + isPalindrome(palindromeList));
        System.out.println("This should be false: " + isPalindrome(notPalindromeList));
    }
    public static boolean isPalindrome(SinglyLinkedList<Character> characters) {
        if(characters == null) {
            throw new IllegalArgumentException();
        } else if (characters.isEmpty()) {
            return false;
        }
        int mid = characters.size() / 2;
        Node<Character> charactersHeadNode =
                characters
                        .getHead()
                        .orElseThrow();
        LoadedStack loadedStack = loadStack(charactersHeadNode, mid);
        Node<Character> current = loadedStack.getCharacterNode();
        MyStack<Character> stack = loadedStack.getStack();
        current = isOdd(characters.size()) ? current.getNext() : current;
        while(current != null) {
            char secondHalfChar = current.getValue();
            char firstHalfChar = stack
                    .pop();
            if(firstHalfChar != secondHalfChar) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
    private static LoadedStack loadStack(Node<Character> characterNode, int mid) {
        MyStack<Character> stack = new MyStack<>();
        for(int i = 0; i < mid; i++) {
            char character = characterNode.getValue();
            stack.push(character);
            characterNode = characterNode.getNext();
        }
        return new LoadedStack(stack, characterNode);
    }
    private static boolean isOdd(int length) {
        return length % 2 > 0 ? true : false;
    }
    private static SinglyLinkedList<Character> toLinkedList(String string) {
        SinglyLinkedList<Character> linkedList = new SinglyLinkedList<>();
        for(int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            linkedList.addLast(character);
        }
        return linkedList;
    }
}
