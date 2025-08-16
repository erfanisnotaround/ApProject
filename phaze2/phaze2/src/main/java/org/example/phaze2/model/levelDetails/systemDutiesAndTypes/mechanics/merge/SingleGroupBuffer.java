package org.example.phaze2.model.levelDetails.systemDutiesAndTypes.mechanics.merge;

import org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.pocketTypes.PocketMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleGroupBuffer implements GroupStash {
    private final PocketMain[] slots;
    private String groupId;

    public SingleGroupBuffer(int capacity) {
        this.slots = new PocketMain[capacity];
    }

    @Override
    public boolean isEmpty() {
        for (PocketMain s : slots) if (s != null) return false;
        return true;
    }

    @Override
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public int size() {
        int count = 0;
        for (PocketMain s : slots) if (s != null) count++;
        return count;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    @Override
    public boolean isThere(PocketMain pocket) {
        for (PocketMain s : slots) if (s != null && s.equals(pocket)) return true;

        return false;
    }

    @Override
    public PocketMain[] getSlot() {
        return slots;
    }

    @Override
    public void reset() {
        groupId = null;
        Arrays.fill(slots, null);
    }

    @Override
    public boolean accept(PocketMain pocket) {

        String gid = pocket.getGroupId();
        if (gid == null) return false;
        if (isEmpty()) groupId = gid;
        if (!gid.equals(groupId)) return false;

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                slots[i] = pocket;
                return true;
            }
        }
        return false;
    }

    @Override
    public List<PocketMain> take(int count) {
        List<PocketMain> taken = new ArrayList<>(count);
        for (int i = 0; i < slots.length && taken.size() < count; i++) {
            if (slots[i] != null) {
                taken.add(slots[i]);
                slots[i] = null;
            }
        }
        if (size() == 0) groupId = null;
        return taken;
    }
}

