package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
    DuplicatesVisitor dupl = new DuplicatesVisitor();
    Files.walkFileTree(Path.of("./"), dupl);
    dupl.finedDuplicate();
    }
}