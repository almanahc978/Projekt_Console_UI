package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.dialogs.FileDialog;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import org.logic.FileManager;
import org.main.MultiWindowTextGUISingleton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileWindow extends BasicWindow {

    private FileDialogBuilder fileDialogBuilder = new FileDialogBuilder();
    MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    Path p1 = Paths.get("C:\\Users\\User");

    public FileWindow(){
       createFileWindow();
        FileDialog fileDialog = fileDialogBuilder.build();
        fileDialog.addWindowListener(new WindowListener() {
            @Override
            public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {

            }

            @Override
            public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {

            }

            @Override
            public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {

            }

            @Override
            public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
                Robot r = null;
                try {
                    r = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                switch (keyStroke.getKeyType()) {
                    case F1:
                        r.keyPress(KeyEvent.VK_ENTER);
                        r.keyRelease(KeyEvent.VK_ENTER);
                        break;
                    case F2:
                        fileDialog.close();
                        break;
                    case Escape:
                        basePane.close();
                        System.exit(0);
                }
            }
        });

        File input = fileDialog.showDialog(ui);
        if (input != null) {
            if (input.exists()) {
                new FileOptions(input).createMenu();
            } else {
                FileManager.createFile(input.getAbsolutePath());
            }
        }
    }

    private void createFileWindow(){
       fileDialogBuilder.setTitle("Open File")
                .setDescription("Choose a file")
                .setActionLabel("Choose(F1)/Close(F2)")
                .setSelectedFile(new File(String.valueOf(p1)));
    }
}
