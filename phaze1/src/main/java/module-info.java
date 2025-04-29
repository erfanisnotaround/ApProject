module com.example.phaze1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.example.phaze1.controllers to javafx.fxml;
    opens com.example.phaze1        to javafx.fxml;
    opens com.example.phaze1.Model to com.fasterxml.jackson.databind;
    exports com.example.phaze1;
    opens com.example.phaze1.Model.SystemsInfo to com.fasterxml.jackson.databind;
    opens com.example.phaze1.Model.FormerVersionOSystems to com.fasterxml.jackson.databind;
}
