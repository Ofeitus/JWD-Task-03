package com.epam.ofeitus.arithmeticcalculator.operator;

import java.util.HashMap;
import java.util.Map;

public class OperatorProvider {
    public static final Map<String, BinaryOperator> binaryOperators = new HashMap<>() {{
        put("+", new PlusOperator());
        put("-", new MinusOperator());
        put("*", new MultiplicationOperator());
        put("/", new DivisionOperator());
    }};

    public static BinaryOperator getOperator(String name) {
        return binaryOperators.get(name);
    }

    private OperatorProvider() {

    }
}
