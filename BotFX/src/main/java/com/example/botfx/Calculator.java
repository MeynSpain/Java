package com.example.botfx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static java.lang.Math.*;

public class Calculator {

    private Stack<Double> Stack_number = new Stack<>();				//Стек с числами
    private Stack<Character> Stack_operation = new Stack<>();		//Стек с операциями

    private boolean flag_bracket_right = false;            //Флаг, чтобы определить встретилась закрывающая скобка или нет
    private boolean flag_number = false;					//Флаг для нахождения первой цифры числа

    private final int c_round = 10000000;               //константа для округления тригонометрии

    private Map <Character , Integer> map_ranks_operation = new HashMap<>()		//map с рангом операций
            {{
                put('(', 0); put(')', 0);           //Ранг скобок
                put('+', 1); put('-', 1);           //Ранг  + -
                put('*', 2); put('/', 2);           //Ранг  * /
                put('^', 3);                        //Ранг  ^
                put('s', 4); put('c', 4);           //Ранг  sin cos
                put('t', 4); put('g', 4);           //Ранг  tg ctg
                put('e', 4); put('l', 4);           //Ранг  exp ln
            }};

    private Map <String, Character> map_operations = new HashMap<>()			//map с названием операций
            {{
                put("sin", 's'); put("cos", 'c');   //sin, cos
                put("tg", 't'); put("ctg", 'g');    //tg, ctg
                put("ln", 'l'); put("exp", 'e');    //ln, exp
            }};

    final double pi = acos(-1);				    //Константа Pi = 3.1415926535897932384626433832795028841971693993751058209749445923

	final double e = exp(1);					//Константа e =	2.7182818284590452353602874713526624977572470936999595749669676277

    Calculator()							//При создании кладе в стек с числами 0
    {
        Stack_number.push(0.00);
    }

    double Sin(double x)					//Вычисление синуса
    {
        return round(sin(x * pi / 180), 4); //Переводим радианы в градусы
//        return (round(sin(x) * 10000000) / 10000000);//А все потому что sin(pi) в стандартной ф-ии не равен 0, в общем проблемы с числом pi
//        return (sin(x * pi / 180));//А все потому что sin(pi) в стандартной ф-ии не равен 0, в общем проблемы
    }

    double Cos(double x)					//Вычисление  косинуса
    {
        return round(cos(x * pi / 180), 4); //Переводим радианы в градусы
//        return (round(cos(x * pi / 180)  * c_round) / c_round); //Переводим радианы в градусы
//        return (round(cos(x) * 10000000) / 10000000);
//        return cos(x);
    }

    double Tan(double x)					//Вычисление тангенса
    {
        return round(tan(x * pi / 180), 4);  //Переводим радианы в градусы
        //l_double a = Cos(x);
        //l_double b = Sin(x);
        //return (b / a);
    }

    double Ctg(double x)					//Вычисление котангенса
    {
        double a = Cos(x);
        double b = Sin(x);
        return round((a / b), 4);
    }

    private int get_rang(char operation) throws Exception                //Возвращает ранг операции
    {
//        if (map_ranks_operation.count(operation))			//Если ключ операции есть
        if (map_ranks_operation.containsKey(operation))			//Если ключ операции есть
        {
            return map_ranks_operation.get(operation);			//то возвращаем ранг этой операции
        }
        else
        {
            throw new Exception("\nInvalid operations!\n");	//Если не подошла ни одна из операций
        }

    }

