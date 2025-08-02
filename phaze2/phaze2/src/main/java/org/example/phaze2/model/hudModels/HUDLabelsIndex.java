package org.example.phaze2.model.hudModels;

public enum HUDLabelsIndex {
    Coins(1),
    WireLeft(2),
    PocketLoss(3),
    Scroll_of_Aergia(1),
    Scroll_of_Sisyphus(2),
    Scroll_of_Eliphas(3);

    int numberToAccessLabel;
    HUDLabelsIndex(int numberToAccessLabel){
        this.numberToAccessLabel = numberToAccessLabel;
    }
    public int getNumberToAccessLabel(){
        return numberToAccessLabel;
    }
}
