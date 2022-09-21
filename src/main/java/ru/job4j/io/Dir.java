package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]); //"c:\\projects"
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isFile()) {
                System.out.printf("%s has length %d%n", subfile.getName(), subfile.length());
            } else {
                System.out.printf("%s - is folder and has length %d%n", subfile.getName(), subfile.length());
            }
        }
    }
}
