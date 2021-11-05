package com.epam.ofeitus.arithmeticcalculator.operator;

public class MinusOperator extends BinaryOperator {
    protected MinusOperator() {
        super(1, ((a, b) -> a - b));
    }
}
