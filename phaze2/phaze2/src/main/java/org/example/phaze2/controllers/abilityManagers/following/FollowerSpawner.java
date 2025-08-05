package org.example.phaze2.controllers.abilityManagers.following;

import org.example.phaze2.model.abilities.mechanics.followers.FollowerType;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;

public interface FollowerSpawner {
    void spawn(FollowerType followerType);
    void ReleaseFollower();
}
