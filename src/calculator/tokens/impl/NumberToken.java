package calculator.tokens.impl;

import calculator.tokens.MathOperations;
import calculator.tokens.Tokens;

public class NumberToken implements MathOperations, Tokens {

    @Override
    public double calculate(double[] args) {
        return 0;
    }

    @Override
    public String getSymbol() {
        return "0";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isNumber() {
        return true;
    }
}
