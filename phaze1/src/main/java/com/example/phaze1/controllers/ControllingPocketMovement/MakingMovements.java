package com.example.phaze1.controllers.ControllingPocketMovement;
import com.example.phaze1.Model.SystemsInfoAndManagers.*;
import com.example.phaze1.Model.Constants.constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class MakingMovements {
    Pane LineContainer;
    ArrayList<Pocket> Pockets = constants.getPockets();
    Map<Node, GatePortInfo> portInfo;
    Map<GatePortInfo , Connection> exitConnections;
    ArrayList<SystemView> systemViews;
    public SystemView startSystem;
    public MakingMovements(Pane LineContainer , ArrayList<SystemView> systemViews) {
        this.LineContainer = LineContainer;
        this.systemViews = systemViews;
    }
    public void goForPocketMovement() throws IOException {
        portInfo = constants.getPortInfo();
        exitConnections = constants.getExitConnections();
        startSystem = getStartSystem(systemViews);
        for (Pocket pocket : Pockets) {
            SendAPocket(pocket , startSystem);
        }
        initCurveListeners();
    }
    public void SendAPocket(Pocket pocket , SystemView systemView) {
        ArrayList<ViewOfSubSystem> PossibleChoices = givingPossibleChoices(systemView, pocket);
        System.out.println(PossibleChoices.size());
        if (!PossibleChoices.isEmpty()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(pocket.getDelay())));
            timeline.setCycleCount(1);
            timeline.play();
            timeline.setOnFinished(event -> {
                Random rand = new Random();
                int choiceIndex = rand.nextInt(PossibleChoices.size());
                ViewOfSubSystem FinalChoice = PossibleChoices.get(choiceIndex);
                GatePortInfo ChoiceGate = portInfo.get(FinalChoice.ExitPort);
                nowWeSendPockets(pocket , ChoiceGate);
            });
        } else {
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
                System.out.println("didi kir shodi");
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
                    conn.curve.isItUsed.addListener((obs, wasUsed, isNowUsed) -> {
                        if (!isNowUsed) {
                            conn.curve.isItUsed.set(trySendFromCapacity(sys , gp));
                        }
                    });
                }
                else {
                    System.out.println("didi kir shodi2");
                }
            }
        }
    }

    private boolean trySendFromCapacity(SystemView sys, GatePortInfo gate) {
        for (int i = 0 ; i < sys.capacity.length; i++) {
            Pocket p = sys.capacity[i];
            if (p!=null && p.getType() == gate.type) {
                sys.capacity[i] = null;
                nowWeSendPockets(p , gate);
                return true;
            }
        }
        return false;
    }


    public void nowWeSendPockets(Pocket pocket , GatePortInfo gate) {
        Connection connection = exitConnections.get(gate);
        if (connection != null) {
            connection.curve.isItUsed.set(true);
            connection.curve.makeMovementOnThis(pocket , connection , 5);
            resume(pocket, connection);
        }
    }
    public void resume(Pocket pocket , Connection connection) {
        if (!connection.to.system.isItStartSystem){
            connection.curve.isItUsed.addListener((obs, wasUsed, isNowUsed) -> {
                if (!isNowUsed) {
                    pocket.setDelay(0.002);
                    SendAPocket(pocket , connection.to.system);
                }
            });
        }
        else {
            System.out.println("you win");
        }
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
