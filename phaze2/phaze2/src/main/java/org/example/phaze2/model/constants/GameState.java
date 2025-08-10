package org.example.phaze2.model.constants;

public final class GameState {
    private volatile boolean movingSystemsAvailable = false;
    private volatile boolean weAreAddingAbility = false;
    private volatile boolean CanWeSpreadWave = true;
    private volatile boolean DoesCollideCounts = true;
    private volatile boolean AddingANchorAvailable = false;
    private volatile Resources resources = new Resources();
    private volatile VisualConstant visualConstant = new VisualConstant();
    private volatile MergerConfig mergerConfig = new MergerConfig();
    private volatile PocketWinAndLoss pocketWinAndLoss = new PocketWinAndLoss();
    private volatile HUDStuffDAta hudStuffDAta = new HUDStuffDAta();

    public boolean isMovingSystemsAvailable() {
        return movingSystemsAvailable;
    }

    public void setMovingSystemsAvailable(boolean movingSystemsAvailable) {
        this.movingSystemsAvailable = movingSystemsAvailable;
    }

    public boolean isWeAreAddingAbility() {
        return weAreAddingAbility;
    }

    public void setWeAreAddingAbility(boolean weAreAddingAbility) {
        this.weAreAddingAbility = weAreAddingAbility;
    }
    public void setCanWeSpreadWave(boolean canWeSpreadWave) {
        this.CanWeSpreadWave = canWeSpreadWave;
    }
    public boolean CanWeSpreadWave() {
        return CanWeSpreadWave;
    }
    public void setDoesCollideCounts(boolean doesCollideCounts) {
        this.DoesCollideCounts = doesCollideCounts;
    }
    public boolean DoesCollideCounts() {
        return DoesCollideCounts;
    }

    public Resources getResources() {
        return resources;
    }

    public boolean isAddingANchorAvailable() {
        return AddingANchorAvailable;
    }

    public void setAddingANchorAvailable(boolean addingANchorAvailable) {
        AddingANchorAvailable = addingANchorAvailable;
    }

    public VisualConstant getVisualConstant() {
        return visualConstant;
    }


    public MergerConfig getMergerConfig() {
        return mergerConfig;
    }

    public void setMergerConfig(MergerConfig mergerConfig) {
        this.mergerConfig = mergerConfig;
    }

    public PocketWinAndLoss getPocketWinAndLoss() {
        return pocketWinAndLoss;
    }

    public void setPocketWinAndLoss(PocketWinAndLoss pocketWinAndLoss) {
        this.pocketWinAndLoss = pocketWinAndLoss;
    }

    public HUDStuffDAta getHudStuffDAta() {
        return hudStuffDAta;
    }

    public void setHudStuffDAta(HUDStuffDAta hudStuffDAta) {
        this.hudStuffDAta = hudStuffDAta;
    }
}
