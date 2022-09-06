package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, previous.size());
        Map<Integer, String> map = previous.stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x.getName()));
        for (User u : current) {
            if (map.containsKey(u.getId())) {
                info.setDeleted(info.getDeleted() - 1);
                if (!map.containsValue(u.getName())) {
                    info.setChanged(info.getChanged() + 1);
                }
            } else {
                info.setAdded(info.getAdded() + 1);
            }
        }
        return info;
    }
}