module com.example.botfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
//    requires java.datatransfer;
    requires java.desktop;


    opens com.example.botfx to javafx.fxml;
    exports com.example.botfx;
}