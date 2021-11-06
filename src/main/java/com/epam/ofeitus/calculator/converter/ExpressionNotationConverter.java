package com.epam.ofeitus.calculator.converter;

import com.epam.ofeitus.calculator.operator.BinaryOperator;
import com.epam.ofeitus.calculator.operator.OperatorProvider;

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

    private static boolean isRightToLeft(String token) {
        BinaryOperator operator;
        if ((operator = OperatorProvider.getOperator(token)) != null) {
            return operator.isRightToLeft();
        } else {
            return false;
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
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek()) &&
                        !(isRightToLeft(token) && isRightToLeft(stack.peek()))) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            } else {
                postfix.add(token);
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }
        return postfix;
    }
}
