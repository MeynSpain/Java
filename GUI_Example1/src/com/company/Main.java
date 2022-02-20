package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        /*
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
        MyFrame myframe = new MyFrame();

    }
}