    /**
     * Округляет число
     * @param value число для округления
     * @param places кол-во знаков после запятой
     * @return
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    boolean check_pred_number(String str, int i)				//Проверяет предыдущий символ число или нет
    {
        if (i != 0)
        {
            return  str.charAt(i - 1) >= '0' &&
                    str.charAt(i- 1) <= '9' ||
                    str.charAt(i - 1) == 'e' ||
                    (str.charAt(i - 1) == 'i' && str.charAt(i-2) == 'p');
        }
        else
        {
            return false;
        }
    }

    int push_pi(String  str, int i) throws Exception            //Положить pi в стек
    {
        if (str.charAt(i + 1) == 'i')
        {
            if (check_pred_number(str, i))
            {
                push_mult();
            }
            i++;						//переходим к следущему символу
            Stack_number.push(pi);		//Записывае Pi в стек
            flag_number = false;

        }
        return i;
    }

    void push_e(String str, Integer i) throws Exception        //Кладем e в стек
    {
        Stack_number.push(e);		//Записываем еxp в стек
        flag_number = false;		//Число кончилось

        if (check_pred_number(str, i))//если предыдущий элемент число
        {
            push_mult();          //То кладем умножение в стек с операциями
        }
    }

    void check_first_symbol(int i)		//смотрим первый символ и удаляем 0 из стека
    {
        if (i == 0)
        {
            Stack_number.pop();					//Удаляем 0 из стека
        }

    }

    boolean check_number(final String str, int i)		//Проверка число это или нет
    {
        if (str.charAt(i) == 'e' && i != str.length() - 1 && str.charAt(i + 1) == 'x' && str.charAt(i + 2) == 'p')
        {
            return false;
        }
        return str.charAt(i) >=
                '0' && str.charAt(i) <= '9' ||
                str.charAt(i) == 'p' || str.charAt(i) == 'e' ||
                str.charAt(i) == '.';
    }

    int push_number(String str, int position, int count)	//Вырезаем строку с числом и кладем в стек
    {
        String number_str = str.substring(position, position + count);	//Режем строку(получаем число)
        count = 0;											//И обнуляем кол-во разрядов числа

        Stack_number.push(Double.parseDouble(number_str));				//Записываем число в стек с числами
        flag_number = false;
        return count;
    }

    void cut_string(String str) throws Exception                    //Нарезает строку и заполняет стеки
    {
        flag_number = false;
        int position = 0;								//Позиция первой цифры числа
        int count = 0;								//Число разрядов в числе


        for (int i = 0; i < str.length(); i++)		//Идем по строке
        {



                    if (check_number(str, i))            //Читаем число
                    {
                        check_first_symbol(i);            //если первый символ число, то удаляем 0 из стека

                        if (str.charAt(i) == 'p' && i != str.length() - 1)        //Если это символ p и это не последний символ в строке
                        {
                            i = push_pi(str, i);        //кладем пи в стек
                            count = 0;                //обнуляем кол-во символов в числе
                            continue;                //переходим к следующей итерации
                        }

                        if (str.charAt(i) == 'e')        //Если это символ e
                        {
                            push_e(str, i);        // Записываем е в стек
                            count = 0;
                            continue;
                        }

                        if (flag_number == false)                //Если это первая цифра числа
                        {
                            flag_number = true;                    //То ставим флаг в true
                            position = i;                        //И запоминаем позицию i
                            count++;                            //Увеличиваем кол-во разрядов

                        } else                                    //Если нет
                        {
                            count++;                            //Увеличиваем кол-во разрядов
                        }

                        if (i < str.length() - 1)                //Если цифра не последняя в строке
                        {
                            if ((str.charAt(i + 1) < '0' || str.charAt(i + 1) > '9') && str.charAt(i + 1) != '.')    //Если следующий символ в строке это не число
                            {
                                count = push_number(str, position, count);
                                continue;
                            } else {
                                continue;
                            }
                        }

                        if (i == str.length() - 1)            //Если число последнее в строке
                        {
                            count = push_number(str, position, count);    //кладем число в стек
                            continue;
                        }

                    }
//            }
            else				//Если встретилось не число
            {

                if (str.charAt(i) == ' ')						//Игнорируем пробелы
                    continue;

                if (str.charAt(i) == '(')						//Если встретилась открывающаяся скобка
                {
                    if (i != 0)
                    {
                        if (check_pred_number(str, i))//если предыдущий элемент число
                        {
                            push_mult();          //То кладем умножение в стек с операциями
                        }
                    }
                    Stack_operation.push('(');			//То просто кладем ее в стек
                    continue;
                }

                if (str.charAt(i) == ')')
                {
                    flag_bracket_right = true;          //Встретили закрывающуюся скобку(можем считать
                    while (Stack_operation.peek() != '(')	//Пока не дойдем до открывающейся скобки - считаем
                    {
                        calculate();
                    }
                    Stack_operation.pop();				//Удаляем открывающуюся скобку
                    flag_bracket_right = false;         //Скобки больше нет
                    continue;
                }



                if (    str.charAt(i) == 's' ||
                        str.charAt(i) == 'c' ||
                        str.charAt(i) == 't' ||
                        str.charAt(i) == 'e' ||
                        str.charAt(i) == 'l')	//Если встретилась функция
                {
//                    symbol:

                    if (i == 0)
                    {
                        Stack_number.pop();				//Удаляем 0 из стека
                    }

                    if (check_pred_number(str, i))		//если предыдущий элемент число
                    {
                        push_mult();				//То кладем умножение в стек с операциями
                    }

                    String function;					//Делаем строку для функции

                    if (str.charAt(i) == 'l' || str.charAt(i) == 't')					//Для логарифма
                    {
                        function = str.substring(i, i + 2);	//И читаем нужное слово
                    }
                    else								//Для всех остальных функций
                    {
                        function = str.substring(i, i + 3);	//И читаем нужное слово
                    }

                    i += function.length() - 1;			//Передвигаем указатель на конец слова

                    if (map_operations.containsKey(function))	//Если такая операция есть, то
                    {
                        Stack_operation.push(map_operations.get(function));	//кладем операцию в стек с операциями
                        continue;
                    }
                    else
                    {
                        throw new Exception("\nInvalid operation!\n");
                    }
                }

                if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == '^')	//Если встретили операцию
                {
                    char operation = str.charAt(i);

                    if (i == 0)							//Если в начале стоит знак
                    {
                        Stack_operation.push(operation);//Кладем операцию на вершину стека
                    }
                    else								//В начале стоит число
                    {

                        if (Stack_operation.size() == 0 || get_rang(operation) > get_rang(Stack_operation.peek()))		//Если стек с операциями пуст или приоритет читаемой операции больше, то мы просто кладем операцию в стек
                        {
                            Stack_operation.push(operation);		//Кладем операцию на вершину стека
                        }
                        else					//Если стек не пустой и приоритет читаемой операции равен или меньше приоритету предыдущей операции, то
                        {

                            while (!Stack_operation.empty() && get_rang(operation) <= get_rang(Stack_operation.peek()))//Считаем, и потом результат кладем в стек с числами, а операцию в стек с операциями
                            {
                                calculate();

                            }
                            Stack_operation.push(operation);		//И после всех необходимых вычислений кладем операцию в стек


                        }
                    }

                }
                else
                {
                    throw new Exception("\nInvalid operation!\n");
                }

            }

        }
    }

    void push_mult() throws Exception                                    //Положить операцию умножить в стек с операциями
    {
        if (Stack_operation.size() == 0 || get_rang('*') > get_rang(Stack_operation.peek()))
        {
            Stack_operation.push('*');
        }
        else
        {
            while (!Stack_operation.empty() && get_rang('*') <= get_rang(Stack_operation.peek()))//Считаем, и потом результат кладем в стек с числами, а операцию в стек с операциями
            {
                calculate();

            }
            Stack_operation.push('*');		//И после всех необходимых вычислений кладем операцию в стек

        }
    }

    double result(String mat_expression) throws Exception                //Вычисление математического выражения
    {

        cut_string(mat_expression);						//Режем строку и в итоге получаем 2 стека

        if (Stack_number.empty())
        {
            throw new Exception("Where numbers?!");
        }


        while (Stack_operation.size() != 0)				//Пока стек операций не опустеет
        {
            calculate();                //Считаем
        }

        return Stack_number.peek();
    }

    void calculate() throws Exception                        //Функция берет из стека операцию и производит вычисление
    {
        double result;					//Результат операции
        double left, right;				//левое и правое число по бокам от операции

        if (Stack_operation.peek() == '(' && flag_bracket_right == false)    //если мы встретили открывающуюся скобку, а закрывающуюся не встречали
        {
            Stack_operation.pop();          //То просто удаляем ее
            return;
        }


        right = Stack_number.peek();			//Кладем число из стека в правый операнд
        Stack_number.pop();					//Удаляем это число из стека



        switch (Stack_operation.peek())		//Берем верхнюю операцию из стека
        {
            case '+':						//Складываем
                if (Stack_number.size() == 0)
                    throw new Exception("\nNot find one more operand!\n");

                left = Stack_number.peek();	//Кладем число из стека в левый операнд
                Stack_number.pop();			//Удаляем это число из стека
                result = left + right;		//Ну и складываем
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case '-':						//Вычитаем
                if (Stack_number.size() == 0)
                    throw new Exception("\nNot find one more operand!\n");

                left = Stack_number.peek();	//Кладем число из стека в левый операнд
                Stack_number.pop();			//Удаляем это число из стека
                result = left - right;		//Ну и вычитаем
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case '*':						//Умножаем
                if (Stack_number.size() == 0)
                    throw new Exception("\nNot find one more operand!\n");

                left = Stack_number.peek();	//Кладем число из стека в левый операнд
                Stack_number.pop();			//Удаляем это число из стека
                result = left * right;		//Ну и умножаем
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case '/':						//Делим
                if (Stack_number.size() == 0)
                    throw new Exception("\nNot find one more operand!\n");

                if (right == 0)				//Бросаем исключение если делим на 0
                    throw new Exception("\nCannot be divided by 0!\n");

                left = Stack_number.peek();	//Кладем число из стека в левый операнд
                Stack_number.pop();			//Удаляем это число из стека
                result = left / right;		//Ну и делим
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case '^':						//Возводим в степень
                if (Stack_number.size() == 0)
                    throw new Exception("\nNot find one more operand!\n");

                left = Stack_number.peek();	//Кладем число из стека в левый операнд
                Stack_number.pop();			//Удаляем это число из стека
                result = pow(left, right);	//Ну и возводим в степень
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case 's':						//sin
                result = Sin(right);		//Вычисляем sin(x)
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case 'c':						//cos
                result = Cos(right);		//Вычисляем cos(x)
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case 't':						//tangens
                if (Cos(right) == 0)		//Бросаем исключение если cos(x) = 0
                    throw new Exception("\nInvalid argument tangent!\n");

                result = Tan(right);		//Вычисляем tan(x)
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case 'g':						//ctg
                result = Ctg(right);		//Вычисляем ctg(x)
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case 'e':						//exp
                result = exp(right);		//Вычисляем exp(x)
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;

            case 'l':						//ln
                result = log(right);		//Вычисляем ln(x)
                Stack_number.push(result);	//Кладем результат в стек с числами
                Stack_operation.pop();		//И удаляем операцию из стека
                break;
        }
    }
}
