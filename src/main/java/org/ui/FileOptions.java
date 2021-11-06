package org.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.dialogs.*;
import org.logic.FileManager;
import org.main.MultiWindowTextGUISingleton;

import java.io.File;
import java.io.IOException;

public class FileOptions extends ActionListDialogBuilder {

    private File file;
    public FileOptions(File file){
        this.file = file;
    }
    MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();


    public void createMenu() {
        setTitle("Files")
                .setDescription("File options")
                .addAction("Open", ()->{
                    String data = FileManager.openFile(file);
                    new TextInputDialogBuilder()
                            .setTitle("Multi-line editor")
                            .setTextBoxSize(new TerminalSize(100, 100))
                            .setInitialContent(data)
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
                .addAction("Edit", ()->{;
                    FileManager.editFile(file);
                })
                .build()
                .showDialog(ui);
    }
}
