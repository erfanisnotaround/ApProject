package com.example.phaze1.controllers.ControllingPocketMovement;
import com.example.phaze1.Model.SystemsInfoAndManagers.*;
import com.example.phaze1.Model.Constants.constants;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
    private Timeline Collisiontimeline;
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
    public void reset(Map<GatePortInfo, Connection> exitConnections , double availableTime) {
        for (Pocket p : Pockets) {
            p.setHP(p.getMaxHP());
            p.setDistanceFromTheLine(0);
            p.setInTheGame(false);
            p.setAvailableTime(availableTime);
        }
        if (Collisiontimeline != null) Collisiontimeline.stop();
        for(SystemView sv : systemViews) {
            Arrays.fill(sv.capacity , null);
        }
        collisionsDetector.reset();
        for (Connection c : exitConnections.values()) {
            c.curve.isItUsed.set(false);
        }
    }
    public void goForPocketMovement(double speed , double availableTime) throws IOException {
        portInfo = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        startSystem = getStartSystem(systemViews);
        initCurveListeners();
        reset(exitConnections, availableTime);
        for (Pocket pocket : Pockets) {
            System.out.println(pocket.getAvailableTime());
            pocket.setSpeed(speed);
            SendAPocket(pocket , startSystem);
        }

    }
    public void detectCollisions() {
         Collisiontimeline = new Timeline(new KeyFrame(Duration.millis(5) , event -> {
            collisionsDetector.checkCollisions();
        }));
        Collisiontimeline.setCycleCount(Timeline.INDEFINITE);
        Collisiontimeline.play();
    }
    public void SendAPocket(Pocket pocket , SystemView systemView) {
        ArrayList<ViewOfSubSystem> PossibleChoices = givingPossibleChoices(systemView, pocket);
        if (!PossibleChoices.isEmpty()) {
            pocket.setInTheGame(true);
            Random rand = new Random();
            int choiceIndex = rand.nextInt(PossibleChoices.size());
            ViewOfSubSystem FinalChoice = PossibleChoices.get(choiceIndex);
            GatePortInfo ChoiceGate = portInfo.get(FinalChoice.ExitPort);
            nowWeSendPockets(pocket , ChoiceGate);
        } else {
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
        }
    }


    public void nowWeSendPockets(Pocket pocket, GatePortInfo gate) {
        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event -> {
            detectCollisions();
        });
        pause.play();
        Connection connection = exitConnections.get(gate);
        if (connection == null) return;
        connection.curve.isItUsed.set(true);
        connection.curve.makeMovementOnThis(pocket, connection, 5);
        resume(pocket, connection);
    }

    public void resume(Pocket pocket, Connection connection) {
        if (connection.to.system.isItStartSystem) {
            System.out.println("you win");
            return;
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
    public SystemView getStartSystem(ArrayList<SystemView> systemViews) {
        for (SystemView systemView : systemViews) {
            if (systemView.isItStartSystem){
                return systemView;
            }
        }
        return systemViews.getFirst();

    }
}
