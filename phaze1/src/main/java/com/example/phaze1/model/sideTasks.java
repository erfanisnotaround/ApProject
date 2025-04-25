package com.example.phaze1.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class sideTasks {
    public static void minimizeOthersOnWindows() throws AWTException {
        Robot r = new Robot();
        // Press Windows key
        r.keyPress(KeyEvent.VK_WINDOWS);
        // Press Home
        r.keyPress(KeyEvent.VK_HOME);
        // Release Home
        r.keyRelease(KeyEvent.VK_HOME);
        // Release Windows key
        r.keyRelease(KeyEvent.VK_WINDOWS);
    }
}
