import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_for_Bot extends JFrame implements ActionListener
{
    //4 константы
    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot";  //Заголовок окна
    final int START_LOCATION = 200;     //Стартовая позиция окна
    final int WINDOW_WIDTH = 350;       //Ширина окна
    final int WINDOW_HEIGHT = 450;      //Высота окна

    //Графические объекты, которые будут внутри формы
    JTextArea textArea_dialogue;
    JCheckBox checkBox_ai;
    JTextField textField_message;
    SimpleBot sbot;

    GUI_for_Bot()
    {
        //Настраиваем форму
        setTitle(TITLE_OF_PROGRAM);                         //Заголовок
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //Чтобы программа закрывалась
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);               //Размеры формы
        setResizable(false);                                //Делаем форму не изменяемым
        setLocationRelativeTo(null);                        //Устанавливаем положение по центру
        getRootPane().setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));   //отступы всех элементов от краев формы

        //Создаем дилоговое окно, в котором виден чат
        textArea_dialogue = new JTextArea();                //Выделяем память(создаем)
        textArea_dialogue.setLineWrap(true);                //Перенос строки
        JScrollPane scrollBar = new JScrollPane(textArea_dialogue); //Добавляем ползунок, в него оборачиваем чат
        textArea_dialogue.setEditable(false);               //запрещаем писать в textArea

        //Создаем нижнюю панель с полем ввода и кнопкой
        JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(350,30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));    //Распологаем все элементы панели по оси X

        //Создаем поле ввода
        textField_message = new JTextField();               //Выделяем поле ввода
        textField_message.addActionListener(this);       //Создаем событие при нажатии Enter на клавиатуре

        //Создаем кнопку ввода
        JButton button_enter = new JButton("Enter");   //Создаем кнопку
        button_enter.addActionListener(this);            //Создаем событие нажатия на кнопку

        //Добавляем элементы на панель в нужном нам порядке
        panel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));  //отступы внутри панели
        panel.add(textField_message);
        panel.add(Box.createHorizontalStrut(1));   //горизонтальный разделитель между элементами(spacer, что типа этого)
        panel.add(button_enter);


        //Добавляем элементы уже на саму форму
        add(BorderLayout.CENTER, scrollBar);     //добавляем именно скролбар, потому что внутри него лежит дилоговое окно
        add(BorderLayout.SOUTH,panel);         //добавляем панель внутри которой лежат поле ввода и кнопка

        //Поиграюсь с цветами
        textArea_dialogue.setBackground(Color.darkGray);
        textArea_dialogue.setForeground(Color.WHITE);

        textField_message.setBackground(Color.darkGray);
        textField_message.setForeground(Color.WHITE);

        button_enter.setBackground(Color.darkGray);
        button_enter.setForeground(Color.WHITE);


        sbot = new SimpleBot();

        setVisible(true);   //Делаем форму видимой
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (textField_message.getText().trim().length() > 0)        ///Берем поле ввода, берем из него текст, (trim) - убираем все пробелы, и смотрим чтобы длина текста была больще 0
        {
            textArea_dialogue.append(textField_message.getText() + "\n");
//            textArea_dialogue.setSelectedTextColor(Color.red);
            textArea_dialogue.setForeground(Color.red);

            textArea_dialogue.setOpaque(true);
            textArea_dialogue.append("Bot: ");
            textArea_dialogue.repaint();
//            textArea_dialogue.setSelectedTextColor(Color.WHITE);
            textArea_dialogue.setForeground(Color.white);
            textArea_dialogue.append(sbot.answer(textField_message.getText()) + "\n");
        }
        textField_message.setText("");
    }
}
