package com.example.databasetest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DB_Interface.fxml"));

        fxmlLoader.setResources(ResourceBundle.getBundle("Bundles.Locale", new Locale("ru")));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(fxmlLoader.getResources().getString("key.Enterprise"));
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image("file:src\\main\\resources\\icons\\mainIcon.png"));
        Controller controller = new Controller();
        controller.setMainStage(stage);

    }

    public static void main(String[] args) {
        launch();
    }
}
