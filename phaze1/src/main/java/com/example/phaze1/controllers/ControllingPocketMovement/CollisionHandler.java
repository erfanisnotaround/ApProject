package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
    private double waveRangeEffect = 100;
    List<Pocket> pockets;
    public CollisionHandler(List<Pocket> pockets) {
        this.pockets = pockets;
    }
    public void SpreadImpact(Pocket firstPocket, Pocket secondPocket) {
        firstPocket.setHP(firstPocket.getHP() - 1);
        firstPocket.setDistanceFromTheLine(firstPocket.getDistanceFromTheLine() + firstPocket.getMaxDistanceFromTheLine()/firstPocket.getMaxHP());
        secondPocket.setHP(secondPocket.getHP() - 1);
        secondPocket.setDistanceFromTheLine(secondPocket.getDistanceFromTheLine() + secondPocket.getMaxDistanceFromTheLine()/secondPocket.getMaxHP());
        System.out.println(firstPocket.getHP() + " " + secondPocket.getHP());
        SpreadWave(firstPocket, secondPocket);
    }
    public void SpreadWave(Pocket firstPocket, Pocket secondPocket) {
        for (Pocket pocket : pockets) {
            if(pocket == firstPocket||pocket == secondPocket) continue;
            double X1 = pocket.getLayoutX() , Y1 = pocket.getLayoutY();
            double X2 = (firstPocket.getLayoutX()+secondPocket.getLayoutX())/2 , Y2 = (firstPocket.getLayoutY()+secondPocket.getLayoutY())/2;
            double distance = Math.hypot(X1 - X2, Y1 - Y2);
            reducingHp(distance , pocket);
        }
    }
    private void reducingHp(double distance , Pocket pocket) {
        if (distance > waveRangeEffect) return;
        pocket.setHP(pocket.getHP() - distance/waveRangeEffect);
        pocket.setDistanceFromTheLine(pocket.getDistanceFromTheLine() + distance/waveRangeEffect * pocket.getMaxDistanceFromTheLine());
    }
    public void reset() {
        for (Pocket pocket : pockets) {
            pocket.setHP(pocket.getMaxHP());
            pocket.setDistanceFromTheLine(0);
        }
    }
}
