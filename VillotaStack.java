import java.util.Scanner;
import java.util.Stack;

/*
Description: Checks whether delimiters are properly matched
and displays the stack operations during execution.
Programmed by:  Fiona Erica A. Villota
Course: BSIT 
CN: 48160 
Subject: DATA STRUCTURES AND ALGORITHMS
Last Modified: October 19, 2026
Version: 1.0
Acknowledgements:OpenAI ChatGPT (GPT-6) was used to help explain the
laboratory instructions, draft sample Java code, and
explain stack operations.
"Explain the objectives and requirements of this Stack
laboratory activity in simple terms. Provide a sample
Java implementation of a stack-based delimiter checker
that I can study and use as a guide in developing my
own program."
*/

public class StackDelimiterChecker {

    // Check if a character is an opening delimiter
    static boolean isOpening(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    // Check if two delimiters match
    static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();

        System.out.print("Enter an expression: ");
        String input = scanner.nextLine();

        boolean valid = true;
        String reason = "";

        System.out.println("\n--- STACK EXECUTION TRACE ---");

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Opening delimiter: push onto the stack
            if (isOpening(ch)) {
                stack.push(ch);

                System.out.println("\nRead: " + ch);
                System.out.println("Operation: PUSH " + ch);
                System.out.println("Stack: " + stack);

            // Closing delimiter: check the stack
            } else if (ch == ')' || ch == ']' || ch == '}') {
                System.out.println("\nRead: " + ch);

                // No opening delimiter available
                if (stack.isEmpty()) {
                    System.out.println("Operation: INVALID");
                    reason =
                        "Closing delimiter has no matching opening delimiter: "
                        + ch;
                    valid = false;
                    System.out.println("Reason: " + reason);
                    System.out.println("Stack: " + stack);
                    break;
                }

                // Examine the top without removing it
                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);

                // Check whether the delimiters match
                if (isMatching(top, ch)) {
                    stack.pop();

                    System.out.println("Operation: POP " + top);
                    System.out.println("Stack: " + stack);

                } else {
                    System.out.println("Operation: INVALID");
                    reason = "Mismatched delimiters: top is "
                             + top + ", but closing delimiter is " + ch;
                    valid = false;
                    System.out.println("Reason: " + reason);
                    System.out.println("Stack: " + stack);
                    break;
                }
            }

            // Other characters, such as letters and numbers,
            // are ignored.
        }

        // Check whether unmatched opening delimiters remain
        if (valid && !stack.isEmpty()) {
            valid = false;
            reason = "Unmatched opening delimiter remains: " + stack;
            System.out.println("\nReason: " + reason);
        }

        System.out.println("\n--- FINAL RESULT ---");

        if (valid) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        scanner.close();
    }
}

