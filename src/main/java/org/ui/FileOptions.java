package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import org.logic.FileManager;
import org.main.MultiWindowTextGUISingleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileOptions extends BasicWindow {

    private final File file;
    private final MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    private final Set<Window.Hint> set = new HashSet<>(Arrays.asList(Window.Hint.FIT_TERMINAL_WINDOW, Window.Hint.CENTERED, Window.Hint.NO_POST_RENDERING));
    private final ActionListDialogBuilder actionListDialogBuilder = new ActionListDialogBuilder();
    private final ActionListDialog actionListDialog;


    public FileOptions(File file) {
        createMenu();
        actionListDialog = actionListDialogBuilder.build();
        addListener();

        this.file = file;
        actionListDialogBuilder.setCanCancel(false);
        actionListDialog.showDialog(ui);
    }

    private Runnable openFile() {
        return () -> {
            String data = FileManager.openFile(file);
            new TextInputDialogBuilder()
                    .setTitle(file.getName())
                    .setInitialContent(data)
                    .setTextBoxSize(new TerminalSize(80, 30))
                    .setExtraWindowHints(set)
                    .build()
                    .showDialog(ui);
        };
    }

    private Runnable renameFile() {
        return () -> {
            String newName = new TextInputDialogBuilder()
                    .setTitle("Rename")
                    .setDescription("Enter new name")
                    .build()
                    .showDialog(ui);

            try {
                FileManager.renameFile(file, newName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private Runnable editFile() {
        return () -> {
            FileManager.deleteFile(file);
        };
    }

    private Runnable deleteFile() {
        return () -> {
            FileManager.deleteFile(file);
        };
    }

    private Runnable copyFile(){
        return () -> {
            String copyName = new TextInputDialogBuilder()
                    .setTitle("Copy")
                    .setDescription("Enter new name for a copy")
                    .build()
                    .showDialog(ui);

            FileManager.copyFile(file, copyName);
        };
    }



    private void createMenu() {
        actionListDialogBuilder.setTitle("Files")
                .setDescription("File options")
                .addAction("Open(F1)", openFile())
                .addAction("Rename(F2)", renameFile())
                .addAction("Copy(F3)", copyFile())
                .addAction("Edit(F4)", editFile())
                .addAction("Delete(F5)", deleteFile());
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
                    case F3:
                        actionListDialogBuilder.getActions().get(2).run();
                        break;
                    case F4:
                        actionListDialogBuilder.getActions().get(3).run();
                        break;
                    case F5:
                        actionListDialogBuilder.getActions().get(4).run();
                        break;
                    case Escape:
                        basePane.close();
                        System.exit(0);
                }
            }
        });
    }
}
