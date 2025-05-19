module com.example.phaze1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires javafx.media;
    requires java.sql;


//    opens com.example.phaze1.controllers to javafx.fxml;
    opens com.example.phaze1        to javafx.fxml;
//    opens com.example.phaze1.Model to com.fasterxml.jackson.databind;
    exports com.example.phaze1;
    opens com.example.phaze1.Model.SystemsInfoAndManagers to com.fasterxml.jackson.databind;
    opens com.example.phaze1.Model.FormerVersionOSystems to com.fasterxml.jackson.databind;
    opens com.example.phaze1.Model.JSonManager to com.fasterxml.jackson.databind;
    opens com.example.phaze1.Model.levelLoadingStuff to com.fasterxml.jackson.databind;
    opens com.example.phaze1.Model.Agents to com.fasterxml.jackson.databind;
    opens com.example.phaze1.Model.Constants to com.fasterxml.jackson.databind;
    opens com.example.phaze1.Model.Tasks to com.fasterxml.jackson.databind;
    opens com.example.phaze1.controllers.sceneControllers to javafx.fxml;
}
