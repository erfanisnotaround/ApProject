package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics;

import org.example.phaze2.controllers.moverController.moveRelated.Movable;
import org.example.phaze2.controllers.moverController.moveRelated.Releasable;
import org.example.phaze2.model.levelDetails.necessary.Pocket;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.*;

public class PocketMoveFactory {
    public static Movable givePocketMovementType(PocketTypes pocketType) {
        return switch (pocketType) {
            case Messenger_1 -> new Messenger1(pocketType);
            case Messenger_2 -> new Messenger2(pocketType);
            case Messenger_3 -> new Messenger3(pocketType);
            case SECRET_MESSENGER -> new SecretMessenger(pocketType);
            case SECRET_1 -> new SecretPocket1(pocketType);
            case SECRET_2 -> new SecretPocket2(pocketType);
            case BIG_1 -> new BigPocket1(pocketType);
            case BIG_2 -> new BigPocket2(pocketType);
        };
    }
    public static Releasable givePocketReleaseType(PocketTypes pocketType) {
        return switch (pocketType) {
            case Messenger_1 -> new Messenger1(pocketType);
            case Messenger_2 -> new Messenger2(pocketType);
            case Messenger_3 -> new Messenger3(pocketType);
            case SECRET_MESSENGER -> new SecretMessenger(pocketType);
            case SECRET_1 -> new SecretPocket1(pocketType);
            case SECRET_2 -> new SecretPocket2(pocketType);
            case BIG_1 -> new BigPocket1(pocketType);
            case BIG_2 -> new BigPocket2(pocketType);
        };
    }


    public static Pocket giveType(PocketTypes pocketType) {
        return switch (pocketType) {
            case Messenger_1 -> new Messenger1(pocketType);
            case Messenger_2 -> new Messenger2(pocketType);
            case Messenger_3 -> new Messenger3(pocketType);
            case SECRET_MESSENGER -> new SecretMessenger(pocketType);
            case SECRET_1 -> new SecretPocket1(pocketType);
            case SECRET_2 -> new SecretPocket2(pocketType);
            case BIG_1 -> new BigPocket1(pocketType);
            case BIG_2 -> new BigPocket2(pocketType);
        };
    }


}
