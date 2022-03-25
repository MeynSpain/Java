package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;

    Image enemy;    //картинка пришельца
    Image backgroundImage;  //задник
    Timer timer;    //таймер, чтобы пришелец двигался сам

    int xVelocity = 10;  //скорость по х
    int yVelocity = 3;  //скорость по y

    //координаты пришельца
    int x = 0;
    int y = 0;

    MyPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);

        enemy = new ImageIcon("src/enemy.png").getImage();
        backgroundImage = new ImageIcon("src/space.jpg").getImage();

        timer = new Timer(10,this);
        timer.start();


    }

    public void paint(Graphics g){

        super.paint(g); //рисуем задник
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(backgroundImage,0,0,null);    //задник
        g2D.drawImage(enemy, x, y, null);   //рисуем нашего инопланетянина
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(x >= PANEL_WIDTH - enemy.getWidth(null) || x < 0)
        {
            xVelocity *= -1;
        }

        if(y >= PANEL_HEIGHT - enemy.getHeight(null) || y < 0)
        {
            yVelocity *= -1;
        }

        x = x + xVelocity;
        y = y + yVelocity;
        repaint();  //повторный вызов метода paint

    }
}
