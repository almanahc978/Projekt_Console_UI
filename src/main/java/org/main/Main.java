package org.main;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.logo.Logo;
import org.logo.LogoSettings;
import org.ui.MainWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        Terminal terminal = TerminalSingleton.getInstance();
        Screen screen = ScreenSingleton.getInstance();

        LogoSettings settings = new LogoSettings(new Font("TimesRoman", Font.BOLD, 20), 100, 70);
        Logo logo = new Logo(settings, screen.getTerminalSize());

        MultiWindowTextGUI ui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLACK));

        screen.startScreen();

        logo.drawFirstLine("ChefeFile", "$");
        logo.drawSecondLine("Manager", "$");

        screen.refresh();

        while(true) {
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null && (keyStroke.getKeyType() == KeyType.Enter)) {
                MainWindow mainWindow = new MainWindow("ChefeFile Manager", ui);
                mainWindow.createMainMenu();
                ui.addWindowAndWait(mainWindow);
            } else if(keyStroke != null && (keyStroke.getKeyType() == KeyType.Escape)){
                System.exit(0);
                break;
            }
        }
    }
}
