package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));    //Компоненты  расположатся один за другим(leading слева)
                            //Расположение, отступ по горизонтали, по вертикали

        JPanel panel = new JPanel();

//        panel.setSize(100,250); //метод сработает, но не будет прямоугольника 100х250, будет просто полоска со всеми кнопками и высотой с кнопку
        panel.setPreferredSize(new Dimension(100,250));  //Размер панели как контейнера
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new FlowLayout());




        panel.add(new JButton("1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("7"));
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));




        frame.add(panel);
        frame.setVisible(true);

    }
}
