package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    JButton button;
    JLabel label;

    MyFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(500,500);
        this.setLayout(new FlowLayout());

        button = new JButton("Pick a color");
        button.addActionListener(this);

        label = new JLabel();
        label.setBackground(Color.white);
        label.setText("this is some text");
        label.setFont(new Font("MV Boli", Font.ITALIC, 100));
        label.setOpaque(true);  //видимый задний фон

        this.add(button);
        this.add(label);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button)
        {
            JColorChooser colorChooser = new JColorChooser();
            //выведет цветовую палитру
            Color color = JColorChooser.showDialog(null, "pick a color", Color.BLACK);  //родитель, пример текста, начальный цвет
            //label.setForeground(color);
            label.setBackground(color);
        }

    }
}
