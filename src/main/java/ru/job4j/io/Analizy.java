package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        List<String> list = read(source);
        List<String> rsl = new ArrayList<>();
        String status = "";
        String time = "";
        for (String s : list) {
            s = s.trim();
            String[] strings = s.split(" ");
            if (("200".equals(status) || "300".equals(status))
                    && ("400".equals(strings[0]) || "500".equals(strings[0]))) {
                time = time.concat(strings[1] + ";");
            }
            if (("400".equals(status) || "500".equals(status))
                    && ("200".equals(strings[0]) || "300".equals(strings[0]))) {
                time = time.concat(strings[1] + ";");
                rsl.add(time);
                time = "";
            }
            status = strings[0];
        }
        save(rsl, target);
    }

    public List<String> read(String source) {
        List<String> log = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            log = read.lines().filter(line -> !line.startsWith("#") && !line.isEmpty()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "./data/server.log";
        String target = "./data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
     }
}