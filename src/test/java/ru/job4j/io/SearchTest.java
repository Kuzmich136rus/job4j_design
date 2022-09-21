package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class SearchTest {
    @Test
    void trueTes() throws IOException {
        Path start = Paths.get(".");
        Predicate<Path> condition = p -> p.toFile().getName().endsWith(".js");
        Search.search(start, condition);
    }

}