package com.example.phaze1.model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public enum GateType {
    SQUARE{
        @Override
        public Shape createShape() {
            Rectangle r = new Rectangle(5,5);
            r.setFill(Color.LIGHTBLUE);
            return r;
        }
    },
    TRIANGLE {
        @Override
        public Shape createShape() {
            Polygon p = new Polygon(
                    5 * 0.5, 0.0,   // top middle
                    5,      5,   // bottom right
                    0.0,       5    // bottom left
            );
//            p.setRotate(-30);
//            if (enterGate){
//                p.setRotate(270);
//            }
            p.setFill(Color.ORANGERED);
            return p;
        }
    };


    public abstract Shape createShape();

}
