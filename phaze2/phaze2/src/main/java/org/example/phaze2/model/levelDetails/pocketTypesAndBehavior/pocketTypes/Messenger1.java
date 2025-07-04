package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

public class Messenger1 extends Pocket implements Movable {

    public Messenger1(PocketTypes type) {
        super(type);
        setCoinsPerEntry(2);
    }

    @Override
    public void move(Curve curve) {

    }
}
