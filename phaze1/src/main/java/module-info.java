module com.example.phaze1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires javafx.media;
    requires java.sql;


    opens com.example.phaze1        to javafx.fxml;
    exports com.example.phaze1;
    opens com.example.phaze1.model.systemsInfoAndManagers to com.fasterxml.jackson.databind;
    opens com.example.phaze1.model.formerVersionOSystems to com.fasterxml.jackson.databind;
    opens com.example.phaze1.model.jsonManager to com.fasterxml.jackson.databind;
    opens com.example.phaze1.model.levelLoadingStuff to com.fasterxml.jackson.databind;
    opens com.example.phaze1.model.agents to com.fasterxml.jackson.databind;
    opens com.example.phaze1.model.constants to com.fasterxml.jackson.databind;
    opens com.example.phaze1.model.tasks to com.fasterxml.jackson.databind;
    opens com.example.phaze1.controllers.sceneControllers to javafx.fxml;
}
