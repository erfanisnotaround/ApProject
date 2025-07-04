package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes;

import org.example.phaze2.model.levelDetails.Curve;
import org.example.phaze2.model.levelDetails.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.Movable;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.PocketTypes;

public class SecretMessenger extends Pocket implements Movable {

    public SecretMessenger(PocketTypes type) {
        super(type);
        setCoinsPerEntry(5);
    }

    @Override
    public void move(Curve curve) {

    }
}
