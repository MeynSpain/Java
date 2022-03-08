package com.example.vector_interface;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_vector {

    private Vector_int vector = new Vector_int();

    private boolean mistake = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_add;

    @FXML
    private Button button_clear;

    @FXML
    private Button button_erase;

    @FXML
    private Button button_pop_back;

    @FXML
    private Button button_resize;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField_Add;

    @FXML
    private TextField textField_Resize;

    @FXML
    private TextField textField_count;

    @FXML
    private TextField textField_position;



    @FXML
    void initialize() {

    }

    @FXML   //Клик по кнопке создать/добавить элементы в массив
    public void button_add_clicked(ActionEvent e) {
        String str = textField_Add.getText();

        //Ищем все кроме цифр и пробелов
        Pattern pattern = Pattern.compile("[^\\d^\\s]");
        Matcher matcher = pattern.matcher(str);


        if (matcher.find())     //проверям нашли ли
        {
            textArea.setText("Надо бы цифорки вводить, а не буквы и какие то знаки, ты свои крокозябры сам обрабатывай!");
            mistake = true;
        }
        else
        {
            //Парсим строку и делаем массив
            Parser parser = new Parser();
            vector = parser.Pars(str, vector);
            textArea.clear();
            print_vector();
        }



    }

    @FXML   //Resize
    public void button_resize_clicked(ActionEvent e) {
        vector.resize(Integer.parseInt(textField_Resize.getText()));
        textArea.clear();
        print_vector();
    }

    @FXML   //Clear
    public void button_clear_clicked(ActionEvent actionEvent) {
        vector.clear();
        textArea.clear();
    }

    @FXML   //Pop_back
    public void button_pop_back_clicked(ActionEvent actionEvent) {
        try {
            vector.pop_back();
            textArea.clear();
            print_vector();
        }catch (ArithmeticException e) {
            textArea.setText(e.getMessage());
        }

    }

    @FXML   //Erase
    public void button_erase_clicked(ActionEvent actionEvent) {
        int index = Integer.parseInt(textField_position.getText());
        int count = Integer.parseInt(textField_count.getText());
        try {
            vector.erase(index, count);
            textArea.clear();
            print_vector();

        } catch (ArithmeticException e) {
            textArea.setText(e.getMessage());
        }
    }

    public void print_vector()  //Вывод массива на экран
    {
        for (int i = 0; i < vector.get_size(); i++)     //вывод на экран
        {
            textArea.setText(textArea.getText() + vector.get(i) + " ");
        }
    }





}
