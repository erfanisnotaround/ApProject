package org.example.phaze2.controllers.abilityManagers.following;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.example.phaze2.model.abilities.mechanics.followers.Follower;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerFactory;
import org.example.phaze2.model.abilities.mechanics.followers.FollowerType;
import org.example.phaze2.model.abilities.modelingAbilities.GameContext;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.List;

public class FollowerAdder implements FollowerSpawner{

    private final Pane ContainerPane;
    private Follower SelectedFollower = null;
    private FollowerAbilityController followerAbilityController = new FollowerAbilityController();
    private GameContext gameContext;

    public FollowerAdder(Pane ContainerPane) {
        this.ContainerPane = ContainerPane;
    }


    @Override
    public void spawn(FollowerType followerType) {

        if (gameContext.getGameState().isWeAreAddingAbility()) return;
        Follower follower = FollowerFactory.createFollower(followerType);
        SelectedFollower = follower;


        follower.setFill(Color.WHITE);
        follower.setRadius(5);

        ContainerPane.getChildren().add(SelectedFollower);





    }

    public void FollowTheMouse(MouseEvent mouseEvent) {
        if (SelectedFollower != null) {
            SelectedFollower.setCenterX(mouseEvent.getX());
            SelectedFollower.setCenterY(mouseEvent.getY());
        }

    }
    @Override
    public void ReleaseFollower() {
        if (SelectedFollower == null) return;
        List<Connection> connections = Constants.getInstance().getConnections();

        Connection closestConnection = findClosestConnection(connections);

        followerAbilityController.AddFollower(SelectedFollower , closestConnection.getCurve());
        SelectedFollower = null;

        gameContext.getGameState().setWeAreAddingAbility(false);

        gameContext.getAliveManager().RemoveAbility(SelectedFollower.getFollowerType().getAbilityType());
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
    @Override
    public void SetGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }
    private GameContext getGameContext() {
        return gameContext;
    }


}
