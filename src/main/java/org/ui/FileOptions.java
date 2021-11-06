package org.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.terminal.Terminal;
import org.logic.FileManager;
import org.main.MultiWindowTextGUISingleton;
import org.main.TerminalSingleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileOptions extends ActionListDialogBuilder {

    private File file;

    public FileOptions(File file) {
        this.file = file;
        setCanCancel(false);
    }

    private MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    private Terminal terminal = TerminalSingleton.getInstance();
    Set<Window.Hint> set = new HashSet<>(Arrays.asList(Window.Hint.FIT_TERMINAL_WINDOW, Window.Hint.CENTERED, Window.Hint.NO_POST_RENDERING));


    public void createMenu() {
        setTitle("Files")
                .setDescription("File options")
                .addAction("Open", () -> {
                    String data = FileManager.openFile(file);
                    new TextInputDialogBuilder()
                            .setTitle(file.getName())
                            .setInitialContent(data)
                            .setTextBoxSize(new TerminalSize(80, 30))
                            .setExtraWindowHints(set)
                            .build()
                            .showDialog(ui);
                })
                .addAction("Rename", () -> {
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
                })
                .addAction("Delete", () -> {
                    FileManager.deleteFile(file);
                })
                .addAction("Edit", () -> {
                    String data = FileManager.openFile(file);
                    String newData = new TextInputDialogBuilder()
                            .setTitle(file.getName())
                            .setInitialContent(data)
                            .setTextBoxSize(new TerminalSize(80, 30))
                            .setExtraWindowHints(set)
                            .build()
                            .showDialog(ui);
                    if (newData != null) {
                        try {
                            FileManager.editFile(file, newData);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                })
                .build()
                .showDialog(ui);
    }
}
