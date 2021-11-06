package com.epam.ofeitus.calculator.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OperatorProvider {
    private static final Map<String, BinaryOperator> binaryOperators = new HashMap<>() {{
        put("+", new PlusOperator());
        put("-", new MinusOperator());
        put("*", new MultiplicationOperator());
        put("//", new DivisionOperator());
        put("%", new ModOperator());
        put("**", new PowerOperator());
    }};

    public static BinaryOperator getOperator(String name) {
        return binaryOperators.get(name);
    }

    public static Set<String> getOperatorsKeys() {
        return binaryOperators.keySet();
    }

    private OperatorProvider() {

    }
}
