import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> charStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                charStack.push(c);
            } else {

                if (charStack.isEmpty()) {
                    return false;
                }

                char topC = charStack.pop();

                if (c == ']' && topC != '[') {
                    return false;
                }

                if (c == '}' && topC != '{') {
                    return false;
                }

                if (c == ')' && topC != '(') {
                    return false;
                }
            }
        }

        return charStack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).isValid("(}){}"));
        System.out.println((new Solution()).isValid("{()}"));
        System.out.println((new Solution()).isValid("{()}"));
    }
}
