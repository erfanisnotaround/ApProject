package com.example.phaze1.Model.SystemsInfoAndManagers;
import javafx.scene.paint.Color;

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
        public Pocket createShape() {
            double size = 5.0;
            Pocket square = new Pocket(
                    0.0, 0.0,      // top-left
                    5.0, 0.0,     // top-right
                    5.0, 5.0,    // bottom-right
                    0.0, 5.0      // bottom-left
            );
            square.setFill(Color.BLUEVIOLET);
            return square;
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
        public Pocket createShape() {
            Pocket p = new Pocket(
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
    public abstract Pocket createShape();

}
