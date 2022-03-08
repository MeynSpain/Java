module com.example.vector_interface {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vector_interface to javafx.fxml;
    exports com.example.vector_interface;
}