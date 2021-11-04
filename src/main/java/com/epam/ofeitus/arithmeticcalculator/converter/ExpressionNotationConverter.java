package com.epam.ofeitus.arithmeticcalculator.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionNotationConverter {
    private static int precedence(String ch)
    {
        return switch (ch) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    public static List<String> infixToPostfix(List<String> expression) throws ExpressionConverterException {
        List<String> postfix = new ArrayList<>();

        Stack<String> stack = new Stack<>();

        for (String token : expression)
        {
            switch (token) {
                case "(" -> stack.push(token);
                case ")" -> {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                    }
                    stack.pop();
                }
                case "+", "-", "*", "/" -> {
                    while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                        postfix.add(stack.pop());
                    }
                    stack.push(token);
                }
                default -> postfix.add(token);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty()){
            if (stack.peek().equals("("))
                throw new ExpressionConverterException("Invalid parentheses in expression");
            postfix.add(stack.pop());
        }
        return postfix;
    }
}
