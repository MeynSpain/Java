package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SliderDemo implements ChangeListener {

    JFrame frame;
    JPanel panel;
    JLabel label;
    JSlider slider;

    SliderDemo()
    {
        frame = new JFrame("Slider Demo");
        panel = new JPanel();
        label = new JLabel();
        slider = new JSlider(0,100,50); //значения min,max,стартовое
        slider.addChangeListener(this);

        slider.setPreferredSize(new Dimension(400,200));

        //Промежуточная(маленькая)
        slider.setMinorTickSpacing(5); //задает разметку на расстоянии n пикселей
        slider.setPaintTicks(true);     //Делает эту разметку видимой

        //Более крупная
        slider.setMajorTickSpacing(10); //задает разметку на расстоянии n пикселей, но б
        slider.setPaintTrack(true);     //Делает эту разметку видимой

        //Цифры только под крупной разметкой
        slider.setPaintLabels(true);    //делает цифры

        slider.setFont(new Font("MV Boli", Font.PLAIN, 13));//задаем шрифт

        slider.setOrientation(SwingConstants.VERTICAL); //Делаем слайдер вертикальным

        label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        label.setText("°C = " + slider.getValue());


        panel.add(slider);
        panel.add(label);
        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setVisible(true);
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider)
        {
            label.setText("°C = " + slider.getValue());
        }
    }
}
