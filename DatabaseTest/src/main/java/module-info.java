module com.example.databasetest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.databasetest to javafx.fxml;
    exports com.example.databasetest;
}