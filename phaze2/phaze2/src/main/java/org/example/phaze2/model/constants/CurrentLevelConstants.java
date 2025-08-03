package org.example.phaze2.model.constants;

public class CurrentLevelConstants {
    private static final CurrentLevelConstants Instance = new CurrentLevelConstants();

    private final double WidthOfSubSystems = 80;
    private final double width = 100;
    private final double UpperHeight = 20;
    private final double lightBarWidth = 30;
    private final double lightBarHeight = 10;
    private final double LightRadius = 5;
    private final double HeightOfSubSystems = 60;
    private final double distanceOFRightForSubSystems = 10;
    private final double gapOFBottom = 5;
    private final double NameLabelX = 60;
    private final double NameLabelY = 2;


    private CurrentLevelConstants() {}
    public static CurrentLevelConstants getInstance() {
        return Instance;
    }

    public double getWidthOfSubSystems() {
        return WidthOfSubSystems;
    }

    public double getWidth() {
        return width;
    }

    public double getUpperHeight() {
        return UpperHeight;
    }

    public double getLightBarWidth() {
        return lightBarWidth;
    }

    public double getLightBarHeight() {
        return lightBarHeight;
    }

    public double getHeightOfSubSystems() {
        return HeightOfSubSystems;
    }

    public double getDistanceOFRightForSubSystems() {
        return distanceOFRightForSubSystems;
    }

    public double getGapOFBottom() {
        return gapOFBottom;
    }

    public double getNameLabelX() {
        return NameLabelX;
    }

    public double getNameLabelY() {
        return NameLabelY;
    }

    public double getLightRadius() {
        return LightRadius;
    }
}
