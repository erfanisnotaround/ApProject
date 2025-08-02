package org.example.phaze2.model.abilities;

public enum AbilityTypes {
    Scroll_of_Aergia(1),
    Scroll_of_Sisyphus(2),
    Scroll_of_Eliphas(3);

    int numberToAccessLabel;
    AbilityTypes(int numberToAccessLabel){
        this.numberToAccessLabel = numberToAccessLabel;
    }
    public int getNumberToAccessLabel(){
        return numberToAccessLabel;
    }

}
