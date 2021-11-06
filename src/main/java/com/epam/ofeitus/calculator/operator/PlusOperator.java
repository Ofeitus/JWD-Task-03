package com.epam.ofeitus.calculator.operator;

public class PlusOperator extends BinaryOperator {
    protected PlusOperator() {
        super(1, ((a, b) -> a + b), false);
    }
}
