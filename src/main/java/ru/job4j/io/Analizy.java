package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            List<String> rsl = new ArrayList<>();
            String status = "";
            String time = "";
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    line = line.trim();
                    String[] strings = line.split(" ");
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
            }
            save(rsl, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String source = "./data/server1.log";
        String target = "./data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
     }
}