package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isFile()) {
                System.out.println(subfile.getName() + " length = " + subfile.length());
            } else {
                System.out.println(subfile.getName() +  " length = " + subfile.length() + " but this is a folder.");
            }
        }
    }
}
