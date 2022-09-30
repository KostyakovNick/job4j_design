package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        List<List<String>> lines = read(argsName);
        write(lines, index(lines, argsName.get("filter").split(",")), argsName);
    }

    public static List<Integer> index(List<List<String>> lines, String[] filter) {
        List<Integer> in = new ArrayList<>();
        for (String f : filter) {
            in.add(lines.get(0).indexOf(f));
        }
        return in;
    }

    public static List<List<String>> read(ArgsName argsName) {
        List<List<String>> lines = new ArrayList<>();
        try (var scanner = new Scanner(Path.of(argsName.get("path"))).useDelimiter(argsName.get("delimiter"))) {
            while (scanner.hasNext()) {
                lines.add(Arrays.asList(scanner.nextLine().split(argsName.get("delimiter"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void write(List<List<String>> lines, List<Integer> in, ArgsName argsName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("out"), true))) {
            for (List<String> l :lines) {
                for (int i = 0; i < in.size(); i++) {
                    if (i < in.size() - 1) {
                        pw.print(l.get(in.get(i)) + ";");
                    } else {
                        pw.print(l.get(in.get(i)));
                    }
                }
                pw.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void check(String[] args) {

        if (!args[0].endsWith(".csv")) {
            throw new IllegalArgumentException("No read File!");
        }
        if (!args[1].endsWith(";")) {
            throw new IllegalArgumentException("Does not contain the sing \";\"");
        }
        if (!args[2].endsWith("stdout")) {
            throw new IllegalArgumentException("No write File");
        }
        if (!(args[3].contains("name") || args[3].contains("age") || args[3].contains("last_name") || args[3].contains("education"))) {
            throw new IllegalArgumentException("No filter");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("No arguments!");
        }
        check(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}