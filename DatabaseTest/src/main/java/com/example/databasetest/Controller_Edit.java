package com.example.databasetest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_Edit {

    @FXML
    private Button button_edit;

    @FXML
    private TextField textField_bank;

    @FXML
    private TextField textField_contact;

    @FXML
    private TextField textField_name;

    private Enterpise enterpise;


    void setEnterpise(Enterpise enterpise)
    {
        this.enterpise = enterpise;
        textField_name.setText(enterpise.name);
        textField_bank.setText(enterpise.banking_details);
        textField_contact.setText(enterpise.contact_person);
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public Enterpise getEnterpise() {
        return enterpise;
    }

    @FXML
    void button_edit_click(ActionEvent actionEvent)
    {
        //Получаем данные и убираем лишние пробелы
        String name = textField_name.getText().trim().replaceAll("\\s+", " ");
        String bank = textField_bank.getText().trim().replaceAll("\\s+", " ");
        String contact = textField_contact.getText().trim().replaceAll("\\s+", " ");

        //Если строки не пустые, то добавляем
        if (!name.isEmpty() && !bank.isEmpty() && !contact.isEmpty())
        {
            enterpise.name = name;
            enterpise.banking_details = bank;
            enterpise.contact_person = contact;
            System.out.println("внутри :" + enterpise);
        }

        actionClose(actionEvent);
    }

}