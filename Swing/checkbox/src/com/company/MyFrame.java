package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    private JButton button = new JButton();
    private JCheckBox checkBox;
    private ImageIcon ok_icon;
    private ImageIcon no_icon;

    MyFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        ok_icon = new ImageIcon("src/ok.png");
        no_icon = new ImageIcon("src/no.png");

        button.setText("submit");
        button.addActionListener(this);

        JCheckBox checkBox = new JCheckBox();
        checkBox.setText("I'm not a robot");
        checkBox.setFocusable(false);
        checkBox.setFont(new Font("Consolas", Font.ITALIC, 20));
        checkBox.setIcon(no_icon);
        checkBox.setSelectedIcon(ok_icon);







        this.add(checkBox);
        this.add(button);
        this.pack();
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button)
        {
            //System.out.println(checkBox.isSelected());
        }
    }
}
