package calculator;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

/**
 * Класс отвечающий за взаимодействие с пользователем.
 */

//todo ВОПРОС:
// 1.можно ли использовать в разных методах одинаковые названия возвращаемых одним
// и входящих в другой аргументов?
// Кажется что так вроде понятнее читается, например везде экспрешн.
// 2.как использовать удаленные из конструкторов трай кэтчи уже в методах?


public class ConsoleView {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //примеры выражений и ОПН выражения:
        System.out.println("-3*1*2^+5/2 = [0, 1, -, 3, *, 1, *, 2, ^, *, 5, 2, /, +]");
        System.out.println("-(-1-(1+2)) = [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]");
        System.out.println("1-(1+2)-3+4-5*7 = [1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -]");
        System.out.println("-3+(1-(1+2)) = [0, 1, -, 3, *, 1, 1, 2, +, -, +]");
        System.out.println("1*(2-(3-4)) = [1, 2, 3, 4, -, -, *]");
        System.out.println("3+1*2^+5 = [3, 1, 2, ^, *, +, 5, +]\n");

        //присваиваем пользовательский ввод переменной inputExpression.
        String inputExpression = prompt();

        //
        UnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator();
        ArrayList<String> tempArray = unaryMinusPreparator.resultArrayAfterTransformation(inputExpression); // specialSymbolChanger();
        System.out.println("После трансформатора:\n" + tempArray);

        //Загоняем выражение, прошедшее проверку и преобразование в ОПН-конвертер.
        PolandNotationConverter polandNotationConverter = new PolandNotationConverter();
        ArrayList<String> resultArrayOfConversation = polandNotationConverter.resultArrayAfterConversation(inputExpression);
        System.out.println("ОПН:\n" + resultArrayOfConversation);

        //Загоняем ОПН-выражение в калькулятор (котор высчитывает ОПН).
        ExpressionService expressionService = new ExpressionService();
        Deque<Double> finalResultArray = expressionService.resultDequeAfterCalculation(inputExpression);
        //Выводим результат работы калькулятора.
        System.out.println("Результат:\n" + finalResultArray);


    }


    //    private static void start(){
//        while (true){
//            prompt();
//        }
//    }
    private static String prompt() {
        System.out.print("Введите выражение: ");
        return sc.nextLine();
    }
}
