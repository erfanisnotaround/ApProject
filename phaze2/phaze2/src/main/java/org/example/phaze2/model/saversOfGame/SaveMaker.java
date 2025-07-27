package org.example.phaze2.model.saversOfGame;

public class SaveMaker extends Thread {
    private final SaveHandler saveHandler;
    private volatile boolean running = true;
    private final long saveIntervalMillis = 5000;// 5 seconds
    private int currentLevel;

    public SaveMaker(int currentLevel) {
        this.saveHandler = new SaveHandler(currentLevel);
        setDaemon(true);
    }

    @Override
    public void run() {
        while (running) {
            try {
                saveHandler.StartSave();
                Thread.sleep(saveIntervalMillis);
            } catch (InterruptedException e) {
                System.out.println("SaveMaker interrupted. Exiting save loop.");
                running = false;
            } catch (Exception e) {
                System.err.println("SaveMaker encountered an error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void stopSaving() {
        running = false;
        this.interrupt();
    }
}
