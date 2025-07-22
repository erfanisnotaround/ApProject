package org.example.phaze2.model.levelDetails.pocketTypesAndBehavior.mechanics;

import java.util.ArrayList;
import java.util.List;

public enum PocketTypeGroup {
    MESSENGER(PocketTypes.Messenger_1 , PocketTypes.Messenger_3 , PocketTypes.Messenger_2),
    SECRET(PocketTypes.SECRET_1 , PocketTypes.SECRET_2),
    BIG(PocketTypes.BIG_1 , PocketTypes.BIG_2),;

    private List<PocketTypes> groups = new ArrayList<>();

    PocketTypeGroup(PocketTypes pocket1 , PocketTypes pocket2 , PocketTypes pocket3) {
        this.groups.add(pocket1);
        this.groups.add(pocket2);
        this.groups.add(pocket3);
    }
    PocketTypeGroup(PocketTypes pocket1 , PocketTypes pocket2 ) {
        this.groups.add(pocket1);
        this.groups.add(pocket2);
    }
    public List<PocketTypes> getGroups() {
        return groups;
    }
}
