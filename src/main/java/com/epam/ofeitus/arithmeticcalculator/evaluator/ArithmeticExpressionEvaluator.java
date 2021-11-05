package com.epam.ofeitus.arithmeticcalculator.evaluator;

import com.epam.ofeitus.arithmeticcalculator.operator.BinaryOperator;
import com.epam.ofeitus.arithmeticcalculator.operator.OperatorProvider;

import java.util.List;
import java.util.Stack;

public class ArithmeticExpressionEvaluator {
    public Integer evaluate(List<String> postfix) {
        Stack<Integer> stack = new Stack<>();
        BinaryOperator operator;

        for (String token : postfix) {
            if ((operator = OperatorProvider.getOperator(token)) != null) {
                Integer op2 = stack.pop();
                Integer op1 = stack.pop();
                stack.push(operator.calculate(op1, op2));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
