package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {

    JLabel label;
    ImageIcon icon;

    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(null);

        icon = new ImageIcon("src/hero.png");

        this.addKeyListener(this);      //теперь форма будет реагировать на нажатии кнопок

        label = new JLabel();
        label.setIcon(icon);
        label.setBounds(0,0,100,100);
        //label.setBackground(Color.red);
        //label.setOpaque(true);

        this.getContentPane().setBackground(Color.RED);   //Меняем цвет формы


        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar())
        {
            case 'a':
                label.setLocation(label.getX() - 10, label.getY());  //при нажатии двигаем влево
                break;
            case 'w':
                label.setLocation(label.getX(), label.getY() - 10);  //при нажатии двигаем вверх
                break;                                  //такие знаки, изза координатной сетки
            case 's':
                label.setLocation(label.getX(), label.getY() + 10);  //при нажатии двигаем вниз
                break;
            case 'd':
                label.setLocation(label.getX() + 10, label.getY());  //при нажатии двигаем вправо
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case 37:
                label.setLocation(label.getX() - 10, label.getY());  //при нажатии двигаем влево
                break;
            case 38:
                label.setLocation(label.getX(), label.getY() - 10);  //при нажатии двигаем вверх
                break;                                  //такие знаки, изза координатной сетки
            case 40:
                label.setLocation(label.getX(), label.getY() + 10);  //при нажатии двигаем вниз
                break;
            case 39:
                label.setLocation(label.getX() + 10, label.getY());  //при нажатии двигаем вправо
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released key char: " + e.getKeyChar());
        System.out.println("You released key code: " + e.getKeyCode());
    }
}
