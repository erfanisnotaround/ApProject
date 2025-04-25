module com.example.phaze1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.example.phaze1.controllers to javafx.fxml;
    opens com.example.phaze1        to javafx.fxml;
    exports com.example.phaze1;
}
