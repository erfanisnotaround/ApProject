package com.example.phaze1.controllers.ControllingPocketMovement;

import com.example.phaze1.Model.SystemsInfoAndManagers.Pocket;
import com.example.phaze1.Model.Constants.constants;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
