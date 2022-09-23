package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path p : sources) {
                zip.putNextEntry(new ZipEntry(p.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(p.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkArgsName(ArgsName argsName) {
        if (!Files.exists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(
                    String.format("this \"%s\" is not a directory or there is no such directory", argsName.get("d")));
        }

        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(
                    String.format("this argument \"%s\" is not an extension or sign \".\" is not specified", argsName.get("e")));
        }
        if (!".zip".equals(argsName.get("o").substring(argsName.get("o").indexOf(".")))) {
            throw new IllegalArgumentException(
                    String.format("this argument \"%s\" is not an archive name", argsName.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("no args!");
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgsName(argsName);
        List<Path> list = Search.search(Paths.get(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));
        Zip zip = new Zip();
        zip.packFiles(list, new File(argsName.get("o")));
    }
}