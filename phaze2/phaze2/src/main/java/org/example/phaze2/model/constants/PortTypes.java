package org.example.phaze2.model.constants;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public enum PortTypes {
    TRIANGLE {
        @Override
        public Shape createShape() {
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(
                    0.0, 0.0,   // Point 1
                    50.0, 100.0, // Point 2
                    100.0, 0.0   // Point 3
            );
            return triangle;
        }
    },
    SQUARE {
        @Override
        public Shape createShape() {
            Polygon square = new Polygon();
            square.getPoints().addAll(
                    0.0, 0.0,
                    100.0, 0.0,
                    100.0, 100.0,
                    0.0, 100.0
            );
            return square;
        }
    },
    INFINITY {
        @Override
        public Shape createShape() {
            double centerX = 50;
            double centerY = 50;
            double radius = 50;
            double verticalGap = 20;
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

            return shape;
        }
    };

    public abstract Shape createShape();
}
