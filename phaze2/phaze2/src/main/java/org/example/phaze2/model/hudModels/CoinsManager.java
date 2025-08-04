package org.example.phaze2.model.hudModels;

public class CoinsManager {
    private int numberOfCoins = 0;



    public void Increment(int EnteredCoins) {
        this.numberOfCoins += EnteredCoins;
    }
    public void Decrement(int decrement) {
        this.numberOfCoins -= decrement;
    }
    public int getNumberOfCoins() {
        return  this.numberOfCoins;
    }
    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

}
