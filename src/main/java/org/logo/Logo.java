package org.logo;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import org.main.ScreenSingleton;
import org.main.TerminalSingleton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Logo {

    private LogoSettings settings;
    private int width;
    private int height;
    private BufferedImage image;
    Graphics2D graphics2D;
    private Screen screen = ScreenSingleton.getInstance();

    public Logo(LogoSettings settings, TerminalSize terminalSize) {
        this.settings = settings;
        this.width = terminalSize.getColumns();
        this.height = terminalSize.getRows();
        this.image = getImageIntegerMode(settings.width, settings.height);
        this.graphics2D = getGraphics2D(image.getGraphics(), settings);
    }

    public void draw(String text, String artChar, int column, int row, double heightMultiplayer) {
        graphics2D.drawString(text, 0, ((int) (settings.height * heightMultiplayer)));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String c = image.getRGB(x, y) == -16777216 ? " " : artChar;
                screen.setCharacter(x, y, new TextCharacter(c.charAt(0)));
            }
        }

    }

    public static ArrayList getRandomNonRepeatingIntegers(int size, int min, int max) {
        ArrayList numbers = new ArrayList();
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }


    public void clearTerminal() throws InterruptedException, IOException {
        ArrayList rowList = getRandomNonRepeatingIntegers(height +1 , 0, height);
        ArrayList columnList = getRandomNonRepeatingIntegers(width +1 , 0, width);
        for (int y = 0; y < rowList.size() ; y++) {
            for (int x = 0; x < columnList.size(); x++) {
                screen.setCharacter((Integer) columnList.get(x), (Integer) rowList.get(y), new TextCharacter(' '));
                screen.refresh();
            }
        }
    }

    private BufferedImage getImageIntegerMode(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private Graphics2D getGraphics2D(Graphics graphics, LogoSettings settings) {
        graphics.setFont(settings.font);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        return graphics2D;
    }

}
