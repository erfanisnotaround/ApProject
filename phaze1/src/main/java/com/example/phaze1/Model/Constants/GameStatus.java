package com.example.phaze1.Model.Constants;

import com.example.phaze1.Model.JSonManager.JsonManager;
import com.example.phaze1.Model.levelLoadingStuff.level;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public enum GameStatus {
    START_GAME {
        @Override
        public String GetStyleSheets() {
            return"/styles/game_scene.css";
        }
    },
    SETTINGS {
        @Override
        public String GetStyleSheets() {
            return null;
        }
    },
    LEVELS {
        @Override
        public String GetStyleSheets() {
            return  null;
        }
    },
    MENU {
        @Override
        public String GetStyleSheets() {
            return null;
        }
    },
    ;


    public abstract String GetStyleSheets();

}
