package com.company;

import javax.swing.*;
import java.awt.*;

public class ProgressBarDemo {

    JFrame frame = new JFrame();
    JProgressBar bar = new JProgressBar();

    ProgressBarDemo()
    {
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bar.setValue(0);    //задаем начальное значение 0 - 100
        bar.setBounds(0,0,420,50);  //положение и размер
        bar.setStringPainted(true); //Надпись процентов и их кол-во
        bar.setMaximum(100);
        bar.setFont(new Font("MV Boli", Font.PLAIN, 20));

        bar.setForeground(Color.red);   //Цвет полосы, которая увеличивается
        bar.setBackground(Color.blue);  //Цвет полосы, которая уменьшается



        frame.add(bar);

        frame.setVisible(true);

        fill();
    }

    public void fill()
    {
        int counter = 0;

        while (counter <=bar.getMaximum())
        {
            bar.setValue(counter);

            try {   //чтобы Thread работал
                Thread.sleep(50);  //делаем простой
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            counter++;
        }
        bar.setString("Finish!");   //сообщение которое появится после того как бар заполнится
    }

}
