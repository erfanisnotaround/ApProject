module com.example.phaze1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.phaze1 to javafx.fxml;
    exports com.example.phaze1;
}