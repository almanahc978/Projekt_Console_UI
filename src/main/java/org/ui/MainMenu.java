package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import org.main.MultiWindowTextGUISingleton;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainMenu extends BasicWindow {

    private final MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    private final ActionListDialogBuilder actionListDialogBuilder = new ActionListDialogBuilder();
    private final ActionListDialog actionListDialog;


    public MainMenu() throws IOException {
        createMenu();
        actionListDialog = actionListDialogBuilder.setListBoxSize(new TerminalSize(20,5)).build();
        addListener();
        actionListDialog.showDialog(ui);
    }

    private Runnable openFileDialog() {
        return () -> {
            actionListDialog.close();
            FileWindow fileWindow = new FileWindow();
            ui.addWindow(fileWindow);
            ui.removeWindow(fileWindow);
            try {
                ui.updateScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private Runnable openDirectoryDialog() {
        return () -> {
            actionListDialog.close();
            DirectoryWindow directoryWindow = new DirectoryWindow();
            ui.addWindow(directoryWindow);
            ui.removeWindow(directoryWindow);
            try {
                ui.updateScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }


    private void createMenu() {
        actionListDialogBuilder.setTitle("Files")
                .setDescription("Main menu")
                .addAction("Files(F1)", openFileDialog())
                .addAction("Directories(F2)", openDirectoryDialog());
    }

    private void addListener() {
        actionListDialog.addWindowListener(new WindowListener() {
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
                switch (keyStroke.getKeyType()) {
                    case F1:
                        actionListDialogBuilder.getActions().get(0).run();
                        break;
                    case F2:
                        actionListDialogBuilder.getActions().get(1).run();
                        break;
                    case Escape:
                        basePane.close();
                        System.exit(0);
                }
            }
        });
    }

}
