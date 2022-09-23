package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;

import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        dir(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void dir(String[] args) {
        Path start = Paths.get(args[0]);
        Path finish = Paths.get(args[1]);
        if (!start.isAbsolute()) {
            throw new IllegalArgumentException("Is wrong way");
        }
        if (start.toFile().isFile()) {
            throw new IllegalArgumentException("File can not be a directory");
        }
        if (!finish.startsWith(".")) {
            throw new IllegalArgumentException("Incorrect file extension");
        }
    }
}
