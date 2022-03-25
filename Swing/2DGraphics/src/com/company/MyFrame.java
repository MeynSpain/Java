package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyPanel panel;

    MyFrame(){

        panel = new MyPanel();
        this.setTitle("2D Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }


}
