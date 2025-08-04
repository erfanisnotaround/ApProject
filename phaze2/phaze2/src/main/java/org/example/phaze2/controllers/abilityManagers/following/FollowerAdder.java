package org.example.phaze2.controllers.abilityManagers.following;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.phaze2.controllers.moverController.moveRelated.PathData;
import org.example.phaze2.model.abilities.mechanics.followers.Follower;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerFactory;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerType;
import org.example.phaze2.model.constants.AbilityBooleansConstants;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class FollowerAdder {

    private Follower SelectedFollower = null;
    private FollowerAbilityController followerAbilityController = new FollowerAbilityController();

    public void AddFollower(FollowerType followerType) {

        Follower follower = FollowerFactory.createFollower(followerType);
        SelectedFollower = follower;

        AbilityBooleansConstants.getInstance().setWeAreAddingAbility(false);

    }
    public void FollowTheMouse(MouseEvent mouseEvent) {
        if (SelectedFollower != null) {
            SelectedFollower.setCenterX(mouseEvent.getX());
            SelectedFollower.setCenterY(mouseEvent.getY());
        }
    }
    public void ReleaseFollower() {
        if (SelectedFollower == null) return;
        List<Connection> connections = Constants.getInstance().getConnections();

        Connection closestConnection = findClosestConnection(connections);

        followerAbilityController.AddFollower(SelectedFollower , closestConnection.getCurve());
    }
    private Connection findClosestConnection(List<Connection> connections) {
        Point2D pointOfSelectedFollower = new Point2D(SelectedFollower.getCenterX(), SelectedFollower.getCenterY());
        Connection closest = connections.getFirst();
        double MinDistance = closest.getCurve().getPathData().distanceTo(pointOfSelectedFollower);
        for (Connection connection : connections) {
            if (connection == closest) continue;
            double distance = connection.getCurve().getPathData().distanceTo(pointOfSelectedFollower);
            if (distance < MinDistance) {
                closest = connection;
                MinDistance = distance;
            }
        }
        return closest;
    }


}
