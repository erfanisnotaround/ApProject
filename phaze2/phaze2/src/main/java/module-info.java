module org.example.phaze2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.phaze2 to javafx.fxml;
    exports org.example.phaze2;
}