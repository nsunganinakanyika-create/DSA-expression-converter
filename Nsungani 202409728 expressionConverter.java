
import java.util.Scanner;
import java.util.Stack;

// Added this import

public class ExpressionConverter {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '$': // exponent
                return 3;
        }
        return -1;
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                result += ch;
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }
            else {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(ch)) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Reverse string
    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        exp = reverse(exp);

        String temp = "";
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(')
                temp += ')';
            else if (exp.charAt(i) == ')')
                temp += '(';
            else
                temp += exp.charAt(i);
        }
        
        String postfix = infixToPostfix(temp);
        return reverse(postfix);
    }

    public static void main(String[] args) {
        // --- NEW CODE STARTS HERE ---
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the Infix expression you want to convert:");
        String infix = input.nextLine();
        
        // Optional: Remove spaces to prevent errors in processing
        infix = infix.replaceAll("\\s+", ""); 
        // --- NEW CODE ENDS HERE ---

        System.out.println("\n--- Conversion Results ---");
        System.out.println("Infix:   " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
        System.out.println("Prefix:  " + infixToPrefix(infix));
        
        input.close(); // Closing the scanner
    }
}
