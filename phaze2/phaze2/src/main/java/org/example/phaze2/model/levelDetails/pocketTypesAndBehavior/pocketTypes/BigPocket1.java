package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

import java.awt.*;

public class BigPocket1 extends Pocket implements Movable {
    Image image = new Image(getClass().getResource("/org/example/phaze2/images/horns.png").toExternalForm());

    public BigPocket1(PocketTypes type) {
        super(type);
        setImage(image);
        setScaleX(0.05);
        setScaleY(0.05);
        pathMover = new PathMover(this);
    }


    @Override
    public void move(Curve curve) {
        pathMover.move(curve , 100 , 50 , true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2300), event -> {
        }));
        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(e -> {
//            pathMover.restart(-100);
            pathMover.reverse();
        });
    }
}
