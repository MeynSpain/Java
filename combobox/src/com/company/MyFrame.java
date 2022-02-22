package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {


    JComboBox comboBox;

    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);   //форма по центру

        String[] animals = {"dog", "cat", "bird"};
        Integer[] d = {1,23,45,6};  //можно и так
        comboBox = new JComboBox(d);
        comboBox.addActionListener(this);

        //comboBox.setEditable(true); //можно писать
//        comboBox.getItemCount(); //вернет кол-во обхектов в боксе
        comboBox.addItem("horse");  //Добаление
        comboBox.insertItemAt("pig",2 );    //Вставка в определенное место
        comboBox.setSelectedIndex(0);       //ставит что изначально стоит в выборе
        //comboBox.removeItem("pig");    //удаляет элемент по названию
        //comboBox.removeItemAt(0);    //удаляет элемент по индексу
       // comboBox.removeAllItems();    //удаляет все эл=ты

        this.add(comboBox);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == comboBox)
        {
//            System.out.println(comboBox.getSelectedItem());
            System.out.println(comboBox.getSelectedIndex());
        }

    }
}
