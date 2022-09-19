package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private static Map<FileProperty, List<Path>> rsl = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        List<Path> paths = new ArrayList<>();
        paths.add(file.toAbsolutePath());
        if (rsl.putIfAbsent(fileProperty, paths) != null) {
            paths = rsl.get(fileProperty);
            paths.add(file.toAbsolutePath());
            rsl.put(fileProperty, paths);
        }
        return super.visitFile(file, attrs);
    }

    public static void print() {
        for (Map.Entry<FileProperty, List<Path>> entry : rsl.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.printf("%s - %.2fMb\n", entry.getKey().getName(), (double) entry.getKey().getSize() / 1024);
                for (Path p : entry.getValue()) {
                    System.out.println(p);
                }
            }
        }
    }
}