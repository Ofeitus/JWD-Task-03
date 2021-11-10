package com.epam.ofeitus.calculator;

import com.epam.ofeitus.calculator.parser.ExpressionParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void testEvaluation() {
        // arrange
        Calculator calculator = new Calculator();

        // act
        int value = 0;
        try {
            value = calculator.evaluate("2**3**2 + ((2**3)**2//2 - 5*(243//3)**2) % 13");
        } catch (ExpressionParserException e) {
            e.printStackTrace();
        }

        // assert
        Assertions.assertEquals(512, value);
    }
}
