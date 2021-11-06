package com.epam.ofeitus.calculator.operator;

public abstract class BinaryOperator {
    @FunctionalInterface
    public interface Operation {
        int calculate(int a, int b);
    }

    private final int precedence;

    private final boolean rightToLeft;

    private final Operation operation;

    protected BinaryOperator(int precedence, Operation operation, boolean rightToLeft) {
        this.precedence = precedence;
        this.operation = operation;
        this.rightToLeft = rightToLeft;
    }

    public int getPrecedence() {
        return precedence;
    }

    public boolean isRightToLeft() {
        return rightToLeft;
    }

    public int calculate(int a, int b) {
        return operation.calculate(a, b);
    }
}
