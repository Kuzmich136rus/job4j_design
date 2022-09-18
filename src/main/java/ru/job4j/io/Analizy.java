package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean serverStatus = true;
            while (in.ready()) {
                String checkLine = in.readLine();
                if (serverStatus && checkLine.startsWith("400") || checkLine.startsWith("500")) {
                    serverStatus = false;
                    String[] stringSplit = checkLine.split(" ", 2);
                    out.write(stringSplit[1] + ";");
                } else if (!serverStatus && checkLine.startsWith("200") || checkLine.startsWith("300")) {
                    serverStatus = true;
                    String[] stringSplit = checkLine.split(" ", 2);
                    out.write(stringSplit[1] + ";" + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy test = new Analizy();
        test.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}