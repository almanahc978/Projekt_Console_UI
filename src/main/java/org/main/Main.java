package org.main;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.logo.Logo;
import org.logo.LogoSettings;
import org.ui.MainWindow;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    private final static String LOGO = new Logo().drawString("ChefeFile   MANAGER", "$", new LogoSettings(new Font("TimesRoman", Font.BOLD ,20), 10000, 1000)).toString();

    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
        Screen screen = new TerminalScreen(terminal);
        MultiWindowTextGUI ui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE_BRIGHT));
        screen.startScreen();

        terminal.putString(LOGO);

        MainWindow mainWindow = new MainWindow("ChefeFile Manager", ui);
        mainWindow.createMainMenu();
        ui.addWindowAndWait(mainWindow);

    }
}
