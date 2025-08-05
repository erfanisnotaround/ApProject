package org.example.phaze2.controllers.shopController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.phaze2.controllers.abilityManagers.abilityKeys.ShopButton;
import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.constants.SceneActions;

import java.util.ArrayList;
import java.util.List;

public class ShopAdder {

    private final Stage mini = new Stage(StageStyle.UTILITY);
    private final VBox  items = new VBox(8);
    private final List<ShopButton> buttons = new ArrayList<>();

    public ShopAdder() {

        items.setPadding(new Insets(12));
        items.setStyle("""
            -fx-background-color: #292929;
            -fx-border-color: #5e5e5e;
            -fx-border-radius: 6;
            -fx-background-radius: 6;
        """);


        for (AbilityTypes type : AbilityTypes.values()) {
            String text = type.name();
            ShopButton btn = new ShopButton(text, type);
            btn.setPrefWidth(140);
            items.getChildren().add(btn);
            buttons.add(btn);
        }
    }


    public  Stage addStage(Stage owner, Pane actionController) {

        Scene miniScene = new Scene(items);
        mini.setScene(miniScene);

        mini.initStyle(StageStyle.UNDECORATED);


        mini.initOwner(owner);
        mini.initModality(Modality.NONE);
        mini.setResizable(false);
        mini.setTitle("Shop");





        return mini;
    }

    public List<ShopButton> getItems() {
        return buttons;
    }
}
