package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialog;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import org.logic.DirectoryManager;
import org.logic.FileManager;
import org.main.MultiWindowTextGUISingleton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class DirectoryWindow extends BasicWindow {
    private final DirectoryDialogBuilder directoryDialogBuilder = new DirectoryDialogBuilder();
    private final MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    private final Path path = Paths.get("C:\\Users\\User");
    private final DirectoryDialog directoryDialog;
    private DirectoryOptions directoryOptions;


    public DirectoryWindow() {
        createFileWindow();
        directoryDialog = directoryDialogBuilder.build();
        addListener();

        File input = directoryDialog.showDialog(ui);
        if (input != null) {
            directoryOptions = new DirectoryOptions(input);
            directoryDialog.close();
        }
    }

    private void createFileWindow() {
        directoryDialogBuilder.setTitle("Open Directory")
                .setDescription("Choose a directory")
                .setActionLabel("Choose(F1)/Close(F2)")
                .setSelectedDirectory(new File(String.valueOf(path)));
    }

    private void addListener() {
        directoryDialog.addWindowListener(new WindowListener() {
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
                        r.keyPress(KeyEvent.VK_TAB);
                        r.keyRelease(KeyEvent.VK_TAB);
                        r.keyPress(KeyEvent.VK_TAB);
                        r.keyRelease(KeyEvent.VK_TAB);
                        r.keyPress(KeyEvent.VK_ENTER);
                        r.keyRelease(KeyEvent.VK_ENTER);
                        break;
                    case F2:
                        directoryDialog.close();
                        break;
                }
            }
        });
    }
}
