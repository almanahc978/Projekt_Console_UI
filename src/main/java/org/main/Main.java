package org.main;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.logo.Logo;
import org.logo.LogoSettings;
import org.ui.MainWindow;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Terminal terminal = TerminalSingleton.getInstance();
        Screen screen = ScreenSingleton.getInstance();
        MultiWindowTextGUI ui = MultiWindowTextGUISingleton.getInstance();
        LogoSettings settings = new LogoSettings(new Font("TimesRoman", Font.BOLD, 20), 100, 70);
        Logo logo = new Logo(settings, screen.getTerminalSize());
//        terminal.setCursorVisible(false);
        screen.startScreen();

        logo.draw("ChefeFile", "$", screen.getCursorPosition().getColumn(), screen.getCursorPosition().getRow(), 0.25);
        logo.draw("Manager", "$", screen.getCursorPosition().getColumn() + 1, 0, 0.5);

        terminal.addResizeListener((terminal1, newSize) -> {
            try {
                screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        screen.refresh();

        while (true) {
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null && (keyStroke.getKeyType() == KeyType.Enter)) {
                MainWindow mainWindow = new MainWindow("ChefeFile Manager");
//                logo.clearTerminal();
                mainWindow.createMainMenu();
                ui.addWindowAndWait(mainWindow);
            } else if (keyStroke != null && (keyStroke.getKeyType() == KeyType.Escape)) {
                System.exit(0);
            }
        }
    }
}
