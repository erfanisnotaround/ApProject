package com.example.phaze1.controllers.controllingPocketMovement;
import com.example.phaze1.model.systemsInfoAndManagers.*;
import com.example.phaze1.model.constants.constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class MakingMovements {
    ArrayList<Timeline> timelines = constants.getTimeLines();
    private CollisionsDetection collisionsDetector;
    Pane LineContainer;
    ArrayList<Pocket> Pockets = constants.getPockets();
    Map<Node, GatePortInfo> portInfo;
    Map<GatePortInfo , Connection> exitConnections;
    ArrayList<SystemView> systemViews;
    public SystemView startSystem;
    public MakingMovements(Pane LineContainer , ArrayList<SystemView> systemViews) {
        this.LineContainer = LineContainer;
        this.systemViews = systemViews;
        collisionsDetector = new CollisionsDetection(Pockets);
    }

    public void test(){
        portInfo = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        initCurveListeners();
    }
    public Pocket PocketReset(Pocket pocket , double speed , double availableTime) {
        pocket.setCoins(0);
        pocket = pocket.getDefaultPocket();
        pocket.setSpeed(speed);
        pocket.setAvailableTime(availableTime);
        return pocket;
    }
    public void  wholeReset(Map<GatePortInfo , Connection> exitConnections) {
        for (Connection c : exitConnections.values()) {
            c.curve.isItUsed.set(false);
        }
        for (SystemView sv : systemViews) {
            Arrays.fill(sv.capacity , null);
        }
        for (Timeline timeline : constants.getTimeLines()) {
            timeline.stop();
        }
        constants.getTimeLines().clear();
        collisionsDetector.reset();
    }
    public void goForPocketMovement(double speed , double availableTime) throws IOException {
        startSystem = getStartSystem(systemViews);
        wholeReset(exitConnections);
        for (int i = 0 ; i < Pockets.size() ; i++) {
            Pockets.set(i , PocketReset(Pockets.get(i), speed , availableTime));
            Pocket p = Pockets.get(i);
            System.out.println(p.getAvailableTime());
            SendAPocket(p , startSystem);
        }

    }
    public void detectCollisions() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1) , event -> {
            collisionsDetector.checkCollisions();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timelines.add(timeline);
        timeline.play();
    }
    public void SendAPocket(Pocket pocket , SystemView systemView) {
        if (pocket.isLastRound())return;
        double delay = pocket.getDelay();
        if (pocket.getDelay() >=0.1){
            delay = delay/pocket.getSpeed();
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delay)));
        timeline.setCycleCount(1);
        timeline.play();
        timelines.add(timeline);
        timeline.setOnFinished(event -> {
            pocket.setAvailableTime(pocket.getAvailableTime() - pocket.getDelay());
            ArrayList<ViewOfSubSystem> PossibleChoices = givingPossibleChoices(systemView, pocket);
            ArrayList<ViewOfSubSystem> secondPossibleChoices = secondChoices(systemView , pocket);
            if (!PossibleChoices.isEmpty()) {
                pocket.setInTheGame(true);
                Random rand = new Random();
                int choiceIndex = rand.nextInt(PossibleChoices.size());
                ViewOfSubSystem FinalChoice = PossibleChoices.get(choiceIndex);
                GatePortInfo ChoiceGate = portInfo.get(FinalChoice.ExitPort);
                nowWeSendPockets(pocket , ChoiceGate);
            }
            else if (!secondPossibleChoices.isEmpty()) {
                pocket.setInTheGame(true);
                Random rand = new Random();
                int choiceIndex = rand.nextInt(secondPossibleChoices.size());
                ViewOfSubSystem FinalChoice = secondPossibleChoices.get(choiceIndex);
                GatePortInfo ChoiceGate = portInfo.get(FinalChoice.ExitPort);
                nowWeSendPockets(pocket , ChoiceGate);
            }
            else {
                pocket.setDelay(0.002);
                int emptyIndex = -1;
                for (int i = 0; i < systemView.capacity.length; i++) {
                    if (systemView.capacity[i] == null && !containsPocket(pocket , systemView)) {
                        emptyIndex = i;
                        break;
                    }
                }
                if (emptyIndex != -1) {
                    systemView.capacity[emptyIndex] = pocket;
                } else {
                    System.out.println("");
                }
            }

        });


    }
    public boolean containsPocket(Pocket pocket , SystemView systemView) {
        for (Pocket p : systemView.capacity) {
            if (p == pocket) {
                return true;
            }
        }
        return false;
    }
    public void initCurveListeners() {
        for (SystemView sys : systemViews) {
            for (ViewOfSubSystem sub : sys.SubSystems) {
                if (!sub.doesItHavaExitGate) continue;
                GatePortInfo gp   = portInfo.get(sub.ExitPort);
                Connection   conn = exitConnections.get(gp);
                if (conn != null) {
                    conn.curve.isItUsed.addListener((obs, was, isNow) -> {
                        if (!isNow) {
                            trySendFromCapacity(sys, gp , sub);
                        }
                    });
                }
            }
        }
    }


    private void trySendFromCapacity(SystemView sys, GatePortInfo gate , ViewOfSubSystem sub) {

        for (int i = 0 ; i < sys.capacity.length; i++) {
            Pocket p = sys.capacity[i];
            if (p!=null && p.getType() == gate.type) {
                SendAPocket(p , sys);
                sys.capacity[i] = null;
                return;
            }
            else if (p!=null) {
                SendAPocket(p , sys);
                sys.capacity[i] = null;
                return;
            }
        }
    }


    public void nowWeSendPockets(Pocket pocket, GatePortInfo gate) {
        Timeline pause = new Timeline(new KeyFrame(Duration.millis(1)));
        pause.setOnFinished(event -> {
            detectCollisions();
        });
        pause.setCycleCount(1);
        pause.play();
        timelines.add(pause);
        Connection connection = exitConnections.get(gate);
        if (connection == null) return;
        connection.curve.isItUsed.set(true);
        resume(pocket, connection);
        connection.curve.makeMovementOnThis(pocket, connection, 5);
    }

    public void resume(Pocket pocket, Connection connection) {

        if (connection.to.system.isItStartSystem) {
            pocket.setLastRound(true);
        }

        BooleanProperty used = connection.curve.isItUsed;
        ChangeListener<Boolean> oneShot = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs,
                                Boolean wasUsed, Boolean isNowUsed) {
                if (!isNowUsed) {
                    used.removeListener(this);
                    pocket.setDelay(0.002);
                    SendAPocket(pocket, connection.to.system);
                }
            }
        };
        used.addListener(oneShot);
    }


    public ArrayList<ViewOfSubSystem> givingPossibleChoices(SystemView ParentSystem , Pocket pocket){
        ArrayList<ViewOfSubSystem> possibleChoices = new ArrayList<>();
        for (ViewOfSubSystem subSystem : ParentSystem.SubSystems) {
            if (subSystem.doesItHavaExitGate && subSystem.ExitGate == pocket.getType()){
                Connection connection = exitConnections.get(portInfo.get(subSystem.ExitPort));
                if (connection!=null&&!connection.curve.isItUsed.get()){
                    possibleChoices.add(subSystem);
                }
            }
        }
        return possibleChoices;
    }
    public ArrayList<ViewOfSubSystem> secondChoices(SystemView ParentSystem , Pocket pocket){
        ArrayList<ViewOfSubSystem> possibleChoices = new ArrayList<>();
        for (ViewOfSubSystem subSystem : ParentSystem.SubSystems) {
            if (subSystem.doesItHavaExitGate){
                Connection connection = exitConnections.get(portInfo.get(subSystem.ExitPort));
                if (connection!=null&&!connection.curve.isItUsed.get()){
                    possibleChoices.add(subSystem);
                }
            }
        }
        return possibleChoices;
    }
    public SystemView getStartSystem(ArrayList<SystemView> systemViews) {
        for (SystemView systemView : systemViews) {
            if (systemView.isItStartSystem){
                return systemView;
            }
        }
        return systemViews.getFirst();

    }
}