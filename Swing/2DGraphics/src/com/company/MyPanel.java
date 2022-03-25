package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MyPanel extends JPanel {

    Image image;
    MyPanel(){
        image = new ImageIcon("src/fon.png").getImage();
        this.setPreferredSize(new Dimension(500,500));
    }

    public void paint(Graphics g){

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(image,0,0,null);  //Вставка картинки(в самом начале, чтобы была как фон)

        g2D.setPaint(Color.BLUE);                     //Цвет линии
        g2D.setStroke(new BasicStroke(5));      //Толщина линии
        g2D.drawLine(0,0,500,500);

        g2D.setPaint(Color.PINK);                     //Цвет линии
        //g2D.drawRect(0,0,100,200);  //Прямоугольник
        g2D.fillRect(0,0,100,100);  //Закрашенный прямоугольник,(верхняя строчка не нужна)

        g2D.setPaint(Color.ORANGE);                     //Цвет линии
        g2D.drawOval(100,100,100,100);//Эллипс
        g2D.fillOval(100,100,100,100);//закрашенный эллипс

        g2D.drawArc(200,200,100,100,0,90);//часть эллипса(x,y,длина,ширина,start, длина(последние 2 в градусах))
        g2D.fillArc(200,200,100,100,60,90);//тоже самое, только закрашенное(можно делать пиццу)

        int[] xPoints = {150,250,350};
        int[] yPoints = {300,150,300};
        g2D.drawPolygon(xPoints,yPoints,3);//рисует по точкам(массив Х-в , массив Y-в, кол-во точек) в данном случе треугольник
        g2D.fillPolygon(xPoints,yPoints,3);//тоже самое, но закращенное

        g2D.setColor(Color.magenta);
        g2D.setFont(new Font("MV Boli", Font.ITALIC,50));
        g2D.drawString("You're a Winner!", 70, 50);//Строка, и ее координаты


    }
}
