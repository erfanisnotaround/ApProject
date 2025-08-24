package org.example.phaze2.model.audio;

import javax.print.attribute.standard.Media;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class JavaSoundMusicPlayer implements MusicPlayer {
    private Clip clip;
    private FloatControl gain;      // MASTER_GAIN if available
    private double currentVol = 1.0;

    @Override
    public void load(String resourcePath) {
        close(); // dispose previous

        try (InputStream raw = getClass().getResourceAsStream(resourcePath)) {
            if (raw == null) {
                throw new IllegalArgumentException("Audio resource not found: " + resourcePath);
            }
            try (BufferedInputStream in = new BufferedInputStream(raw)) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(in);
                AudioFormat base = ais.getFormat();

                AudioFormat target = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        base.getSampleRate(),
                        16,
                        base.getChannels(),
                        base.getChannels() * 2,
                        base.getSampleRate(),
                        false
                );

                try (AudioInputStream pcm = AudioSystem.getAudioInputStream(target, ais)) {
                    DataLine.Info info = new DataLine.Info(Clip.class, pcm.getFormat());
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(pcm);
                }
            }

            // Volume control if provided by the mixer
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                applyVolume(currentVol);
            } else {
                gain = null;
            }

        } catch (UnsupportedAudioFileException e) {
            throw new IllegalArgumentException("Unsupported audio format. Use WAV/AIFF/AU (or add MP3 SPI).", e);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException("Failed to load audio: " + resourcePath, e);
        }
    }

    @Override public void playLoop() {
        if (clip == null) return;
        clip.stop();
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override public void pause()  { if (clip != null) clip.stop(); }
    @Override public void resume() { if (clip != null) clip.start(); }
    @Override public void stop()   { if (clip != null) { clip.stop(); clip.setFramePosition(0); } }

    @Override public void setVolume(double v01) {
        currentVol = Math.max(0.0, Math.min(1.0, v01));
        applyVolume(currentVol);
    }

    @Override public double getVolume() { return currentVol; }

    @Override public void close() {
        if (clip != null) {
            try { clip.stop(); } catch (Exception ignored) {}
            try { clip.flush(); } catch (Exception ignored) {}
            try { clip.close(); } catch (Exception ignored) {}
            clip = null;
        }
        gain = null;
    }

    private void applyVolume(double v01) {
        if (gain == null) return;  // some mixers don’t expose MASTER_GAIN
        // Map 0..1 to decibels; clamp to control range
        // dB = 20 * log10(v), with special case for v=0
        float min = gain.getMinimum(); // typically around -80 dB
        float max = gain.getMaximum(); // typically around 6 dB
        float db  = (v01 <= 0.0001) ? min : (float)(20.0 * Math.log10(v01));
        db = Math.max(min, Math.min(max, db));
        gain.setValue(db);
    }
}
