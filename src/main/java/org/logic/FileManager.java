package org.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        file.renameTo(new File(newName));
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

    public static void copyFile(File file, String copyName) {
        if (copyName == null) {
            copyName = file.getName() + "(copy)";
        }

        Path path  = Paths.get(file.getAbsolutePath());
        Path copiedFile = path.resolveSibling(copyName);

        try {
            Files.copy(path, copiedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
