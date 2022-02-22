package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //Диалоговое окно(обычное)            родительски        , текст сообщения                  , заголовок   , тип сообщения
//        JOptionPane.showMessageDialog(null,"THis is some useless info", "title", JOptionPane.PLAIN_MESSAGE);
//        JOptionPane.showMessageDialog(null,"THis is some useless info", "title", JOptionPane.INFORMATION_MESSAGE);
//        JOptionPane.showMessageDialog(null,"THis is some useless info", "title", JOptionPane.QUESTION_MESSAGE);
//        JOptionPane.showMessageDialog(null,"THis is some useless info", "title", JOptionPane.WARNING_MESSAGE);
//        JOptionPane.showMessageDialog(null,"THis is some useless info", "title", JOptionPane.ERROR_MESSAGE);

        //YEs - 0, No - 1, Cancel - 2, Крестик - -1
//        int answer = JOptionPane.showConfirmDialog(null, "bro, do you even code?", "this is title", JOptionPane.YES_NO_CANCEL_OPTION);
//        //Окно ввода (input)
//        String name = JOptionPane.showInputDialog("What is your Name?");
//        System.out.println(name);


        String[] respones = {"No, you're awesome!", "thank you!", "*blush*"};
        ImageIcon icon = new ImageIcon("src/smile.png");


        JOptionPane.showOptionDialog(null,//от  кого наследуется,
                "You are awesome!", // текст
                "secret message",   //заголовок
                JOptionPane.YES_NO_CANCEL_OPTION,   //какие кнопки
                JOptionPane.INFORMATION_MESSAGE,    //вид сообщения
                icon,       //Изображение
                respones,   //Текст на кнопках
                0);         //не знаю



    }
}
