package com.example.phaze1.model.constants;

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
