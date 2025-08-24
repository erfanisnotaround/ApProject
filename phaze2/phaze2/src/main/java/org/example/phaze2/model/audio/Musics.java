package org.example.phaze2.model.audio;

public enum Musics {
    GAMESCENE_MUSIC("/org/example/phaze2/musics/11. Lonely Day.mp3");


    private String musicPAth;
    Musics(String musicPAth) {
        this.musicPAth = musicPAth;
    }
    public String getMusicPath() {
        return musicPAth;
    }
}
