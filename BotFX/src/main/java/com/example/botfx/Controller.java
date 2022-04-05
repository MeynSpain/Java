package com.example.botfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.WindowEvent;

import java.util.List;
import java.util.Scanner;


public class Controller {

    String User;
    private SimpleBot bot = new SimpleBot();
    private List<DataMessage> history;

    @FXML
    private Button buttonInput;

    @FXML
    private TextArea textArea_dialogue;

    @FXML
    private TextField textField_input;


    public void get_history()
    {
        DataBase dataBase = new DataBase();
        history = dataBase.DB_get_history_messages();
        for (DataMessage dataMessage: history)
        {
            textArea_dialogue.appendText(dataMessage.date + "\n" +
                                           dataMessage.author + ": " + dataMessage.message + "\n\n");
        }
    }

    public void send_history()
    {
        DataBase dataBase = new DataBase();
        dataBase.DB_Insert(bot);
    }


    public void button_click(ActionEvent actionEvent) {
        /*
        Text text1 = new Text("Пример вот так странный пример");
        text1.setFill(Color.RED);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40));
        Text text2 = new Text("Малый жирный синий текст");
        text2.setFill(Color.BLUE);
        text2.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
        //Соединение этих двух кусков
        TextFlow textFlow = new TextFlow(text1, text2);
        System.out.println(text1.getText());
//        textArea_dialogue.setText(text1.);

         */

        //Читаем сообщение и очищаем строку
        String message = textField_input.getText();
        textField_input.clear();
//        DataBase dataBase = new DataBase();
//        dataBase.DB_clear();

        if (message.trim().length() > 0)    //Проверяем не пустая ли строка
        {
            //Печатаем наше сообщение в диалоговое окно
            textArea_dialogue.appendText(bot.getDate() + "\n" +
                                            User + ": " + message + "\n\n");
            //И добавляем ответ бота
            textArea_dialogue.appendText(bot.getDate() + "\n" +
                                            "Чаттер: " + bot.answer(message, User) + "\n\n");



        }

    }


    /**
     * Нужно для выполнения каких либо действий после закрытия формы
     */
    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {
        /**
         * Вызывается соединение с базой данных и загрузка истории
         * сообщений
         */
        @Override
        public void handle(WindowEvent event) {
            send_history();
        }
    };

    /**
     * @return Как раз возвращаем метод при закрытии программы
     */
    public javafx.event.EventHandler<WindowEvent> getCloseEventHandler(){
        return closeEventHandler;
    }
}
