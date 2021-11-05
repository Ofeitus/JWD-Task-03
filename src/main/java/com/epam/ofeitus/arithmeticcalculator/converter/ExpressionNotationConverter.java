package com.epam.ofeitus.arithmeticcalculator.converter;

import com.epam.ofeitus.arithmeticcalculator.operator.BinaryOperator;
import com.epam.ofeitus.arithmeticcalculator.operator.OperatorProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionNotationConverter {
    private static int precedence(String token)
    {
        BinaryOperator operator;
        if ((operator = OperatorProvider.getOperator(token)) != null) {
            return operator.getPrecedence();
        } else {
            return -1;
        }
    }

    public static List<String> infixToPostfix(List<String> expression) throws ExpressionConverterException {
        List<String> postfix = new ArrayList<>();

        Stack<String> stack = new Stack<>();

        for (String token : expression) {
            if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else if (OperatorProvider.getOperator(token) != null) {
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            } else {
                postfix.add(token);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                throw new ExpressionConverterException("Invalid parentheses in expression");
            }
            postfix.add(stack.pop());
        }
        return postfix;
    }
}
