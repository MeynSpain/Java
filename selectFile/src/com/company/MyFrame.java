package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame implements ActionListener {

    JButton button;

    MyFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(500,500);
        this.setLayout(new FlowLayout());

        button = new JButton("Select file");
        button.addActionListener(this);


        this.add(button);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == button)
        {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".")); //указываем путь, где изначально откроется диалоговое окно( "." - ткущая папка)

            //int response = fileChooser.showOpenDialog(null);   //выбираем файл для открытия    вернет 0, если открыл и 1, если нет
            int response = fileChooser.showSaveDialog(null);    //файл для сохранения
            if (response == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);   //выведет только путь к файлу
            }
        }

    }
}
