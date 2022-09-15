package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> rsl = new ArrayList<>();
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            rsl = read.lines().filter(line -> !line.startsWith("#") && !line.isEmpty()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : rsl) {
            s = s.trim();
            int index = s.indexOf("=");
            if ("=".equals(s)) {
                throw new IllegalArgumentException(
                        String.format("this line: \"%s\"  does not contain a key and value", s));
            }
            if (s.startsWith("=")) {
                throw new IllegalArgumentException(
                        String.format("this line: \"%s\" does not contain a key", s));
            }
            if (index == s.length() - 1) {
                throw new IllegalArgumentException(
                        String.format("this line: \"%s\" does not contain a value", s));
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException(
                        String.format("this line: \"%s\" does not contain the equal sing", s));
            }
            values.put(s.substring(0, index), s.substring(index + 1));
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
}