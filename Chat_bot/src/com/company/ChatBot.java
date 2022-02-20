package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatBot extends JFrame implements ActionListener //Наследуем класс JFrame, для объектов и реализуем метод класса ActionListener(итерфейсный)
{
    //4 константы
    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot";  //Заголовок окна
    final int START_LOCATION = 200;     //Стартовая позиция окна
    final int WINDOW_WIDTH = 350;       //Ширина окна
    final int WINDOW_HEIGHT = 450;      //Высота окна

    //Графические объекты, которые будут внутри формы
    JTextArea dialogue;
    JCheckBox ai;
    JTextField message;
    SimpleBot sbot;

    public static void main(String[] args)
    {
        new ChatBot();          //Создаем объект
    }

    ChatBot()                                       //конструктор
    {
        setTitle(TITLE_OF_PROGRAM);                 //устанавливаем заголовок окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Закрытие окна(делаем крестик)
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        //setBounds(START_LOCATION,START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);//задаем начальное положение и размеры формы

        //Создаем поле для ввода/вывода текста
        dialogue = new JTextArea();                 //Создаем поле для ввода/вывода текста
        dialogue.setLineWrap(true);                 //Делаем чтобы строки переносились(true)
        JScrollPane scrollbar = new JScrollPane(dialogue);  //создаем скроллбар(ползунок), чтобы листать вверх и вниз, также мы завернули внутрь этого ползунка наше поле диалога


        //Создаем панель для кнопок
        JPanel bp = new JPanel();                    //button panel - панель для кнопок
        bp.setLayout(new BoxLayout(bp,BoxLayout.X_AXIS));//Размещаем эту панель по горизонтали(x_axis)
        ai = new JCheckBox("AI");               //Также создаем чекбокс(чтобы поставить галочку)
        //ai.doClick();
        message = new JTextField();                  //Поле для наших реплик
        message.addActionListener(this);          //Срабатывает кнопка если нажали enter на клавиатуре
        JButton enter = new JButton("Enter");   //Кнопка ввода
        enter.addActionListener(this);            //Событие(прослушиватель) нажатия на кнопку(enter), и переходим к actionPerformed

        //Добавление элементов на форму
        bp.add(ai);                                 //Добавляем чекбокс на панель
        bp.add(message);                            //Добавляем поле ввода на панель
        bp.add(enter);                              //Добавляем кнопку на панель
        add(BorderLayout.CENTER, scrollbar);        //Размещаем наше диалогове поле с позунком на форме(в центре)
        add(BorderLayout.SOUTH, bp);                //Размещаем панель с полем ввода, чекбоксом и кнопкой внизу формы

        setVisible(true);                           //делаем форму видимой

        sbot = new SimpleBot();                   //Создаем бота
    }

    @Override
    public void actionPerformed(ActionEvent event)  //Переопределяем метод, который возникает когда нажимают на кнопку
    {
        if (message.getText().trim().length() > 0)  //Берем поле ввода, берем из него текст, (trim) - убираем все пробелы, и смотрим чтобы длина текста была больще 0
        {
            dialogue.append(message.getText() + "\n");  //Тогда берем наш текс из поля ввода и добавляем его в поле диалога и переносим строку
            dialogue.append("Bot: " + sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n");
        }
        message.setText("");                        //Очищаем наше поле ввода
        message.requestFocusInWindow();
    }
}
