package org.example.phaze2.model.abilities;

import org.example.phaze2.model.abilities.types.OTar;

public enum AbilityTypes {
    Scroll_of_Aergia(1 , AbilityBaseType.Point_ON_CURVE),
    Scroll_of_Sisyphus(2 , AbilityBaseType.ACTION_BASE),
    Scroll_of_Eliphas(3 , AbilityBaseType.Point_ON_CURVE),
    OTAR(4 , AbilityBaseType.PERIOD_BASE),
    OAIRYAMAN(5 , AbilityBaseType.PERIOD_BASE),
    OANAHITA(6 , AbilityBaseType.ACTION_BASE),
    ANCHOR_POINT(7 , AbilityBaseType.Point_ON_CURVE);

    int numberToAccessLabel;
    AbilityBaseType baseType;
    AbilityTypes(int numberToAccessLabel , AbilityBaseType baseType){
        this.numberToAccessLabel = numberToAccessLabel;
        this.baseType = baseType;
    }
    public int getNumberToAccessLabel(){
        return numberToAccessLabel;
    }
    public AbilityBaseType getBaseType(){return baseType;}
}
