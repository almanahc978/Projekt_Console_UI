package org.ui;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import org.logic.FileManager;

import java.io.File;

public class MainMenu extends ActionListDialogBuilder {

    public void createMenu(MultiWindowTextGUI ui) {
        setTitle("Files")
                .setDescription("Main menu")
                .addAction("Files", () -> {
                    File input = new FileDialogBuilder()
                            .setTitle("Open File")
                            .setDescription("Choose a file")
                            .setActionLabel("Choose")
                            .build()
                            .showDialog(ui);
                    if (input != null) {
                        if (input.exists()) {
                            new FileOptions(input).createMenu(ui);
                        } else {
                            FileManager.createFile(input.getAbsolutePath());
                        }
                    }
                })
                .addAction("Directories", () -> {
                    File input = new DirectoryDialogBuilder()
                            .setTitle("Select directory")
                            .setDescription("Choose a directory")
                            .setActionLabel("Choose")
                            .build()
                            .showDialog(ui);
                })
                .build()
                .showDialog(ui);
    }
}
