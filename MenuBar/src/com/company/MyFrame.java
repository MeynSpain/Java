package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame implements ActionListener {
    //создаем панель меню
    JMenuBar menuBar;
    //создаем разделы меню
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    //Создаем выбор из выпадающего списка в раздела File
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    ImageIcon saveIcon;
    ImageIcon loadIcon;
    ImageIcon exitIcon;

    MyFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(new FlowLayout());

        //Картиночки под пункты меню
        saveIcon = new ImageIcon("src/save1.png");
        loadIcon = new ImageIcon("src/load.png");
        exitIcon = new ImageIcon("src/exit.png");


        menuBar = new JMenuBar();  //создаем панель меню
        //создаем разделы меню
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
        //Создаем выбор из выпадающего списка в раздела File
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        //Создаем событие под клик на эти item-ы
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        //добавляем картиночки к меню
        saveItem.setIcon(saveIcon);
        loadItem.setIcon(loadIcon);
        exitItem.setIcon(exitIcon);


        fileMenu.setMnemonic(KeyEvent.VK_F);    //alt + f
        editMenu.setMnemonic(KeyEvent.VK_E);    //alt + e
        helpMenu.setMnemonic(KeyEvent.VK_H);    //alt + h
        //При нажатии клавиш срабатывают пункты меню, но для этого нужно раскрыть его
        loadItem.setMnemonic(KeyEvent.VK_L);    //l - нажать
        saveItem.setMnemonic(KeyEvent.VK_S);    //s - нажать
        exitItem.setMnemonic(KeyEvent.VK_E);    //e - нажать

        //Добавляем их в раздел
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        //Добавляем разделы на панель
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);



        this.setLocationRelativeTo(null);




        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem)
        {
            System.out.println("*beep boop* you loaded a file");
        }
        if (e.getSource() == saveItem)
        {
            System.out.println("*beep boop* you saved a file");
        }
        if (e.getSource() == exitItem)
        {
            System.exit(0);
        }

    }
}
