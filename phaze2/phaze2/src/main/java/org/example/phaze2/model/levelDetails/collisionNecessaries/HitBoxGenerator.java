package org.example.phaze2.model.levelDetails.collisionNecessaries;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypes;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.List;

public class HitBoxGenerator {
    public static HitBox generateHitBox(Image image) {
        HitBox hitBox = new HitBox();
        List<Point2D> boundaryPoints = new ArrayList<>();

        double threshold = 0.1;
        PixelReader pixelReader = image.getPixelReader();
        for (int y = 1; y < image.getHeight() - 1; y++) {
            for (int x = 1; x < image.getWidth() - 1; x++) {
                if (isOpaque(x, y, pixelReader, threshold) &&
                        isTransparentNeighbor(x, y, pixelReader, (int) image.getWidth(), (int) image.getHeight(), threshold)) {
                    boundaryPoints.add(new Point2D(x, y));
                }
            }
        }



        for (Point2D boundaryPoint : boundaryPoints) {
            hitBox.getPoints().addAll(boundaryPoint.getX(), boundaryPoint.getY());
        }

        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.WHITE );
        return hitBox;
    }
    private static boolean isOpaque(int x, int y, PixelReader reader, double threshold) {
        Color color = reader.getColor(x, y);
        return color.getOpacity() > threshold;
    }
    private static boolean isTransparentNeighbor(int x, int y, PixelReader reader, int width, int height, double threshold) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if (nx < 0 || ny < 0 || nx >= width || ny >= height) continue;

                Color neighborColor = reader.getColor(nx, ny);
                if (neighborColor.getOpacity() <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

}

