package calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import static calculator.Utils.isNumber;

/**
 * Высчитывает ОПН.
 */
public class ExpressionService {

    public ExpressionService(){
    }


    /**
     * Публичный результирующий метод для получения результата расчета пользовательского выражения.
     */
    public Deque<Double> resultDequeAfterCalculation(String inputExpression) {
        return calculatePostfixNotation(inputExpression);
    }


    /**
     * Приватный метод для получения результата расчета пользовательского выражения.
     */
    private Deque<Double> calculatePostfixNotation(String inputExpression) { //Обработка постфиксного выражения

        PolandNotationConverter polandNotationConverter = new PolandNotationConverter();
        ArrayList<String> validExpressionAfterConversation = polandNotationConverter.resultArrayAfterConversation(inputExpression);

        Deque<Double> resultStack = new ArrayDeque<>();
        double result;

        for (String element : validExpressionAfterConversation) {
            //Условие "Если элемент массива число, то перевод в дабл и пушим в стек"
            if (isNumber(element)) resultStack.push(Double.parseDouble(element));
            else {

                switch (element) {
                    case "*" -> {
                        result = resultStack.pop() * resultStack.pop();
                        resultStack.push(result);
                    }
                    case "/" -> {
                        double division = resultStack.pop();
                        result = resultStack.pop() / division;
                        resultStack.push(result);
                    }
                    case "-" -> {
                        result = -resultStack.pop() + resultStack.pop();
                        resultStack.push(result);
                    }
                    case "+" -> {
                        result = resultStack.pop() + resultStack.pop();
                        resultStack.push(result);
                    }
                    case "^" -> {
                        double exponentiation = resultStack.pop();
                        result = exponentiation * exponentiation;
                        resultStack.push(result);
                    }
                    default -> System.out.println("Алармус, это не оператор!");
                }
            }
        }
        return resultStack;
    }


}