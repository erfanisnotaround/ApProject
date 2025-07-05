package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import javafx.scene.image.Image;
import org.example.phaze2.controllers.moverController.PathMover;
import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

public class BigPocket2 extends Pocket implements Movable {
    Image image = new Image(getClass().getResourceAsStream("/org/example/phaze2/images/vagabond.png"));
    public BigPocket2(PocketTypes type) {
        super(type);
        setLayoutX(200);
        setLayoutY(200);
        setCoinsPerEntry(10);
        setImage(image);
        setScaleX(0.01);
        setScaleY(0.01);
        pathMover = new PathMover(this);
    }

    @Override
    public void move(Curve curve) {
        pathMover.move(curve , 100 , 50 , false);
    }
}
