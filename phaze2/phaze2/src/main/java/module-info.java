module org.example.phaze2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires java.sql;


    opens org.example.phaze2 to javafx.fxml;
    exports org.example.phaze2;
    exports org.example.phaze2.controllers.sceneControllers;
    exports org.example.phaze2.model.constants;
    opens org.example.phaze2.model.constants to javafx.fxml;
    opens org.example.phaze2.controllers.sceneControllers to javafx.fxml;
    exports org.example.phaze2.model.agentsAndManagers;
    opens org.example.phaze2.model.agentsAndManagers to javafx.fxml;
    exports org.example.phaze2.model.jsonRefrencesAndLOadings to com.fasterxml.jackson.databind;
    exports org.example.phaze2.model.levelDetails.pocketTypesAndBehavior to com.fasterxml.jackson.databind;
    opens org.example.phaze2.model.jsonRefrencesAndLOadings to com.fasterxml.jackson.databind;
    opens org.example.phaze2.model.levelDetails to com.fasterxml.jackson.databind;
    exports org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes to com.fasterxml.jackson.databind;
    exports org.example.phaze2.model.levelDetails.necessary to com.fasterxml.jackson.databind;
    exports org.example.phaze2.controllers.moverController.moveRelated to com.fasterxml.jackson.databind;
    exports org.example.phaze2.controllers.moverController.checkings to com.fasterxml.jackson.databind;
    exports org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics to com.fasterxml.jackson.databind;
    opens org.example.phaze2.model.levelDetails.necessary to com.fasterxml.jackson.databind;
    exports org.example.phaze2.model.levelSavesAndTheirPojo to com.fasterxml.jackson.databind;
    exports org.example.phaze2.model.portConnectingDetails to com.fasterxml.jackson.databind;

}