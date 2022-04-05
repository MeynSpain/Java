module com.example.botfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;


    opens com.example.botfx to javafx.fxml;
    exports com.example.botfx;
}