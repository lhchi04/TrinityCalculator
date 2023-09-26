package edu.trinity;
import java.util.Stack;

public class RPNCalculator {
    public static double evaluate(String expr) {
        Stack<Integer> opnStack = new Stack<>();
        String[] tokens = expr.split(" ");
        int result = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].matches("-?[1-9][0-9]*|0")) {
                opnStack.push(Integer.parseInt(tokens[i]));
            }
            else {
                char operator = tokens[i].charAt(0);
                int opn2 = opnStack.pop();
                int opn1 = opnStack.pop();
                if (operator == '+')
                    result = opn2 + opn1;
                else if (operator == '-')
                    result = opn1 - opn2;
                else if (operator == '*')
                    result = opn1 * opn2;
                else if (operator == '/') {
                    if (opn2 == 0) {
                        throw new IllegalArgumentException();
                    }
                    result = opn1 / opn2;
                }
                else {
                    throw new IllegalArgumentException();
                }
                opnStack.push(result);
            }
        }
        return result;
    }
}
