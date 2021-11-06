package com.epam.ofeitus.calculator.converter;

public class ExpressionConverterException extends Exception {
    public ExpressionConverterException() {
        super();
    }

    public ExpressionConverterException(String message) {
        super(message);
    }

    public ExpressionConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionConverterException(Throwable cause) {
        super(cause);
    }
}
