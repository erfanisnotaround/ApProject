package org.example.phaze2.model.constants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public enum PortTypes {
    TRIANGLE {
        @Override
        public Shape createShape() {
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(
                    5.0, 5.0,
                    5.0, 0.0,
                    0.0, 0.0
            );
            triangle.setRotate(315);
            triangle.setScaleX(2);
            triangle.setScaleY(2);
            triangle.setFill(Color.RED);
            return triangle;
        }
    },
    SQUARE {
        @Override
        public Shape createShape() {
            Polygon square = new Polygon();
            square.getPoints().addAll(
                    0.0, 0.0,
                    5.0, 0.0,
                    5.0, 5.0,
                    0.0, 5.0
            );
            square.setScaleX(2);
            square.setScaleY(2);
            square.setFill(Color.BLUE);
            return square;
        }
    },
    INFINITY {
        @Override
        public Shape createShape() {
            double centerX = 0;
            double centerY = 0;
            double radius = 5;
            double verticalGap = 5;
            Polygon shape = new Polygon();

            for (int i = 0; i < 6; i++) {
                double angle = Math.toRadians(60 * i - 30);
                double x = centerX + radius * Math.cos(angle);
                double y = centerY - verticalGap + radius * Math.sin(angle);
                shape.getPoints().addAll(x, y);
            }

            shape.getPoints().addAll(centerX + radius / 2, centerY);
            shape.getPoints().addAll(centerX - radius / 2, centerY);

            for (int i = 0; i < 6; i++) {
                double angle = Math.toRadians(60 * i - 30);
                double x = centerX + radius * Math.cos(angle);
                double y = centerY + verticalGap + radius * Math.sin(angle);
                shape.getPoints().addAll(x, y);
            }
            shape.setScaleX(1);
            shape.setScaleY(1);
            shape.setFill(Color.GREEN);
            return shape;
        }
    },
    ALL {
        @Override
        public Shape createShape() {
            return null;
        }
    };


    public abstract Shape createShape();
}
