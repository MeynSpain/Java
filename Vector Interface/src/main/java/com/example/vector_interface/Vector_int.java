package com.example.vector_interface;

//Класс вектор для работы с динамическим массивом типа int
//Автор Сорокин Александр Егорович ВМК-20-1

public class Vector_int extends Exception
{
    //Поля
    private int[] ptr;						//Указатель на массив
    private int size;					    //Размер массива

    //Методы

    //////////////  Конструкторы \\\\\\\\\\\\\\\
    Vector_int()                            //Конструктор по умолчанию
    {
        ptr = null;
        size = 0;
    }

    Vector_int(int size)                    //Конструктор, выделяет память и заполняет нулями
    {
        assert(size > 0);
        this.size = size;
        ptr = new int[size];

        for (int i = 0; i < size; i++)
        {
            ptr[i] = 0;
        }
    }


    Vector_int(int size, int value)         //Конструктор, выделяет память и заполняет числом
    {
        assert(size > 0);
        this.size = size;
        ptr = new int[size];

        for (int i = 0; i < size; i++) {
            ptr[i] = value;
        }
    }

    Vector_int (Vector_int v2)				//Конструктор копирования
    {
        this.ptr = new int[v2.size];		//Выделяем память под еще один массив
        this.size = v2.size;

        for (int i = 0; i < size; i++) {	//Копируем значения из одного массива в другой
            this.ptr[i] = v2.ptr[i];
        }
    }

    //////////////  Методы добавления \\\\\\\\\\\\\\\

    void push_back(int value)	            //Добавление элемента в конец массива
    {
        int[] ptr2 = new int[size + 1];		//Выделяем память под новый массив

        for (int i = 0; i < size; i++)      //Копируем все значения из
        {
            ptr2[i] = ptr[i];				//одного массива в другой
        }

        ptr2[size] = value;					//добавляем последний элемент


        ptr = ptr2;							//переводим указатель на новый массив
        ptr2 = null;
        this.size++;
    }

    void set(int index, int value)	        //Добавления в определенный индекс массива заданное значение
    {
        assert(index >= 0 && index < size);
        ptr[index] = value;
    }

    void resize(int new_size)				//Изменение размера массива
    {
        int[] ptr2 = new int[new_size];		//Выделяем память под новый массив

        if (new_size > size)
        {
            for (int i = 0; i < size; i++)  //Копируем все значения из
            {
                ptr2[i] = ptr[i];		    //одного массива в другой
            }

            for (int i = size; i < new_size; i++){	//Заполняем нулями оставшиеся элементы
                ptr2[i] = 0;
            }
        }
        else
        {
            for (int i = 0; i < new_size; i++) {	//Копируем все значения из
                ptr2[i] = ptr[i];					//одного массива в другой
            }
        }
        size = new_size;

        ptr = ptr2;										//Переопределям указатель на массив
        ptr2 = null;
    }

    Vector_int copy(Vector_int v2)	        //Копирование вектора
    {
        ptr = null;

        ptr = new int[v2.get_size()];       //Выделяем память под копию массива

        this.size = v2.size;                //задаем значение размера

        for (int i = 0; i < size; i++) {	//Копируем значения из одного массива в другой
            this.ptr[i] = v2.ptr[i];
        }

        return this;					    //Возвращаем массив
    }

    void insert(int index, int Value)	    //Вставляет элемент в массиве по указанному индексу
    {
        assert  index >= 0 && index < size;

        int[] ptr2 = new int[size + 1];		//Выделяем память под новый массив
        this.size++;

        for (int i = 0; i < index; i++)     //Копируем значения до индекса
        {
            ptr2[i] = ptr[i];			    //одного массива в другой
        }

        ptr2[index] = Value;

        for (int i = index + 1; i < size; i++)//Копируем оставшиеся значения
        {
            ptr2[i] = ptr[i - 1];
        }

        ptr = ptr2;							   //переводим указатель на новый массив
        ptr2 = null;

    }

    void swap(int index1, int index2)       //Меняет местами значения лежащие по указанным индексам
    {

        //assert(index1 >= 0 && index1 < size && index2 >= 0 && index2 < size);		//провк

        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {			//Проверка на верность индексов
            throw new AssertionError("Does not match the size of the array!");
        }
        else
        {
            int buffer;									//Обычная смена значений через буфферную переменную
            buffer = ptr[index1];
            ptr[index1] = ptr[index2];
            ptr[index2] = buffer;
        }
    }

    //////////////  Геттеры \\\\\\\\\\\\\\\
    public int get(int index)				//Доступ к элементу лежащему по данному индексу
    {
        if (index < 0 || index >= size)
            throw new ArithmeticException("Выход за границы массива");
        return ptr[index];
    }

    int get_size() 							//Возвращает размер массива
    {
        return this.size;
    }

    //////////////  Удаление  \\\\\\\\\\\\\\\
    void pop_back()	throws ArithmeticException						//Удаление последнего элемента
    {
        if (size == 0)
            throw  new ArithmeticException("Массив и так пустой, что ты пытаешься удалить?");

        int[] ptr2 = new int[size - 1];		//Выделяем память по новый массив на размер 1 меньше

        this.size--;						//Уменьшаем размер массива на 1

        for (int i = 0; i < size; i++) {	//Копируем все значения из
            ptr2[i] = ptr[i];				//одного массива в другой
        }

        ptr = ptr2;							//Меняем указатель на новый массив
        ptr2 = null;
    }

    void erase(int index, int count) throws ArithmeticException        //Вырезать элеметы из массива начиная с указанного индекса нужное кол-во
    {
        if ((index < 0 || index >= size) || (index + count >= size))
            throw new ArithmeticException("Выход за пределы массива!");
        assert(index >= 0 && index < size);
        int[] ptr2 = new int[size - count];	//Выделяем память под новый массив

        for (int i = 0; i < index; i++) {	//Копируем все до индекса
            ptr2[i] = ptr[i];
        }

        for (int i = index + count; i < size; i++) //Копируем все после выреззаной части
        {
            ptr2[i - count] = ptr[i];
        }
        size = size - count;
        ptr = ptr2;							//Переопределяем указатель на массив
        ptr2 = null;
    }

    void clear()							//Очистка памяти от массива
    {
        this.size = 0;
        ptr = null;
    }

    int last()                              //Вернет значение последнего элемента
    {
        return ptr[size - 1];
    }


}
