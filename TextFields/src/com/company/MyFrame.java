package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    private JButton button = new JButton("Submit");
    private JTextField textField = new JTextField();

    MyFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());


        button.addActionListener(this);


        textField.setPreferredSize(new Dimension(250,40));  //размер
        textField.setFont(new Font("Consolas", Font.ITALIC, 20));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.DARK_GRAY);
        textField.setCaretColor(Color.BLUE);
        textField.setText("Username");
        textField.setEditable(false);       //Нельзя писать




        this.add(button);
        this.add(textField);

        this.pack();
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button)
        {
            System.out.println(textField.getText());
        }
    }
}
