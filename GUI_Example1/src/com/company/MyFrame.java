package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame()       //конструктор класса
    {
        
        this.setTitle("JFrame title");     //заголовок
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  //JFrame.DO_NOTHING_ON_CLOSE - чтобы не закрывалось окно
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //при нажатии крестика программа завершится и не будет висеть в процессах
        this.setSize(420,420); //Задание размера
        this.setResizable(false);          //Не даем изменять размер формы

        this.setVisible(true);             //делаем видимой форму

        ImageIcon image = new ImageIcon("logo.png");    //создаем картинку и подгружаем ее
        this.setIconImage(image.getImage());       //вставляем вместо иконки(у меня не получилось:( )

        //Меняем цвет задника (3 способа)
        this.getContentPane().setBackground(Color.darkGray);   //Меняем цвет формы(задника)
        //this.getContentPane().setBackground(new Color(0,0,0));   //Смена задника с помощью rgb(сделать цвет самому_
        //this.getContentPane().setBackground(new Color(0xC71585));   //Меняем цвет формы(задника) HTML ЦВЕТА
    }
}
