package org.ui;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import org.ui.MainMenu;

import java.util.Arrays;

public class MainWindow extends BasicWindow {

    MultiWindowTextGUI ui;

    private Panel contentPanel;
    private Button startButton;
    private Button exitButton;
    private Button aboutButton;

    public MainWindow(String name, MultiWindowTextGUI ui) {
        super(name);
        this.ui = ui;
        contentPanel = new Panel(new GridLayout(2));
        setComponent(contentPanel);

    }

    public void createMainMenu() {

        startButton = new Button("OK", () -> {
            new MainMenu().createMenu(ui);
        });

        exitButton = new Button("OK", () -> {
            System.exit(0);
        });

        aboutButton = new Button("OK", () -> {
            new MessageDialogBuilder()
                    .setExtraWindowHints(Arrays.asList(Hint.MENU_POPUP, Hint.CENTERED))
                    .setTitle("About")
                    .setText("about")
                    .addButton(MessageDialogButton.OK)
                    .build()
                    .showDialog(ui);
        });

        contentPanel.addComponent(new Label("START"));
        contentPanel.addComponent(startButton);
        contentPanel.addComponent(new Label("ABOUT"));
        contentPanel.addComponent(aboutButton);
        contentPanel.addComponent(new Label("EXIT"));
        contentPanel.addComponent(exitButton);


    }


}

