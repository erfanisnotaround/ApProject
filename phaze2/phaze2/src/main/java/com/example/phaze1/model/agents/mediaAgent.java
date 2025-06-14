package com.example.phaze1.model.agents;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Node;
import java.io.File;

public class mediaAgent {
    private static double volumeLevel = 1.0;
    private String path;
    private final MediaPlayer player;
    public mediaAgent(String path , MediaView mediaView) {
        String uri = new File(path).toURI().toString();
        Media media = new Media(uri);
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
    }
    public void setAudioVolume() {
        player.setVolume(volumeLevel);
    }
    public static double getVolumeLevel() {
        return volumeLevel;
    }

    public static void setVolumeLevel(double v) {
        volumeLevel = v;

    }

    public MediaPlayer getMediaPlayer() {
        return player;
    }

    public void Play(){
        player.play();
    }
    public void Stop(){
        player.stop();
    }
    public void Pause(){
        player.pause();
    }
}
