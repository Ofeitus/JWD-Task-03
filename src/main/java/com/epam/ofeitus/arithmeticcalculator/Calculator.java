package com.epam.ofeitus.arithmeticcalculator;

import com.epam.ofeitus.arithmeticcalculator.converter.ExpressionConverterException;
import com.epam.ofeitus.arithmeticcalculator.converter.ExpressionNotationConverter;
import com.epam.ofeitus.arithmeticcalculator.parser.ExpressionParser;
import com.epam.ofeitus.arithmeticcalculator.parser.ExpressionParserException;
import com.epam.ofeitus.arithmeticcalculator.parser.impl.ArithmeticExpressionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "12+(34*(45-0)+23)/1";
        ExpressionParser parser = new ArithmeticExpressionParser();
        List<String> tokens = new ArrayList<>();
        try {
            tokens = parser.parse(expression);
        } catch (ExpressionParserException e) {
            e.printStackTrace();
        }

        for (String token : tokens) {
            System.out.print(token + " ");
        }
        System.out.println();

        try {
            tokens = ExpressionNotationConverter.infixToPostfix(tokens);
        } catch (ExpressionConverterException e) {
            e.printStackTrace();
        }

        for (String token : tokens) {
            System.out.print(token + " ");
        }
    }
}
