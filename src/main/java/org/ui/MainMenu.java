package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.logic.FileManager;
import org.logic.KeyboardListener;
import org.main.MultiWindowTextGUISingleton;
import org.main.ScreenSingleton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainMenu extends BasicWindow {

    MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();


    Path p1 = Paths.get("C:\\Users\\User");
    private ActionListDialogBuilder actionListDialogBuilder = new ActionListDialogBuilder();

    public MainMenu() throws IOException {
        createMenu();
        ActionListDialog a = actionListDialogBuilder.build();
        a.addWindowListener(new WindowListener() {
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
        a.showDialog(ui);
    }

    private Runnable openFileDialog() {
        return () -> {
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
            File input = new DirectoryDialogBuilder()
                    .setTitle("Select directory")
                    .setDescription("Choose a directory")
                    .setSelectedDirectory(new File(String.valueOf(p1)))
                    .setActionLabel("Choose")
                    .build()
                    .showDialog(ui);
            System.out.println(input);
            if (input != null) {
                File file2 = new FileDialogBuilder()
                        .setTitle("Open File")
                        .setDescription("Choose a file")
                        .setActionLabel("Choose")
                        .setSelectedFile(input)
                        .build()
                        .showDialog(ui);
                if (file2 != null) {
                    if (input.exists()) {
                        new FileOptions(input).createMenu();
                    } else {
                        FileManager.createFile(input.getAbsolutePath());
                    }
                }
            }
        };
    }


    private void createMenu() {
        actionListDialogBuilder.setTitle("Files")
                .setDescription("Main menu")
                .addAction("Files(F1)", openFileDialog())
                .addAction("Directories(F2)", openDirectoryDialog());
    }
}
