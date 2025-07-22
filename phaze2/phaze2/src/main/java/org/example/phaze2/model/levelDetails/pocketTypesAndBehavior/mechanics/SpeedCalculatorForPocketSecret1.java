package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics;

import org.example.phaze2.controllers.moverController.moveRelated.PathMover;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.necessary.Curve;
import org.example.phaze2.model.levelDetails.necessary.Port;
import org.example.phaze2.model.levelDetails.necessary.SubSystemView;
import org.example.phaze2.model.levelDetails.necessary.SystemView;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.SecretPocket1;
import org.example.phaze2.model.portConnectingDetails.Connection;

import java.util.Map;

public class SpeedCalculatorForPocketSecret1{


    private SecretPocket1 pocketSecret1;
    private PathMover pathMover;

    private Map<Port, Connection> connections = Constants.getInstance().getExitConnections();
    public SpeedCalculatorForPocketSecret1(SecretPocket1 pocketMain) {
        this.pocketSecret1 = pocketMain;
        this.pathMover = pocketMain.getPathMover();
    }



    public double calculateSpeed(SystemView systemView) {
        double wholeTimeRequired = 0;



        wholeTimeRequired += TImeInSystem(systemView);

        wholeTimeRequired +=OutGoingPocketsToSystem(systemView);



        double distanceRemains = pocketSecret1.getPathMover().distanceRemains();


        return distanceRemains / wholeTimeRequired;

    }
    private double TImeInSystem(SystemView systemView ) {
        Connection longestExitConnection = getLongestConnection(systemView);
        double wholeTimeInSystem = 0;
        for (PocketMain pocketMainInCapacity : systemView.getCapacity()){
            if (pocketMainInCapacity != null) wholeTimeInSystem += APocketTime(pocketMainInCapacity , longestExitConnection);

        }
        return wholeTimeInSystem;
    }

    private double OutGoingPocketsToSystem(SystemView systemView) {
        double minimumTimeGoingPockets = 0;
        boolean FirstOption = true;
        for (SubSystemView subSystemView : systemView.getSubSystems()){
            if (subSystemView.DoesItHavaExitGate()){
                Connection ExitConnection = connections.get(subSystemView.getExitPort());
                PocketMain pocketMovingOnCurve = ExitConnection.getCurve().getPocketMovingOnIt();
                if (pocketMovingOnCurve != null){
                    double TimeRemain = APocketTime(pocketMovingOnCurve , ExitConnection);
                    if (FirstOption){
                        minimumTimeGoingPockets = TimeRemain;
                        FirstOption = false;
                        continue;
                    }

                    minimumTimeGoingPockets = Math.min(minimumTimeGoingPockets, TimeRemain);
                }
            }
        }
        return minimumTimeGoingPockets;
    }
    private Connection getLongestConnection(SystemView systemView) {
        double MaxLength = 0;
        Connection longestConnection = new Connection(null , null , null);
        for (SubSystemView subSystemView : systemView.getSubSystems()) {
            if (subSystemView.DoesItHavaExitGate()){
                Connection connection = connections.get(subSystemView.getExitPort());
                Curve curve = connection.getCurve();

                double length = curve.ApproximateLength();
                if (length > MaxLength) {
                    longestConnection = connection;
                    MaxLength = length;
                }
            }
        }
        return longestConnection;
    }
    private double APocketTime(PocketMain pocket, Connection connection){

        double SpeedMultiplier = pocket.getSpeed();
        double constant = connection.getCurve().ApproximateLength();
        if (pocket.isIsItMoved() && connection.getCurve().getPocketMovingOnIt() != null){
            constant = pocket.getPathMover().distanceRemains();
            SpeedMultiplier = pocket.getPathMover().getSpeed();
        }

        return constant / SpeedMultiplier;
    }
    public double defaultSpeed(){
        return pocketSecret1.getSpeed();
    }



}
