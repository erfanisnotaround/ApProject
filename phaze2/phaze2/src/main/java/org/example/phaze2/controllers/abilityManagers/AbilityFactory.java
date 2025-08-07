package org.example.phaze2.controllers.abilityManagers;

import org.example.phaze2.model.abilities.types.*;
import org.example.phaze2.model.abilities.AbilityTypes;
import org.example.phaze2.model.abilities.mechanics.AbilityExecutable;

public class AbilityFactory {

    static AbilityExecutable createAbility(AbilityTypes abilityType){
        return switch (abilityType){
            case Scroll_of_Sisyphus -> new Scroll_Of_SisyPhus();
            case Scroll_of_Eliphas -> new Scroll_Of_Eliphus();
            case Scroll_of_Aergia -> new Scroll_of_Aergia();
            case OTAR -> new OTar();
            case OAIRYAMAN -> new OAiryaman();
            case OANAHITA -> new OAnahita();
            case ANCHOR_POINT -> new AnchotPointAbility();
        };

    }
}
