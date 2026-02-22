import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Validate_Properly_Nested_Brackets {
    public static void main(String[] args) {

    }

    public static boolean areBracketsProperlyMatched(String code_snippet) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < code_snippet.length(); i++) {
            char c = code_snippet.charAt(i);

            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == ')') {
                if (stack.isEmpty()) return false;

                char open = stack.pop();
                if (!((c == ']' && open == '[') ||
                        (c == '}' && open == '{') ||
                        (c == ')' && open == '('))) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
