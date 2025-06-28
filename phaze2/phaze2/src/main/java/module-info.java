module org.example.phaze2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens org.example.phaze2 to javafx.fxml;
    exports org.example.phaze2;
    exports org.example.phaze2.controllers.sceneControllers;
    exports org.example.phaze2.model.constants;
    opens org.example.phaze2.model.constants to javafx.fxml;
    opens org.example.phaze2.controllers.sceneControllers to javafx.fxml;
    exports org.example.phaze2.model.agentsAndManagers;
    opens org.example.phaze2.model.agentsAndManagers to javafx.fxml;
    exports org.example.phaze2.model.jsonRefrencesAndLOadings to com.fasterxml.jackson.databind;
}