package org.example.phaze2.controllers.winAndPocketLoss;

import javafx.geometry.Point2D;
import org.example.phaze2.model.constants.GameState;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics.PocketTypeGroup;
import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.*;

public class PocketLossInWin {

    private final Map<String, Set<PocketMain>> pocketGroups;
    private final GameState gameState;

    // what we’ve already credited per group id
    private final Map<String, Integer> winCredited  = new HashMap<>();
    private final Map<String, Integer> lossCredited = new HashMap<>();

    public PocketLossInWin(GameState gameState) {
        this.gameState = gameState;
        this.pocketGroups = gameState.getResources().getPocketGroupIdGroups();
    }

    public synchronized int[] detectOutLeftOvers() {
        int lossSum = 0, winSum = 0;


        for (var e : pocketGroups.entrySet()) {
            String id = e.getKey();
            Set<PocketMain> group = e.getValue();


            if (!detectAccess(group)) continue;             // not finished yet

            int[] lw = WinAndLoss(group);                   // {lossTotal, winTotal} for this group

            int prevW = winCredited.getOrDefault(id, 0);
            int prevL = lossCredited.getOrDefault(id, 0);

            int dW = Math.max(0, lw[1] - prevW);
            int dL = Math.max(0, lw[0] - prevL);

            if (dW > 0 || dL > 0) {
                winSum  += dW;
                lossSum += dL;
                winCredited.put(id, prevW + dW);
                lossCredited.put(id, prevL + dL);
            }
        }
//        System.out.println(winSum);
        return new int[]{lossSum , winSum};
    }

    public synchronized void reset() {
        winCredited.clear();
        lossCredited.clear();
    }


    private synchronized int[] WinAndLoss(Set<PocketMain> group) {
        if (group.isEmpty()) return new int[]{0,0};



        int k = group.size();
        int sum = 0;

        for (PocketMain p : group) {
            int v = Math.max(0, p.getMaxHp());
//            System.out.println(v + " jdjdjdjdjww");
            sum += v;

        }

        int win = deliveredForGroup(group);






        int loss = Math.max(0, sum - win);
        return new int[]{win, loss};
    }
    public static int deliveredForGroup(Set<PocketMain> group) {
        if (group == null || group.isEmpty()) return 0;

        // Filter members (optional BIG-only)
        List<Integer> members = new ArrayList<>();
        for (PocketMain p : group) {
            members.add(p.getMaxHp());
        }

        final int k = members.size();
        // If any size is 0, product is 0 -> delivered = 0
        BigInteger prod = BigInteger.ONE;
        for (int v : members) {
            prod = prod.multiply(BigInteger.valueOf(v));
        }

        BigInteger kPowK = BigInteger.valueOf(k).pow(k);
        BigInteger combined = prod.multiply(kPowK);

        BigInteger root = kthRootFloor(combined, k);

        // Clamp to int just in case (extremely large values)
        return root.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
                ? Integer.MAX_VALUE
                : root.intValue();

    }

    private static BigInteger kthRootFloor(BigInteger x, int k) {
        if (k <= 0) throw new IllegalArgumentException("k must be >= 1");
        if (x.signum() <= 0) return BigInteger.ZERO;

        int b = x.bitLength();
        BigInteger lo = BigInteger.ZERO;
        BigInteger hi = BigInteger.ONE.shiftLeft((b + k - 1) / k);

        while (lo.compareTo(hi) < 0) {
            BigInteger mid = lo.add(hi).add(BigInteger.ONE).shiftRight(1); // ceil((lo+hi)/2)
            BigInteger midPow = mid.pow(k);
            if (midPow.compareTo(x) <= 0) lo = mid; else hi = mid.subtract(BigInteger.ONE);
        }
        return lo;
    }


    private boolean detectAccess(Set<PocketMain> group) {
        for (PocketMain p : group) if (!p.isDone()) return false;
        return true;
    }
}