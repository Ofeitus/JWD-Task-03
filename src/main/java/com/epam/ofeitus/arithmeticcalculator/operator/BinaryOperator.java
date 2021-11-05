package com.epam.ofeitus.arithmeticcalculator.operator;

public abstract class BinaryOperator {
    @FunctionalInterface
    public interface Operation {
        int calculate(int a, int b);
    }

    private final int precedence;

    private final Operation operation;

    protected BinaryOperator(int precedence, Operation operation) {
        this.precedence = precedence;
        this.operation = operation;
    }

    public int getPrecedence() {
        return precedence;
    }

    public int calculate(int a, int b) {
        return operation.calculate(a, b);
    }
}
