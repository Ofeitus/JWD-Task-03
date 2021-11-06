package com.epam.ofeitus.calculator.operator;

public class MinusOperator extends BinaryOperator {
    protected MinusOperator() {
        super(1, ((a, b) -> a - b), false);
    }
}
