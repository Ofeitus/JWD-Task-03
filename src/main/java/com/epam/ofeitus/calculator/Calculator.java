package com.epam.ofeitus.calculator;

import com.epam.ofeitus.calculator.converter.ExpressionConverterException;
import com.epam.ofeitus.calculator.converter.ExpressionNotationConverter;
import com.epam.ofeitus.calculator.evaluator.ArithmeticExpressionEvaluator;
import com.epam.ofeitus.calculator.parser.ExpressionParser;
import com.epam.ofeitus.calculator.parser.ExpressionParserException;
import com.epam.ofeitus.calculator.parser.impl.ArithmeticExpressionParser;

public class Calculator {
    public int evaluate(String expression) throws ExpressionParserException, ExpressionConverterException {
        // "2**3**2 + ((2**3)**2//2 - 5*(243//3)**2) % 13";
        ExpressionParser parser = new ArithmeticExpressionParser();
        ArithmeticExpressionEvaluator evaluator = new ArithmeticExpressionEvaluator();
        return evaluator.evaluate(ExpressionNotationConverter.infixToPostfix(parser.parse(expression)));
    }
}
