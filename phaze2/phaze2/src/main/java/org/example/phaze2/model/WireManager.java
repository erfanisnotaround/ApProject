package org.example.phaze2.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class WireManager {
    private double maxLength;
    private double usedLength = 0;
    private DoubleProperty usedLengthProperty = new SimpleDoubleProperty(0);

    public WireManager(double maxLength) {
        usedLengthProperty.set(maxLength);
        this.maxLength = maxLength;
    }
    public boolean canUse(double length) {
        return usedLength + length <= maxLength;
    }

    public void addWire(double length) {
        usedLengthProperty.set(usedLengthProperty.get() - length);
        usedLength += length;
    }

    public void removeWire(double length) {
        usedLength -= length;
        usedLengthProperty.set(usedLengthProperty.get() + length);
    }

    public double remaining() {
        return maxLength - usedLength;
    }

    public DoubleProperty usedLengthPropertyProperty() {
        return usedLengthProperty;
    }
}
