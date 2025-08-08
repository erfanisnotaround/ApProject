package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.splitting;

import javafx.scene.effect.ColorAdjust;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.concurrent.ThreadLocalRandom;

public class HueShiftEffect implements PocketVisualEffect {
    private double nextHue = ThreadLocalRandom.current().nextDouble();
    @Override
    public void apply(PocketMain p, String groupId) {
        ColorAdjust fx = new ColorAdjust();
        fx.setHue((nextHue * 2) - 1);           // map 0‑1 → −1…+1
        nextHue += 0.18; if (nextHue > 1) nextHue -= 1;
        p.setEffect(fx);
    }
}
