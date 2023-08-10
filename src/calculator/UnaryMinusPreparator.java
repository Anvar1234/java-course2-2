package calculator;


import java.util.ArrayList;
import java.util.Arrays;

import static calculator.Fields.brackets;
import static calculator.Utils.addSpaces;

/**
 * Класс для преобразования пользовательского выражения, как то: проверка на унарный минус,
 * замена унарного минуса спецсимволом, замена спецсимвола на набор символов, после
 * прохождения необходимых проверок.
 * Имеет один публичный метод, который возвращает окончательный результат для дальнейшего использования.
 */
public class UnaryMinusPreparator {


    public UnaryMinusPreparator() {}


    /**
     * Публичный результирующий метод для получения коллекции (списка),
     * после необходимых трансформаций пользовательского выражения.
     */
    public ArrayList<String> resultArrayAfterTransformation(String inputExpression) {
        return unaryMinusChanger(inputExpression);
    }



    /**
     * Метод проверки в пользовательском выражении наличия унарного минуса и его замены.
     */
    private ArrayList<String> unaryMinusChanger(String inputExpression) {
        MathExpressionValidator mathExpressionValidator = new MathExpressionValidator(inputExpression);
        //проверка выражения на валидность.
        try {
            mathExpressionValidator.isExpressionValid();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        //получаем чищенное от пробелов выражение из валидатора.
        String expression = mathExpressionValidator.getExpression();
        //разделяем валидное выражение на токены и операнды с помощью addSpaces и далее сплитуем в список ArrayList.
        ArrayList<String> validExpression = new ArrayList<>(Arrays.asList(addSpaces(expression).split(" ")));
        ArrayList<String> tempArray = new ArrayList<>();

        for (int i = 0; i < validExpression.size(); i++) {
            //если элемент не минус, то добавляем его в выводную коллекцию.
            if (!validExpression.get(i).equals("-")) {
                tempArray.add(validExpression.get(i));
                //иначе если элемент является первым в коллекции (i==0),
                // то в вывод коллекцию добавляем строки 0 и -.
            } else if (i == 0) {
                tempArray.add("0");
                tempArray.add("-");
                //иначе, если элемент "-" не первый, проверяем есть ли перед ним откр скобка, если
                // да, то в вывод коллекцию добавляем строки 0 и -.
            } else if (brackets.containsValue(validExpression.get(i - 1).charAt(0))) {
                tempArray.add("0");
                tempArray.add("-");
                //если минус - это не первый элемент и перед ним нет откр скобки,
                // то добавляем в выводную коллекцию.
            } else tempArray.add("-");
        }
        return tempArray;

    }

}