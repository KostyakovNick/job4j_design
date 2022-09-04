package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        info.setAdded(check(current, previous));
        info.setDeleted(check(previous, current));
        int i = 0;
        Iterator<User> it = current.iterator();
        while (it.hasNext()) {
            User u = it.next();
            if (previous.contains(u)) {
                Iterator<User> itC = previous.iterator();
                while (itC.hasNext()) {
                    User uC = itC.next();
                    if (u.getId() == uC.getId() && !Objects.equals(u.getName(), uC.getName())) {
                        i++;
                        info.setChanged(i);
                    }
                }
            }
        }
        return info;
    }

    public static int check(Set<User> set1, Set<User> set2) {
        int i = 0;
        Iterator<User> it1 = set1.iterator();
        while (it1.hasNext()) {
            User u1 = it1.next();
            if (!set2.contains(u1)) {
                i++;
            }
        }
        return i;
    }
}