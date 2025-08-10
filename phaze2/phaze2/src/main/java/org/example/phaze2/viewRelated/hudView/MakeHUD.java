package org.example.phaze2.viewRelated.hudView;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import org.example.phaze2.model.abilities.AbilityTypes;

import java.util.HashMap;
import java.util.Map;

public class MakeHUD {
    private Pane HUD_Pane;
    Map<Integer , AbilityLabels> abilityLabelsMap = new HashMap<>();
    Map<Integer , Label> normalLabelsMap = new HashMap<>();
    int numberOfNormalLabels;
    int numberAbilityLabels;

    AbilityTypes[] abilities =AbilityTypes.values();

    String[] texts = {
            "Coins : 0",
            "Wire left : 0",
            "PocketLoss : 0",
            "Ability1",
            "Ability2",
            "Ability3",
            "Ability4",
            "Ability5",
            "Ability6"
    };
    public MakeHUD(Pane HUDPane , int numberOfNormalLabels, int numberAbilityLabels) {
        this.HUD_Pane = HUDPane;
        this.numberOfNormalLabels = numberOfNormalLabels;
        this.numberAbilityLabels = numberAbilityLabels;
    }
    public void makeHUD() {

        HUD_Pane.getChildren().clear();

        double cellW = 300, cellH = 233;
        double fontSize = 27;


        for (int i = 0; i < numberOfNormalLabels; i++) {
            Label lbl = new Label(texts[i]);
            lbl.setFont(Font.font(fontSize));
            lbl.setAlignment(Pos.CENTER);
            lbl.setPrefSize(cellW, cellH);

            normalLabelsMap.put(i + 1, lbl);
            int col = i % 3, row = i / 3;
            SetPositionAndStyle("horrifying-label1" ,lbl ,new Point2D(col * cellW , row * cellH));

            HUD_Pane.getChildren().add(lbl);
        }

        for (int i = 0; i < numberAbilityLabels; i++) {
            AbilityLabels lbl = new AbilityLabels( abilities[i] ,texts[i + 3]);
            lbl.setFont(Font.font(fontSize));
            lbl.setAlignment(Pos.CENTER);
            lbl.setPrefSize(cellW, cellH);

            int index = i + 3;
            int col = index % 3, row = index / 3;
            SetPositionAndStyle("horrifying-label2" ,lbl ,new Point2D(col * cellW , row * cellH));

            abilityLabelsMap.put(i , lbl);
            HUD_Pane.getChildren().add(lbl);
        }



    }

    private void SetPositionAndStyle(String style , Label label , Point2D point) {
        label.setLayoutX(point.getX());
        label.setLayoutY(point.getY());

        label.getStyleClass().add(style);
    }
    public Map<Integer , AbilityLabels> getAbilityLabelsMap() {
        return abilityLabelsMap;
    }
    public Map<Integer , Label> getNormalLabelsMap() {
        return normalLabelsMap;
    }
}
