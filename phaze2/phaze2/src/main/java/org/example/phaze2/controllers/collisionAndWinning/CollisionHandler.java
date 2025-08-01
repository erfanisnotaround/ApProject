package org.example.phaze2.controllers.collisionAndWinning;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import org.example.phaze2.model.constants.Constants;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.List;

public class CollisionHandler {
    private final double waveRangeEffect = 100;
    private final double basicMultiplier = 1;
    private final int UnitOfNoisePerImpact = 1;
    private final double theUnitOfMeasuring = waveRangeEffect * basicMultiplier;
    List<PocketMain> pockets;
    public CollisionHandler() {
        this.pockets = Constants.getInstance().getPockets();
    }
    public void SpreadImpact(CollisionPair pair) {
        PocketMain firstPocket = pair.getFirstPocket();
        PocketMain secondPocket = pair.getSecondPocket();
        Point2D impactPoint = pair.getImpactPoint();

        Point2D FirstImpactVector = getImpactVector(impactPoint , firstPocket.centre());
        Point2D SecondImpactVector = getImpactVector(impactPoint , secondPocket.centre());
        ApplyImpactVector(firstPocket, FirstImpactVector);
        ApplyImpactVector(secondPocket, SecondImpactVector);

        reducingHp(0 , firstPocket);
        reducingHp(0 , secondPocket);
        SpreadWave(firstPocket, secondPocket , impactPoint);


    }
    private void SpreadWave(PocketMain firstPocket, PocketMain secondPocket , Point2D impactPoint) {
        for (PocketMain pocket : pockets) {

            if (pocket.equals(firstPocket) || pocket.equals(secondPocket)) continue;


            Point2D impactVector = getImpactVector(impactPoint , pocket.centre());
            double size = Math.hypot(impactVector.getX() , impactVector.getY());
            if ( size == 0) continue;
            reducingHp(size , firstPocket);
            ApplyImpactVector(pocket, impactVector);

        }
    }
    private void reducingHp(double impactVectorSize , PocketMain pocket) {
        double realSize = impactVectorSize * (1 / basicMultiplier);
        double HpMultiplier = (waveRangeEffect - realSize) / waveRangeEffect;

        pocket.setHP(pocket.getHP() - UnitOfNoisePerImpact * HpMultiplier);

    }
    private Point2D getImpactVector(Point2D impactPoint , Point2D destination ) {
        Point2D impactVector = destination.subtract(impactPoint);
        double length = Math.hypot(impactVector.getX(), impactVector.getY());

        if ( length > waveRangeEffect ) return new Point2D( 0 , 0);
        double FirstMultiplier = (waveRangeEffect - length) / waveRangeEffect;
        double SecondMultiplier = FirstMultiplier * basicMultiplier;

        impactVector = impactVector.multiply(SecondMultiplier);
        return impactVector;
    }

    private void ApplyImpactVector(PocketMain pocket , Point2D impactVector) {

        Platform.runLater(() -> {
            pocket.distract(impactVector.getX(), impactVector.getY());
        });

    }

    public void reset() {
        for (PocketMain pocket : pockets) {
            pocket.setHP(pocket.getMaxHp());
            pocket.setDistanceFromTheLine(0);
        }
    }
}
