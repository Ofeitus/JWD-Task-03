package com.epam.ofeitus.arithmeticcalculator.parser.impl;

import com.epam.ofeitus.arithmeticcalculator.parser.ExpressionParser;
import com.epam.ofeitus.arithmeticcalculator.parser.ExpressionParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticExpressionParser implements ExpressionParser {
    @Override
    public List<String> parse(String expression) throws ExpressionParserException {
        Matcher matcher = Pattern.compile("[^-+*/()\\d\\s]+").matcher(expression);
        if (matcher.find()) {
            throw new ExpressionParserException(
                    "Unexpected token \"" + matcher.group() + "\" at position " + matcher.start()
            );
        }
        List<String> tokens = new ArrayList<>();
        matcher = Pattern.compile("[-+*/()]|(\\d+)").matcher(expression);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }
}
