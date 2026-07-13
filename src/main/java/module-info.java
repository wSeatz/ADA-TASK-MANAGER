module com.example.ada1fp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.ada1fp to javafx.fxml;
    exports com.example.ada1fp;
    exports com.example.ada1fp.controllers;
    opens com.example.ada1fp.controllers to javafx.fxml;

    opens com.example.ada1fp.models to org.junit.jupiter;
    exports com.example.ada1fp.models;
}