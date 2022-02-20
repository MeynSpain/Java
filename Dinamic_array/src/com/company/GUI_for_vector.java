package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_for_vector extends JFrame {
    private JButton button = new JButton();
    private  JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel();


    public GUI_for_vector()
    {
        super("Vector int");
        this.setBounds(100,100,250,250);            //местоположение и размеры формы
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          //Закрытие формы

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2 , 2, 2));
        container.add(label);
        container.add(input);
        container.add(button);

    }
}
