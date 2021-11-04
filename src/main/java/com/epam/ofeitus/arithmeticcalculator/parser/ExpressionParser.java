package com.epam.ofeitus.arithmeticcalculator.parser;

import java.util.List;

public interface ExpressionParser {
    List<String> parse(String expression) throws ExpressionParserException;
}
