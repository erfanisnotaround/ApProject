package org.example.phaze2.model.abilities.mechanics.followers;

import org.example.phaze2.controllers.moverController.moveRelated.PathData;

public class FollowerFactory {
    public static Follower createFollower(FollowerType followerType) {
        return switch (followerType) {
            case FollowerType.Acceleration_zero_Maker -> new ZeroAccelerationMaker(followerType);
            case FollowerType.LineDistance_Zero_Maker -> new ZeroLIneDistanceMaker(followerType);
        };
    }
}
