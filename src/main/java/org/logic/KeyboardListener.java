package org.logic;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.main.MultiWindowTextGUISingleton;

import java.util.concurrent.atomic.AtomicBoolean;

public class KeyboardListener implements WindowListener {
    MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();


    @Override
    public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
//        if(keyStroke.getKeyType().equals(KeyType.Escape)){
//            basePane.close();
//            System.exit(0);
//        }

        switch (keyStroke.getKeyType()){
            case Escape:
                basePane.close();
                System.exit(0);
        }

        switch (keyStroke.getCharacter()){
            case 'o':
                System.out.println('s');
        }

    }


    @Override
    public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {

    }

    @Override
    public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {

    }

    @Override
    public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
    }
    }


