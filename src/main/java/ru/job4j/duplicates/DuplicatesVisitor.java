package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> checkMap = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.isAbsolute()) {
            FileProperty temp = new FileProperty(file.toFile().length(), file.toFile().getName());

            checkMap.putIfAbsent(temp, new ArrayList<>());
            checkMap.get(temp).add(file);
        }
        return super.visitFile(file, attrs);
    }

    public void finedDuplicate() {
        for (FileProperty fp : checkMap.keySet()) {
            if (checkMap.get(fp).size() > 1) {
                for (Path p : checkMap.get(fp)) {
                    System.out.println(p.toAbsolutePath());
                }
            }
        }
    }
}
