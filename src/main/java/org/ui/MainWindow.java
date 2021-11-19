package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import org.main.MultiWindowTextGUISingleton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainWindow extends BasicWindow {

    private final Panel contentPanel;
    private MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
    private Button startButton;
    private Button exitButton;
    private Button aboutButton;
    private final Set<Hint> set = new HashSet<>(Arrays.asList(Window.Hint.FIT_TERMINAL_WINDOW, Window.Hint.CENTERED, Window.Hint.NO_POST_RENDERING));


    private Robot r;

    public MainWindow(String name) {
        super(name);
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        contentPanel = new Panel(new GridLayout(2));
        setComponent(contentPanel);
        setHints(Arrays.asList(Window.Hint.CENTERED));
        createMainMenu();
        addListener();
    }

    private Runnable createFileMenu() {
        return () -> {
            MainMenu mainMenu = null;
            try {
                mainMenu = new MainMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ui.updateScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ui.addWindow(mainMenu);
            ui.removeWindow(mainMenu);
            try {
                ui.updateScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private Runnable exitWindow() {
        return () -> System.exit(0);
    }

    private Runnable createAboutDialog() {
        return () -> {
            new MessageDialogBuilder()
                    .setTitle("About us")
                    .setText(" Kamil Zalewski \n Jędrzej Dąborwski \n ChefeFile Manager \n       @2021 v.1.3")
                    .setExtraWindowHints(set)
                    .addButton(MessageDialogButton.Close)
                    .build()
                    .showDialog(ui);
        };
    }

    private void createMainMenu() {
        startButton = new Button("F1", createFileMenu());
        aboutButton = new Button("F2", createAboutDialog());
        exitButton = new Button("Escape", exitWindow());

        contentPanel.addComponent(new Label("START"));
        contentPanel.addComponent(startButton);
        contentPanel.addComponent(new Label("ABOUT"));
        contentPanel.addComponent(aboutButton);
        contentPanel.addComponent(new Label("EXIT"));
        contentPanel.addComponent(exitButton);

    }

    private void addListener() {
        addWindowListener(new WindowListener() {
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
                    case Escape:
                        basePane.close();
                        System.exit(0);
                        break;
                    case F1:
                        startButton.takeFocus();
                        r.keyPress(KeyEvent.VK_ENTER);
                        r.keyRelease(KeyEvent.VK_ENTER);
                        break;
                    case F2:
                        aboutButton.takeFocus();
                        r.keyPress(KeyEvent.VK_ENTER);
                        r.keyRelease(KeyEvent.VK_ENTER);
                }

            }
        });
    }


}

