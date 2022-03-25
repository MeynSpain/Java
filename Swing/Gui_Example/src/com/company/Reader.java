package com.company;

import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;

public class Reader extends JFrame{

    private JButton b1,b2;
    private JLabel l1,l2,l3,l4;
    private JTextField t1, t2;
    private String s1, s2;

    public Reader(String s)
    {
        super(s);
        setLayout(new FlowLayout());
        b1 = new JButton("Очистить");
        b2 = new JButton("Посчитать");
        l1 = new JLabel("Введите первое число:");
        l2 = new JLabel("Введите второе число:");
        l3 = new JLabel("");
        l4 = new JLabel("");
        t1 = new JTextField(10);
        t2 = new JTextField(10);

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(l4);
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {

        Reader r = new Reader("Title");
        r.setSize(280, 200);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setLocationRelativeTo(null);
        r.setVisible(true);
        r.setResizable(false);
    }
}
