package org.main;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TerminalSingleton {
    private static Terminal instance;

    private TerminalSingleton() {

    }

    public static synchronized Terminal getInstance() {
        if (instance == null) {
            if (instance == null) {
                try {
                    instance = new DefaultTerminalFactory(System.out, System.in, StandardCharsets.UTF_8)
                            .setInitialTerminalSize(new TerminalSize(90, 40))
                            .createTerminal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

}
