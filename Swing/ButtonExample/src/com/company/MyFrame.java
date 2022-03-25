package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    private JButton button;
    private JLabel label;

    MyFrame(){
        ImageIcon icon = new ImageIcon("src/icon.png");
        ImageIcon icon2 = new ImageIcon("src/icon2.png");

        label = new JLabel();
        label.setIcon(icon2);
        label.setBounds(150,250, 150, 150);
        label.setVisible(false);

        button = new JButton();
        button.setBounds(100,100,250,100);
        button.addActionListener(this);   //При нажатии на кнопку в консоль выводится сообщение
        //button.addActionListener(e -> System.out.println("Poo"));   //При нажатии на кнопку в консоль выводится сообщение
        //Без реализации класса ActionListener

        button.setText("I'm button!");
        button.setFocusable(false);  //если true или этой функции нет, то после нажатия текст выделяется
        button.setIcon(icon);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setIconTextGap(-15);         //расположени текста по отношению к картинке
        button.setBackground(Color.white);  //цвет кнопки
        button.setForeground(Color.CYAN);   //цвет текста
        button.setBorder(BorderFactory.createEtchedBorder());


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);

        this.add(button);
        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button)
        {
            //  System.out.println("Poo");
            label.setVisible(true);
           // button.setEnabled(false);   //делает кнопку не кликабельной
        }
    }
}
