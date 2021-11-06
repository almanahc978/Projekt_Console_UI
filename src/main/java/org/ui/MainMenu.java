package org.ui;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import org.logic.FileManager;
import org.main.MultiWindowTextGUISingleton;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainMenu extends ActionListDialogBuilder {

    MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    Path p1 = Paths.get("C:\\Users\\User");

    public void createMenu() {
        setTitle("Files")
                .setDescription("Main menu")
                .addAction("Files", () -> {
                    File input = new FileDialogBuilder()
                            .setTitle("Open File")
                            .setDescription("Choose a file")
                            .setActionLabel("Choose")
                            .setSelectedFile(new File(String.valueOf(p1)))
                            .build()
                            .showDialog(ui);
                    if (input != null) {
                        if (input.exists()) {
                            new FileOptions(input).createMenu();
                        } else {
                            FileManager.createFile(input.getAbsolutePath());
                        }
                    }
                })
                .addAction("Directories", () -> {
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
                })
                .build()
                .showDialog(ui);
    }
}
