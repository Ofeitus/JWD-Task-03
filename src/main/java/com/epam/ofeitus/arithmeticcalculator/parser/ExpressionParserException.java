package com.epam.ofeitus.arithmeticcalculator.parser;

public class ExpressionParserException extends Exception {
    public ExpressionParserException() {
        super();
    }

    public ExpressionParserException(String message) {
        super(message);
    }

    public ExpressionParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionParserException(Throwable cause) {
        super(cause);
    }
}
