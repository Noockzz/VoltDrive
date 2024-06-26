module com.example.aula {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.aula to javafx.fxml;
    exports com.example.aula;
}