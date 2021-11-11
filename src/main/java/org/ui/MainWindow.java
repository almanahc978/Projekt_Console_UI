package org.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.logic.KeyboardListener;
import org.main.MultiWindowTextGUISingleton;
import org.main.ScreenSingleton;
import org.ui.MainMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class MainWindow extends BasicWindow {

    MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();

    private Panel contentPanel;
    private Button startButton;
    private Button exitButton;
    private Button aboutButton;
    private MessageDialogBuilder messageDialogBuilder;
    public MainWindow(String name) {
        super(name);

        contentPanel = new Panel(new GridLayout(2));
        setComponent(contentPanel);
        setHints(Arrays.asList(Window.Hint.CENTERED));
        createMainMenu();
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
                Robot r = null;
                try {
                    r = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                switch (keyStroke.getKeyType()) {
                    case Escape:
                        basePane.close();
                        System.exit(0);
                    case F1:
                        startButton.takeFocus();
                        r.keyPress(KeyEvent.VK_ENTER);
                        r.keyRelease(KeyEvent.VK_ENTER);
                }

            }
        });
    }

    public MessageDialogBuilder getMessageDialogBuilder() {
        return messageDialogBuilder;
    }

    private Runnable createFileMenu() {
        return () -> {
            MainMenu mainMenu = null;
            try {
                mainMenu = new MainMenu();
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

    private Runnable exitWindow(){
        return  ()-> {
            System.exit(0);
        };
    }

    private void createMainMenu() {
        startButton = new Button("START(F1)", createFileMenu());
        aboutButton = new Button("ABOUT", () -> {
            if (messageDialogBuilder != null) {
                messageDialogBuilder = new MessageDialogBuilder()
                        .setExtraWindowHints(Arrays.asList(Hint.MENU_POPUP, Hint.CENTERED))
                        .setTitle("About")
                        .setText("about")
                        .addButton(MessageDialogButton.OK);
            }
            messageDialogBuilder
                    .build()
                    .showDialog(ui);
        });
        exitButton = new Button("EXIT(Escape)", exitWindow());

        contentPanel.addComponent(new Label("START"));
        contentPanel.addComponent(startButton);
        contentPanel.addComponent(new Label("ABOUT"));
        contentPanel.addComponent(aboutButton);
        contentPanel.addComponent(new Label("EXIT"));
        contentPanel.addComponent(exitButton);


    }


}

