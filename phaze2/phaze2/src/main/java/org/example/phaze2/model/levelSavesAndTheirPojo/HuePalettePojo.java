package org.example.phaze2.model.levelSavesAndTheirPojo;

import java.util.HashMap;
import java.util.Map;

public class HuePalettePojo {
    private double nextHue;
    private Map<String, Double> hues = new HashMap<>();

    public double getNextHue() {
        return nextHue;
    }

    public void setNextHue(double nextHue) {
        this.nextHue = nextHue;
    }

    public Map<String, Double> getHues() {
        return hues;
    }

    public void setHues(Map<String, Double> hues) {
        this.hues = hues;
    }
}
