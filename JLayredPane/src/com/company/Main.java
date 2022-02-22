package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.red);
        label1.setBounds(50,50,200,200);

        JLabel label2 = new JLabel();
        label2.setOpaque(true);
        label2.setBackground(Color.green);
        label2.setBounds(100,100,200,200);

        JLabel label3 = new JLabel();
        label3.setOpaque(true);
        label3.setBackground(Color.blue);
        label3.setBounds(150,150,200,200);


        JLayeredPane layeredPane = new JLayeredPane();      //Позволит менять слои(уровни) внутри контейнера
        layeredPane.setBounds(0,0,500,500);

//        layeredPane.add(label1,2);    //
//        layeredPane.add(label2, 1);   //  так лучше не делать
//        layeredPane.add(label3, 0);   //Будет выше

        layeredPane.add(label1,Integer.valueOf(0));    //
        layeredPane.add(label2, Integer.valueOf(1));   // Лучше делать вот так, чем больше цифра, тот и будет выше
        layeredPane.add(label3, Integer.valueOf(2));   //Будет выше

//        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);    //
//        layeredPane.add(label2, JLayeredPane.DEFAULT_LAYER);   //
//        layeredPane.add(label3, JLayeredPane.DRAG_LAYER);   //Будет выше

        JFrame frame = new JFrame("JLayredPane");
        frame.add(layeredPane);

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setVisible(true);
    }
}
