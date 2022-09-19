package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    String.format("There are no arguments with the key \"%s\"", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {

        for (String s : args) {
            s = s.trim();
            int index = s.indexOf("=");
            check(s);
            values.put(s.substring(1, index), s.substring(index + 1));
        }
    }

    private void check(String s) {
        int index = s.indexOf("=");
        if (!s.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("this arg: \"%s\" does not contain \"-\"", s));
        }
        if (index == 1) {
            throw new IllegalArgumentException(
                    String.format("this arg: \"%s\" does not contain a key", s));
        }
        if (index == s.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this arg: \"%s\" does not contain a value", s));
        }
        if (!s.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this arg: \"%s\" does not contain the equal sing", s));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("no args!");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-=512", "-encoding=UTF-8"});
        //System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}