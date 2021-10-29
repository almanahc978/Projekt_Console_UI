package org.ui.MainMenuActionBuilder;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;

import java.io.File;

public class MainMenu {

    private MultiWindowTextGUI ui;

    public MainMenu(MultiWindowTextGUI ui){
        this.ui = ui;
    }

    public void createMenu(MultiWindowTextGUI ui) {
        new ActionListDialogBuilder()
                .setTitle("Files")
                .setDescription("Main menu")
                .addAction("Files", () -> {
                    File input = new FileDialogBuilder()
                            .setTitle("Open File")
                            .setDescription("Choose a file")
                            .setActionLabel("Open")
                            .build()
                            .showDialog(ui);

                })
                .addAction("Directories", () -> {
                    File input = new DirectoryDialogBuilder()
                            .setTitle("Select directory")
                            .setDescription("Choose a directory")
                            .setActionLabel("Select")
                            .build()
                            .showDialog(ui);
                    System.out.println(input);
                })
                .build()
                .showDialog(ui);
    }
}
