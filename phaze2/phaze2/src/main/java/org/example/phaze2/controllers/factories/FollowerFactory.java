package org.example.phaze2.controllers.factories;

import org.example.phaze2.model.abilities.mechanics.followers.Follower;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerType;
import org.example.phaze2.model.abilities.mechanics.followers.ZeroAccelerationMaker;
import org.example.phaze2.model.abilities.mechanics.followers.ZeroLIneDistanceMaker;

public class FollowerFactory {
    public static Follower createFollower(FollowerType followerType) {
        return switch (followerType) {
            case FollowerType.Acceleration_zero_Maker -> new ZeroAccelerationMaker(followerType);
            case FollowerType.LineDistance_Zero_Maker -> new ZeroLIneDistanceMaker(followerType);
        };
    }
}
