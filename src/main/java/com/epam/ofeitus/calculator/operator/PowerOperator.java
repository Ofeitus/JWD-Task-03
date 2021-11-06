package com.epam.ofeitus.calculator.operator;

public class PowerOperator extends BinaryOperator {
    protected PowerOperator() {
        super(3, ((a, b) -> {
            int c = 1;
            for (int i = 1; i <= b; i++) {
                c *= a;
            }
            return c;
        }), true);
    }
}
