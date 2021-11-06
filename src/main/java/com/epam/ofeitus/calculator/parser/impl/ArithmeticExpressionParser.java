package com.epam.ofeitus.calculator.parser.impl;

import com.epam.ofeitus.calculator.operator.OperatorProvider;
import com.epam.ofeitus.calculator.parser.ExpressionParser;
import com.epam.ofeitus.calculator.parser.ExpressionParserException;
import com.epam.ofeitus.calculator.validator.ParenthesesValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticExpressionParser implements ExpressionParser {
    private boolean isOperator(String token) {
        return OperatorProvider.getOperator(token) != null;
    }

    private boolean isOperand(String token) {
        return token.matches("\\d+");
    }

    private static String escapeRegex(String key) {
        return key.replaceAll("[\\.\\^\\$\\*\\+\\-\\?\\(\\)\\[\\]\\{\\}\\|\\—\\/]", "\\\\$0");
    }

    @Override
    public List<String> parse(String expression) throws ExpressionParserException {
        // validate parentheses
        if (!ParenthesesValidator.validateParentheses(expression)) {
            throw new ExpressionParserException("Invalid parentheses in expression");
        }

        // construct validation regex, expression can only contain "(", ")", digits and whitespaces
        StringBuilder regex = new StringBuilder("([\\s\\S]*?)(\\(|\\)|\\d|\\s");
        // add operators to validation regex
        for (String key : OperatorProvider.getOperatorsKeys()) {
            regex.append("|").append(escapeRegex(key));
        }
        regex.append(")");
        // string to highlight invalid tokens
        StringBuilder invalidTokens = new StringBuilder(" ".repeat(expression.length()));
        Matcher matcher = Pattern.compile(regex.toString()).matcher(expression);
        boolean isValid = true;
        while (matcher.find()) {
            if (matcher.group(1).length() > 0) {
                for (int i = matcher.start(); i < matcher.end() - 1; i++) {
                    invalidTokens.setCharAt(i, '~');
                }
                isValid = false;
            }
        }
        if (!isValid) {
            throw new ExpressionParserException(
                    "Invalid tokens:\n " + expression + "\n " + invalidTokens.toString()
            );
        }

        List<String> tokens = new ArrayList<>();
        // construct search regex, parser searches only "(", ")", digits and whitespaces
        regex = new StringBuilder("(\\()|(\\))|(\\d+)");
        // add operators to search regex
        for (String key : OperatorProvider.getOperatorsKeys()) {
            regex.append("|(").append(escapeRegex(key)).append(")");
        }
        // string to highlight unexpected tokens
        StringBuilder unexpectedTokens = new StringBuilder(" ".repeat(expression.length()));
        matcher = Pattern.compile(regex.toString()).matcher(expression);
        while (matcher.find()) {
            // check unexpected tokens
            if (matcher.group().equals("(") && (
                    isOperand(matcher.group()) ||
                    (!tokens.isEmpty() && tokens.get(tokens.size() - 1).equals(")")))) {
                unexpectedTokens.setCharAt(matcher.start(), '^');
                isValid = false;
            } else if (matcher.group().equals(")") && (
                    (!tokens.isEmpty() && isOperator(tokens.get(tokens.size() - 1))))) {
                unexpectedTokens.setCharAt(matcher.start(), '^');
                isValid = false;
            } else if (isOperator(matcher.group()) && (
                    tokens.isEmpty() ||
                    tokens.get(tokens.size() - 1).equals("(") ||
                    isOperator(tokens.get(tokens.size() - 1)))) {
                unexpectedTokens.setCharAt(matcher.start(), '^');
                isValid = false;
            } else if (isOperand(matcher.group()) && (
                    (!tokens.isEmpty() && isOperand(tokens.get(tokens.size() - 1))) ||
                    (!tokens.isEmpty() && tokens.get(tokens.size() - 1).equals(")"))
                    )) {
                unexpectedTokens.setCharAt(matcher.start(), '^');
                isValid = false;
            }
            // add token to list
            tokens.add(matcher.group());
        }

        if (!tokens.isEmpty() && isOperator(tokens.get(tokens.size() - 1))) {
            unexpectedTokens.setCharAt(unexpectedTokens.length() - 1, '^');
            isValid = false;
        }

        if (!isValid) {
            throw new ExpressionParserException(
                    "Unexpected tokens:\n " + expression + "\n " + unexpectedTokens.toString()
            );
        }
        return tokens;
    }
}
