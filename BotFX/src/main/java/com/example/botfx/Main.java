package com.example.botfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

//    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);

        stage.setTitle("Авторизация");

        Image icon = new Image("file:src\\main\\resources\\icon.png");
        stage.getIcons().add(icon);

        stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}