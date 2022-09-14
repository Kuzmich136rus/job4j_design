package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> previousMap = new HashMap<>();
        Map<Integer, String> currentMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }
        for (Integer key : currentMap.keySet()) {
            if (!previousMap.containsKey(key)) {
                added++;
            }
        }
        for (Integer key : previousMap.keySet()) {
            if (!currentMap.containsKey(key)) {
                deleted++;
            } else {
                if (!currentMap.get(key).equals(previousMap.get(key))) {
                    changed++;
                }
            }
        }
        return new Info(added, changed, deleted);
    }
}