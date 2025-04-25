package com.example.phaze1.model;
public class WireManager {
    private double maxLength;
    private double usedLength = 0;

    public WireManager(double maxLength) {
        this.maxLength = maxLength;
    }

    public boolean canUse(double length) {
        return usedLength + length <= maxLength;
    }

    public void addWire(double length) {
        usedLength += length;
    }

    public void removeWire(double length) {
        usedLength -= length;
    }

    public double remaining() {
        return maxLength - usedLength;
    }
}
