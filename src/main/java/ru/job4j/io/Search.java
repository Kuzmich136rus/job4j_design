package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;

import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        String[] param = dir(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith("." + param[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static String[] dir(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        Path start = Paths.get(args[0]);
        if (start.toFile().isFile()) {
            throw new IllegalArgumentException("File can not be a directory");
        }
        return args;
    }
}
