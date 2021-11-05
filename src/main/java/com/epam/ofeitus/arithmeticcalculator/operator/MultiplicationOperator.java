package com.epam.ofeitus.arithmeticcalculator.operator;

public class MultiplicationOperator extends BinaryOperator {
    protected MultiplicationOperator() {
        super(2, ((a, b) -> a * b));
    }
}
