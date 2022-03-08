package com.example.vector_interface;

public class Parser {


    Vector_int Pars(String str, Vector_int vector)      //Парсит строку и добавляет числа в вектор
    {

        int position_start = 0;
        int position_end;
        boolean flag_first = true;

        str =  str.trim().replaceAll("[\\s]{2,}" , " ");   //удаляем все пробелы и заменяем на один пробел

        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')   //Если наткнулись на цифру
            {
                if (flag_first == true)                         //Первая цифра
                {
                    position_start = i;
                    flag_first = false;
                }
            }

            if (str.charAt(i) == ' ' )
            {
                flag_first = true;
                position_end = i;
                String buf = str.substring(position_start, position_end);   //режем
                vector.push_back(Integer.parseInt(buf));                //Добавляем в массив
            }

            if(i == str.length() - 1)
            {
                flag_first = true;
                position_end = i + 1;
                String buf = str.substring(position_start, position_end);   //режем
                vector.push_back(Integer.parseInt(buf));                //Добавляем в массив
            }
        }
        return vector;
    }
}
