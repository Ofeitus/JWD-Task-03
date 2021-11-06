package com.epam.ofeitus.calculator.operator;

public class DivisionOperator extends BinaryOperator {
    protected DivisionOperator() {
        super(2, ((a, b) -> a / b), false);
    }
}
