package org.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class DirectoryManager {

    public static void renameDirectory(File file, String newName) throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        Path copiedFile = path.resolveSibling(newName);
        file.renameTo(new File(copiedFile.toString()));
    }

    public static void createDirectory(String absolutePath, String newName) {
        Path path =  Paths.get(absolutePath);
        Path newPath = Paths.get(String.valueOf(path), newName);

        try {
            Files.createDirectory(newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectory(Path path) {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectory(entry);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
