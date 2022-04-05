package com.example.botfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class LoginController {

    @FXML
    private Button button_enter;

    @FXML
    private TextField textField_login;

    /**
     * Принимает текст написанный в textField (логин), и открывает окно чата
     * @param actionEvent
     */
    public void button_enter_clicked(ActionEvent actionEvent) {
        String name = textField_login.getText();        //Считываем имя пользователя
        name = name.trim().replaceAll("\\s+"," ");  //Убираем все первые и последние пробелы, и заменяем оставшиеся пробелы одним пробелом

        if (name.isEmpty())                             //Если имя пустое, то просто очищаем поле ввода
        {
            textField_login.clear();
        }
        else                                            //В противном случае открываем чат
        {

            button_enter.getScene().getWindow().hide();     //Скрываем окно авторизации

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("interface.fxml"));   //Подгружаем форму(интерфейс
            try {
                loader.load();



            } catch (IOException e) {
                e.printStackTrace();
            }

            //Ну и танцуем с бубном, чтобы открыть ее
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Чат бот Чаттер");
            stage.show();

            //Подгружаем иконку
            Image icon = new Image("file:src\\main\\resources\\icon.png");
            stage.getIcons().add(icon);

            //Передаем в новую форму имя пользователя
            Controller controller = loader.getController();
            controller.User = name;

            //Подгружаем историю сообщений
            controller.get_history();

            stage.setOnCloseRequest(controller.getCloseEventHandler());




        }
    }
}
