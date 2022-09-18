package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> rsl = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        List<Path> paths = new ArrayList<>();
        paths.add(file.toAbsolutePath());
        if (rsl.containsKey(fileProperty)) {
            List<Path> tmpPaths = rsl.get(fileProperty);
            tmpPaths.add(file.toAbsolutePath());
            rsl.put(fileProperty, tmpPaths);
            System.out.printf("%s - %.2fMb\n", fileProperty.getName(), (double) fileProperty.getSize() / 1024);
            for (Path p : tmpPaths) {
                System.out.println(p);
            }
        } else {
            rsl.put(fileProperty, paths);
        }
        return super.visitFile(file, attrs);
    }
}