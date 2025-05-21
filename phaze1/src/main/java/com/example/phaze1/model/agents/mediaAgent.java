package com.example.phaze1.model.agents;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class mediaAgent {
    private String path;
    private final MediaPlayer player;
    public mediaAgent(String path , MediaView mediaView) {
        String uri = new File(path).toURI().toString();
        Media media = new Media(uri);
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
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
