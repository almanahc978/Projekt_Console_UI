package org.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class FileManager {
    public static void createFile(String absolutePath) {
        try {
            File myObj = new File(absolutePath);
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void renameFile(File file, String newName) throws IOException {
        Files.move(file.toPath(), file.toPath().resolveSibling(newName));
    }

    public static void deleteFile(File file) {
        file.delete();
    }

    public static void editFile(File file, String newData) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file.getAbsolutePath());
        writer.print(newData);
        writer.close();
    }

    public static String openFile(File file) {
        StringBuilder data = new StringBuilder();
        try {
            File myObj = new File(file.getAbsolutePath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine() + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
