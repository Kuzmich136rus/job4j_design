package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Invalid key %s", key));
        }
        return values.get(key);
    }

    private void checkString(String check) {
        if (check.length() < 3) {
            throw new IllegalArgumentException("String has not contain all elements");
        }
        if (!check.startsWith("-")) {
            throw new IllegalArgumentException("Key not started with special symbol");
        }
        if (!check.contains("=")) {
            throw new IllegalArgumentException("String has not contain the symbol of =");
        }
        if (check.startsWith("=")) {
            throw new IllegalArgumentException("String has not contain key");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String arg : args) {
            checkString(arg);
            String[] parsed = arg.split("=", 2);
            values.put(parsed[0].substring(1), parsed[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}