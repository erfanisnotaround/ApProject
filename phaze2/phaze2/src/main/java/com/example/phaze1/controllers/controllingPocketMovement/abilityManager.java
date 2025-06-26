package com.example.phaze1.controllers.controllingPocketMovement;

import com.example.phaze1.model.systemsInfoAndManagers.Pocket;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class abilityManager {
    shopStage ShopStage;
    List<Pocket> pockets;
    int coins;
    Label coinsShower;
    public abilityManager(int coins , Label coinsShower , List<Pocket> pockets) {
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
