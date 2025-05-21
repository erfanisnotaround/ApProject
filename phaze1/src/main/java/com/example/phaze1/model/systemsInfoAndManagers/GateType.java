package com.example.phaze1.model.systemsInfoAndManagers;
import javafx.scene.paint.Color;

public enum GateType {
    SQUARE{
        @Override
        public double getPocketHp() {
            return 4;
        }

        @Override
        public Pocket createShape() {
            double size = 5.0;
            Pocket square = new Pocket(
                    0.0, 0.0,
                    5.0, 0.0,
                    5.0, 5.0,
                    0.0, 5.0
            );
            square.setFill(Color.BLUEVIOLET);
            return square;
        }
    },
    TRIANGLE {
        @Override
        public double getPocketHp() {
            return 3;
        }

        @Override
        public Pocket createShape() {
            Pocket p = new Pocket(
                    5 * 0.5, 0.0,   // top middle
                    5,      5,   // bottom right
                    0.0,       5    // bottom left
            );
            p.setFill(Color.DARKORCHID);
            return p;
        }
    };
    public abstract double getPocketHp();
    public abstract Pocket createShape();

}
