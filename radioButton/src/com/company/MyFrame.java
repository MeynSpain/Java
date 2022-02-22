package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    JRadioButton pizzaButton;
    JRadioButton humburgerButton;
    JRadioButton hotdogButton;


    MyFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

         pizzaButton = new JRadioButton("pizza");
         humburgerButton = new JRadioButton("humburger");
         hotdogButton = new JRadioButton("hotdog");

        //объединяем радио баттоны в одну группу, чтобы можно было выбрать только один
        ButtonGroup group = new ButtonGroup();
        group.add(pizzaButton);
        group.add(humburgerButton);
        group.add(hotdogButton);

        pizzaButton.addActionListener(this);
        humburgerButton.addActionListener(this);
        hotdogButton.addActionListener(this);

//        pizzaButton.setIcon(icon);    //так можно добавить картинку вместо селектора
//        pizzaButton.setSelectedIcon();//так можно добавить картинку вместо селектора


        this.add(pizzaButton);
        this.add(humburgerButton);
        this.add(hotdogButton);



        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == pizzaButton)
        {
            System.out.println("You're order " + pizzaButton.getText());
        }
        else if (e.getSource() == humburgerButton)
        {
            System.out.println("You're order " + humburgerButton.getText());
        }else if (e.getSource() == hotdogButton)
        {
            System.out.println("You're order " + hotdogButton.getText());
        }
    }
}
