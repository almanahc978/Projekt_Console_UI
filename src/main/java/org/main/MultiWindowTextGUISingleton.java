package org.main;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;

public class MultiWindowTextGUISingleton {
    private static MultiWindowTextGUI instance;

    private  MultiWindowTextGUISingleton(){
    }

    public static synchronized MultiWindowTextGUI getInstance() {
        if (instance == null) {
                instance =new MultiWindowTextGUI(ScreenSingleton.getInstance(), new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE_BRIGHT));
        }
        return instance;
    }
}
