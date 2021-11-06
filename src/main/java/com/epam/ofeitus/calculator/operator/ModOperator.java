package com.epam.ofeitus.calculator.operator;

public class ModOperator extends BinaryOperator {
    protected ModOperator() {
        super(2, ((a, b) -> a % b), false);
    }
}
