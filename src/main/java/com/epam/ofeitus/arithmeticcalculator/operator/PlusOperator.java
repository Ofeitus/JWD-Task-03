package com.epam.ofeitus.arithmeticcalculator.operator;

import com.epam.ofeitus.arithmeticcalculator.operator.BinaryOperator;

public class PlusOperator extends BinaryOperator {
    protected PlusOperator() {
        super(1, ((a, b) -> a + b));
    }
}
