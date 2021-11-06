package com.epam.ofeitus.calculator.parser;

import java.util.List;

public interface ExpressionParser {
    List<String> parse(String expression) throws ExpressionParserException;
}
