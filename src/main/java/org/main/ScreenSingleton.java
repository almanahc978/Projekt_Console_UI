package org.main;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class ScreenSingleton {
    private static Screen instance;

    private ScreenSingleton() {

    }

    public static synchronized Screen getInstance(){
        if (instance == null) {
            if (instance == null) {
                try {
                    instance = new TerminalScreen(TerminalSingleton.getInstance());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }
}
