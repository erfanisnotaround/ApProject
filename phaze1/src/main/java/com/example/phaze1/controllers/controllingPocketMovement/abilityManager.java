package com.example.phaze1.controllers.controllingPocketMovement;

import com.example.phaze1.model.systemsInfoAndManagers.Pocket;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class abilityManager {
    shopStage ShopStage;
    ArrayList<Pocket> pockets;
    int coins;
    Label coinsShower;
    public abilityManager(int coins , Label coinsShower , ArrayList<Pocket> pockets) {
        this.coins = coins;
        this.coinsShower = coinsShower;
        this.pockets = pockets;
        ShopStage = new shopStage(coinsShower);
    }

    public void OpenShop(){

        ShopStage.OpenShop();
    }
    public void CloseShop(){
        ShopStage.close();

    }

}
