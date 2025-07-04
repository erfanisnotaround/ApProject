package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

public class BigPocket2 extends Pocket implements Movable {
    public BigPocket2(PocketTypes type) {
        super(type);
        setCoinsPerEntry(10);
    }

    @Override
    public void move(Curve curve) {

    }
}
