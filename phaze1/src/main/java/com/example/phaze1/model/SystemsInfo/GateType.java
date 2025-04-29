package com.example.phaze1.model.SystemsInfo;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public enum GateType {
    SQUARE{
        @Override
        public void setParent(SystemView parent) {
            Parent = parent;
        }

        @Override
        public void setParentID(String parentID) {
            this.ParentID = parentID;
        }

        @Override
        public Shape createShape() {
            Rectangle r = new Rectangle(5,5);
            r.setFill(Color.BLUEVIOLET);
            return r;
        }
    },
    TRIANGLE {
        @Override
        public void setParent(SystemView parent) {
            Parent = parent;
        }

        @Override
        public void setParentID(String parentID) {
            this.ParentID = parentID;
        }
        @Override
        public Shape createShape() {
            Polygon p = new Polygon(
                    5 * 0.5, 0.0,   // top middle
                    5,      5,   // bottom right
                    0.0,       5    // bottom left
            );

            p.setFill(Color.ORANGERED);
            return p;
        }
    };
    SystemView Parent;
    public abstract void setParent(SystemView parent);
    String ParentID;
    public abstract void setParentID(String parentID);
    public abstract Shape createShape();

}
