package com.example.phaze1.controllers.controllingPocketMovement;
import com.example.phaze1.model.constants.SceneActions;
import com.example.phaze1.model.constants.Constants;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class shopStage extends Stage {
    private Constants constants = Constants.getInstance();

    public shopStage(Label coinsLabel) {
        initStyle(StageStyle.UNDECORATED);
        initOwner(constants.getPrimaryStage());
        initModality(Modality.NONE);
        setTitle("Shop");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #fafafa, #e0e0e0);"
        );

        Button OTar = new Button("O' Atar");
        Button OArya = new Button("O’ Airyaman");
        Button OAna   = new Button("O' Anahita");

        styleButton(OArya);
        styleButton(OAna);
        styleButton(OTar);

        OTar.setOnAction(e -> {
            Abilities.OATar.Benefit(Integer.parseInt(coinsLabel.getText()) , coinsLabel);
        });
        OArya.setOnAction(e -> {
            Abilities.OAiryaman.Benefit(Integer.parseInt(coinsLabel.getText()) , coinsLabel);

        });
        OAna.setOnAction(e -> {
            Abilities.OAnahita.Benefit(Integer.parseInt(coinsLabel.getText()) , coinsLabel);
        });

        root.getChildren().addAll(OAna , OArya , OTar);

        Scene scene = new Scene(root, 280, 180);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == SceneActions.CloseShop.getKeyCode()) {
                close();
                constants.setStopped(false);
            }
        });
        setScene(scene);
    }

    private void styleButton(Button btn) {
        btn.setFont(Font.font("Segoe UI", 14));
        btn.setPrefWidth(240);
        btn.setPadding(new Insets(8, 12, 8, 12));
        btn.setStyle(
                "-fx-background-color: #4a90e2;"
                        + "-fx-text-fill: white;"
                        + "-fx-background-radius: 6;"
        );
        DropShadow ds = new DropShadow(8, Color.gray(0, 0.3));
        btn.setEffect(ds);

        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,  e ->
                btn.setStyle(
                        "-fx-background-color: #357ab8;"
                                + "-fx-text-fill: white;"
                                + "-fx-background-radius: 6;"
                )
        );
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,   e ->
                btn.setStyle(
                        "-fx-background-color: #4a90e2;"
                                + "-fx-text-fill: white;"
                                + "-fx-background-radius: 6;"
                )
        );
    }

    public void OpenShop() {
        constants.setStopped(true);  // pause game
        show();
    }
}
