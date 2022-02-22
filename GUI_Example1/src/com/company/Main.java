package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        //Пример с JPanel
        ImageIcon icon = new ImageIcon("src/com/company/logo1.png");

        JLabel label = new JLabel();
        label.setText("Hi");
        label.setIcon(icon);
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.RIGHT);

        JPanel redpanel = new JPanel();
        redpanel.setBackground(Color.red);
        redpanel.setBounds(0,0,250, 250);
        redpanel.setLayout(new BorderLayout());     //грубо говоря создаем разметку, чтобы перемещать label внутри панели

        JPanel bluepanel = new JPanel();
        bluepanel.setBackground(Color.BLUE);
        bluepanel.setBounds(250,0,250, 250);
        bluepanel.setLayout(new BorderLayout());    //Если же поставить null, то label не будет видно, тк он будет очень маленьким и придется
                                                    //устанавливать его значения и координаты через setBounds(x,y,widht, heught)
        JPanel greenpanel = new JPanel();
        greenpanel.setBackground(Color.GREEN);
        greenpanel.setBounds(0,250,500, 250);
        greenpanel.setLayout(new BorderLayout());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);

        bluepanel.add(label);
        frame.add(redpanel);
        frame.add(bluepanel);
        frame.add(greenpanel);
        /*
        //
        JFrame frame = new JFrame();        //создаем форму
        frame.setTitle("JFrame title");     //заголовок
        //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  //JFrame.DO_NOTHING_ON_CLOSE - чтобы не закрывалось окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //при нажатии крестика программа завершится и не будет висеть в процессах
        frame.setSize(420,420); //Задание размера
        frame.setResizable(false);          //Не даем изменять размер формы

        frame.setVisible(true);             //делаем видимой форму

        ImageIcon image = new ImageIcon("logo.png");    //создаем картинку и подгружаем ее
        frame.setIconImage(image.getImage());       //вставляем вместо иконки(у меня не получилось:( )

        //Меняем цвет задника (3 способа)
        frame.getContentPane().setBackground(Color.darkGray);   //Меняем цвет формы(задника)
        //frame.getContentPane().setBackground(new Color(0,0,0));   //Смена задника с помощью rgb(сделать цвет самому_
        //frame.getContentPane().setBackground(new Color(0xC71585));   //Меняем цвет формы(задника) HTML ЦВЕТА
*/

        //Пример с label
        /*
        ImageIcon image = new ImageIcon("src/com/company/Satyr.png");   //Загружаем картинку в объект image
        Border border = BorderFactory.createLineBorder(Color.BLUE,10);  //создаем границы и задаем им цвет и толщину

        JLabel label = new JLabel();
        label.setText("Bro, do you even code?");
        label.setIcon(image);               //Добавляем эту картинку в label

        label.setHorizontalTextPosition(JLabel.CENTER);    //Расположение текста слева, в центре или справа от картинки
        label.setVerticalTextPosition(JLabel.TOP);  //Расположение текста сверху, в центре или снизу от картинки

        label.setForeground(Color.BLUE);   //Задаем цвет текста
        label.setFont(new Font("MV Boli", Font.PLAIN, 50)); //Задаем шрифт(Название шрифта, его вид, разме)
        label.setIconTextGap(-10);      //Расстояние текста от картинки

        label.setBackground(Color.BLACK);   //Задаем цвет фона, но он не установится
        label.setOpaque(true);         //если не поставить здесь true(но он зальет вообще все!)

        label.setBorder(border);       //добавляем наши границу к label

        //Устанавлием местоположение label
        label.setVerticalAlignment(JLabel.TOP);     //По вертикали
        label.setHorizontalAlignment(JLabel.CENTER);//По горизонтали

        //MyFrame myframe = new MyFrame();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500, 500);
        frame.setVisible(true);
        frame.add(label);

        //Ограничим label
       // frame.setLayout(null);
        //label.setBounds(100,100,250,250);//Устанавливаем координаты label-а и его размеры

        frame.pack();   //Компонует компоненты и подгоняет форму под их размер, поэтому не обязательно указывать 56,61,62 строчки

        //Иконка приложения
        ImageIcon icon = new ImageIcon("src/com/company/logo.png");
        frame.setIconImage(icon.getImage());

         */

        /*
        JButton button1 = new JButton("One");
        JButton button2 = new JButton("Two");
        JButton button3 = new JButton("Three");
        JButton button4 = new JButton("Four");
        JButton button5 = new JButton("Five");
        JButton button6 = new JButton("Six");
        Icon icon = new ImageIcon("src/logo1.png");
        JButton button7 = new JButton(icon);
        Box box = Box.createVerticalBox();
        box.add(button1);
        box.add(button2);
        box.add(button3);
        box.add(button4);
        box.add(button5);
        box.add(button6);
        box.add(button7);
        JFrame frame = new JFrame();
        frame.add(box);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setSize(500, 300);
        frame.setVisible(true);

         */
    }
}
