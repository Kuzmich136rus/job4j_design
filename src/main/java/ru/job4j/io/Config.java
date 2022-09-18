package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private boolean checkString(String readLine) {
        if (readLine.isBlank()) {
            return false;
        }
        if (readLine.startsWith("#")) {
            return false;
        }
        if (!readLine.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this string: %s not contain =", readLine));
        }
        if (readLine.startsWith("=")) {
            throw new IllegalArgumentException(
                    String.format("this string: %s start with =", readLine));
        }
        if (readLine.indexOf("=") == readLine.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this string: %s has not contain value", readLine));
                }
        return true;
    }

    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path))) {
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                if (checkString(readLine)) {
                    String[] lines = readLine.split("=", 2);
                    if (lines.length != 2 || readLine.startsWith("=")) {
                        throw new IllegalArgumentException();
                    }
                    values.put(lines[0], lines[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}
