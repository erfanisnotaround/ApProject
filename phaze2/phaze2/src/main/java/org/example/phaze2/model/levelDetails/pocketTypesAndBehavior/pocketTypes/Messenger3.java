package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

public class Messenger3 extends Pocket implements Movable {
    public Messenger3(PocketTypes type) {
        super(type);
        setCoinsPerEntry(1);
    }

    @Override
    public void move(Curve curve) {

    }
}
