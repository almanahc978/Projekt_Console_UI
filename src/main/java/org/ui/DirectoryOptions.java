package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.input.KeyStroke;
import org.logic.DirectoryManager;
import org.logic.FileManager;
import org.main.MultiWindowTextGUISingleton;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DirectoryOptions {

    private final File file;
    private final MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    private final Set<Window.Hint> set = new HashSet<>(Arrays.asList(Window.Hint.FIT_TERMINAL_WINDOW, Window.Hint.CENTERED, Window.Hint.NO_POST_RENDERING));
    private final ActionListDialogBuilder actionListDialogBuilder = new ActionListDialogBuilder();
    private final ActionListDialog actionListDialog;

    public DirectoryOptions(File file) {

        createMenu();
        actionListDialog = actionListDialogBuilder.setListBoxSize(new TerminalSize(20,3)).build();
        addListener();

        this.file = file;
        actionListDialogBuilder.setCanCancel(false);
        actionListDialog.showDialog(ui);
    }

    public Runnable renameDirectory (){
        return  ()-> {
            String newName = new TextInputDialogBuilder()
                    .setTitle("Rename")
                    .setDescription("Enter new name")
                    .build()
                    .showDialog(ui);
            if(newName != null){
                try {
                    DirectoryManager.renameDirectory(file, newName);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                actionListDialog.close();
                new MessageDialogBuilder()
                        .setTitle("Renamed    " + file.getName())
                        .setText(file.getName() + " file rename to " + newName)
                        .addButton(MessageDialogButton.Close)
                        .build()
                        .showDialog(ui);
            }
        };
    }

    public Runnable createDirectory (){
        return  ()-> {
            String newName = new TextInputDialogBuilder()
                    .setTitle("Create")
                    .setDescription("Enter directory name")
                    .build()
                    .showDialog(ui);

            if(newName != null){
                DirectoryManager.createDirectory(file.getAbsolutePath(), newName);
                new MessageDialogBuilder()
                        .setTitle("Created    " + newName)
                        .setText(newName + " directory created.")
                        .addButton(MessageDialogButton.Close)
                        .build()
                        .showDialog(ui);
            }


        };
    }

    public Runnable deleteDirectory (){
        return  ()-> {
             Path path = Paths.get(file.getAbsolutePath());
            DirectoryManager.deleteDirectory(path);
            actionListDialog.close();
            new MessageDialogBuilder()
                    .setTitle("Deleted    " + file.getName())
                    .setText(file.getName() + " has been deleted.")
                    .addButton(MessageDialogButton.Close)
                    .build()
                    .showDialog(ui);

        };
    }

    private void createMenu() {
        actionListDialogBuilder.setTitle("Files")
                .setDescription("File options")
                .addAction("Rename(F1)", renameDirectory())
                .addAction("Create(F2)", createDirectory())
                .addAction("Delete(F3)", deleteDirectory());
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
                    case Escape:
                        actionListDialog.close();
                }
            }
        });
    }

}
